package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.common.vo.SysRoleMenuResult;
import com.jt.sys.common.exception.ServiceException;
import com.jt.sys.dao.SysRoleDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public PageObject<SysRole> doFindPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent == null || pageCurrent < 1) throw new IllegalArgumentException("页码不合法");
		int rowCount = sysRoleDao.getRowCount(name);
		if(rowCount < 1) throw new ServiceException("没有记录");
		int pageSize = 5;
		int startIndex = (pageCurrent-1) * pageSize;
		List<SysRole> list = sysRoleDao.doFindPageObjects(name, startIndex, pageSize);
		PageObject<SysRole> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(list);
		pageObject.setRowCount(rowCount);
		return pageObject;
	}

	@Override
	public int deleteObjects(Integer id) {
		if(id == null || id < 1) throw new IllegalArgumentException("id的值不正确"+id);
		int row = sysRoleDao.deleteObjects(id);
		if(row == 0) throw new ServiceException("记录可能已经被删除了");
		sysRoleMenuDao.deleteObjectsById("role_id",id);
//		sysUserRoleDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsById("role_id", id);
		
		return row;
	}

	@Override
	public int insertObject(SysRole entity,Integer[] menuIds) {
		if(entity == null) throw new ServiceException("保存数据不可以为空");
		if(StringUtils.isEmpty(entity.getName())) throw new ServiceException("角色名不能为空");
	   	if(menuIds==null||menuIds.length==0) throw new ServiceException("必须为角色赋予权限");
	   	int row = sysRoleDao.insertObject(entity);
	   	sysRoleMenuDao.insertObject(entity.getId(), menuIds);
		return row;
	}

	@Override
	public SysRoleMenuResult findObjectById(Integer id) {
		if(id == null || id<0) throw new IllegalArgumentException("id不合法");
		SysRoleMenuResult role = sysRoleDao.findObjectById(id);
		if(role == null) throw new ServiceException("记录可能已经被删除了");
		return role;
	}

	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		if(entity == null) throw new ServiceException("保存数据不可以为空");
		if(StringUtils.isEmpty(entity.getName())) throw new ServiceException("角色名不能为空");
	   	if(menuIds==null||menuIds.length==0) throw new ServiceException("必须为角色赋予权限");
	   	int row = sysRoleDao.updateObject(entity);
		if(row == 0) throw new ServiceException("记录可能已经被删除了");
    	sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
    	sysRoleMenuDao.insertObject(entity.getId(),menuIds);
		return row;
	}

	@Override
	public List<CheckBox> findObjects() {
		return sysRoleDao.findObjects();
	}


}
