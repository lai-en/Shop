package com.jt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.vo.PageObject;
import com.jt.sys.common.exception.ServiceException;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;
import com.jt.sys.vo.SysUserDeptResult;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public PageObject<SysUserDeptResult> findPageObjects(String username, Integer pageCurrent) {
		// 1.验证参数合法性
		// 1.1验证pageCurrent的合法性，
		if (pageCurrent == null || pageCurrent < 1) {
			// 不合法抛出IllegalArgumentException异常
			throw new IllegalArgumentException("页码不正确");
		}
		// 2.基于条件查询总记录数
		// 2.1) 执行查询
		int rowCount = sysUserDao.getRowCount(username);
		// 2.2) 验证查询结果，假如结果为0不再执行如下操作
		if (rowCount == 0) {
			throw new ServiceException("没有记录");
		}
		// 3.基于条件查询当前页记录(pageSize定义为5)
		// 3.1)定义pageSize
		int pageSize = 5;
		// 3.2)计算startIndex
		int startIndex = (pageCurrent - 1) * pageSize;
		// 3.3)执行当前数据的查询操作
		List<SysUserDeptResult> records = sysUserDao.findPageObjects(username, startIndex, pageSize);
		// 4.对分页信息以及当前页记录进行封装
		// 4.1)构建PageObject对象
		PageObject<SysUserDeptResult> pageObject = new PageObject<SysUserDeptResult>();
		// 4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		pageObject.setRowCount(rowCount);
		// 5.返回封装结果。
		return pageObject;

	}

	@Override
	public int doValidById(SysUserDeptResult user) {
		if(user.getId() == null || user.getId() < 0) throw new IllegalArgumentException("用户id不正确");
		int row = sysUserDao.doValidById(user);
		if(row < 1) throw new ServiceException("记录可能已经不存在了");
		return row;
	}

	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
		if(entity==null) throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername())) throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword())) throw new IllegalArgumentException("密码不能为空");
		if(roleIds == null || roleIds.length == 0) throw new IllegalArgumentException("请最少选择一个角色");
		if(entity.getDeptId()==null) throw new IllegalArgumentException("必须为用户指定一个部门");
		//对密码进行加密
		//1.获取盐值
		String salt = UUID.randomUUID().toString();
		entity.setSalt(salt);
		//2.使用盐值对密码进行加密
		SimpleHash sh = new SimpleHash("MD5", entity.getPassword(), salt);
		String hashPassword = sh.toHex();
		System.out.println("hashPassword"+hashPassword);
		entity.setPassword(hashPassword);
		int row = sysUserDao.insertObject(entity);
		sysUserRoleDao.insertObject(entity.getId(), roleIds);
		return row;
	}

	@Override
	public Map<String, Object> findObjectById(Integer userId) {

		//1.合法性验证
		if(userId==null||userId<=0)
		throw new ServiceException("参数数据不合法,userId="+userId);
		//2.业务查询
		SysUserDeptResult user = sysUserDao.findObjectById(userId);
		if(user==null) throw new ServiceException("此用户已经不存在");
		List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(userId);
		//3.数据封装
		Map<String,Object> map=new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}

	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
		if(entity == null) throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername())) throw new IllegalArgumentException("用户名不能为空");
		if(roleIds == null || roleIds.length == 0) throw new IllegalArgumentException("请最少选择一个角色");
		if(entity.getDeptId()==null) throw new IllegalArgumentException("必须为用户指定一个部门");
		int row = sysUserDao.updateObject(entity);
//		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		row = sysUserRoleDao.deleteObjectsById("user_id", entity.getId());
		System.out.println(row);
		sysUserRoleDao.insertObject(entity.getId(), roleIds);
		return row;
	}

	
	
}
