package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysUser;
import com.jt.sys.vo.SysUserDeptResult;

public interface SysUserDao {
	/**
	 * 根据条件查询用户的总记录数。
	 * @param username
	 * @return
	 */
	int getRowCount(@Param("username")String username);
	
	/**
	 * 分页查询用户以及对应的部门信息
	 * @param name
	 * @param stateIndex
	 * @param pageSize
	 * @return
	 */
	List<SysUserDeptResult> findPageObjects(
			@Param("username")String name, 
			@Param("startIndex")Integer startIndex, 
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 角色的禁用和启用
	 * @param user
	 * @return
	 */
	int doValidById(SysUserDeptResult user);
	
	/**
	 * 保存用户的自身信息
	 */
	int insertObject(SysUser entity);
	
	/**
	 * 基于用户的id查询用户信息以及用于对应的部门信息。
	 * @param id
	 * @return
	 */
	SysUserDeptResult findObjectById(Integer id);
	
	/**
	 * 修改用户信息。
	 * @param user
	 * @return
	 */
	int updateObject(SysUser user);
	
	/**
	 * 根据部门id查询用户数量
	 * @param deptId
	 * @return
	 */
	int getUserCountByDeptId(Integer deptId);
}
