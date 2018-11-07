package com.jt.sys.service;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.entity.SysRole;

public interface SysMenuService {

	List<Map<String, Object>> findObjects();

	Integer deleteObject(Integer id);
	
	List<Node> findZTreeMenuNodes();
	
	int insertObject(SysMenu entity);
	
	int updateObject(SysMenu entity);
}
