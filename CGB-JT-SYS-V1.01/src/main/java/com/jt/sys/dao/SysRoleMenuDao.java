package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * sys_role_menus表所对应的dao
 * @author Administrator
 *
 */
public interface SysRoleMenuDao {
	
	/**
	 * 根据菜单的id删除关系表中和角色关联的记录。
	 * @param id	菜单id
	 * @return	删除了几条。
	 */
	int deleteObjectsByMenuId(@Param("id") int menuId);
	
	/**
	 * 根据页面中的角色id删除角色和菜单关联表的信息。
	 * @param roldId
	 * @return
	 */
	int deleteObjectsByRoleId(@Param("id") int roldId);
	

	int deleteObjectsById(
			@Param("columnName") String columnName, 
			@Param("columnValue") Integer columnValue);
	
	/**
	 * 向数据库中插入角色和菜单的关系信息，一个角色可以有多个菜单，同时一个菜单可以被多个角色应用。
	 */
	int insertObject(@Param("roldId")Integer roldId, @Param("menuIds")Integer[] menuId);
	
	/**
	 * 根据角色id查询对应的角色和菜单关系数据。
	 * @param id
	 * @return
	 */
	List<Integer> findMenuIdsByRoleId(@Param("roldId") int roldId);
}
