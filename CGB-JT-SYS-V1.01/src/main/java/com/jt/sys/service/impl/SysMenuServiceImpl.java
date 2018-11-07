package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.vo.Node;
import com.jt.sys.common.exception.ServiceException;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Override
	public List<Map<String, Object>> findObjects() {
		return sysMenuDao.findObjects();
	}

	/**
	 * 根据菜单的id删除菜单
	 * @param id	菜单id
	 * @return	删除的菜单id
	 */
	public Integer deleteObject(Integer id) {
		if(id == null || id<1) throw new IllegalArgumentException("id值无效");
		int count = sysMenuDao.getChildCount(id);
		if(count>0) throw new ServiceException("请先删除子菜单");
		int rows = sysMenuDao.deleteObject(id);
		if(rows == 0) throw new ServiceException("此记录已经不存在了");
		sysRoleMenuDao.deleteObjectsById("menu_id", id);
		return rows;
	}

	@Override
	public List<Node> findZTreeMenuNodes() {
		List<Node> list = sysMenuDao.findZTreeMenuNodes();
		if(list == null || list.size() == 0) {
			throw new ServiceException("没有记录");
		}
		return list;
	}

	@Override
	public int insertObject(SysMenu entity) {
		if(entity == null) {
			throw new ServiceException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("菜单名不能为空");
		}
		int row = sysMenuDao.insertObject(entity);
		if(row != 1) throw new ServiceException("插入失败");
		return row;
	}

	@Override
	public int updateObject(SysMenu entity) {
		if(entity == null) {
			throw new ServiceException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getName())) {
			throw new ServiceException("菜单名不能为空");
		}
		int row = sysMenuDao.updateObject(entity);
		if(row == 0) throw new ServiceException("修改失败，数据可能已经不存在了。");
		return row;
	}

}
