package com.jt.sys.service;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.common.vo.SysRoleMenuResult;
import com.jt.sys.entity.SysRole;

public interface SysRoleService {

	/**
	 * 根据用户的要求的页和条件返回对应的数据。
	 * @param name	条件参数
	 * @param pageCurrent	要查询页的页码
	 * @return 返回一个vo对象用于封装查询出的结果集。
	 */
	PageObject<SysRole> doFindPageObjects(String name, Integer pageCurrent);
	
	/**
	 * 根据角色id删除角色信息，以及角色对应的关系表中的信息。
	 * @param id
	 * @return
	 */
	int deleteObjects(Integer id) ;

	/**
	 * 将角色信息插入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity,Integer[] menuIds);
	
	SysRoleMenuResult findObjectById(Integer id);

	int updateObject(SysRole entity,Integer[] menuIds);
	

	List<CheckBox> findObjects();
}
