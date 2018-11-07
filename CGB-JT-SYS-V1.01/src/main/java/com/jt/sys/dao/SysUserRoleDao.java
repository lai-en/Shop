package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleDao {
	/**
	 * 根据页面中的id信息删除角色和用户表中的关联信息。
	 * 
	 * @param roldId
	 * @return
	 */
	int deleteObjectsByRoleId(@Param("id") int roldId);

	/**
	 * 负责将用户与角色的关系数据写入到数据库
	 * 
	 * @param userId  用户id
	 * @param roleIds 多个角色id
	 * @return
	 */
	int insertObject(@Param("userId") Integer userId, @Param("roleIds") Integer[] roleIds);
	
	/**
	 * 基于用户id获取用户对应的角色id。
	 * @param id
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer id);
	
	/**
	 * 修改用户信息，根据用户的id去删除该用户对应角色信息，然后在从新添加新的角色信息。
	 * @param userId
	 * @return
	 */
	int deleteObjectsByUserId(Integer userId);
	
	
	int deleteObjectsById(
			@Param("columnName") String columnName, 
			@Param("columnValue") Integer columnValue);
}
