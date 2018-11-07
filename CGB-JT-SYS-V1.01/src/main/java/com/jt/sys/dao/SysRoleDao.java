package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.SysRoleMenuResult;
import com.jt.sys.entity.SysRole;

public interface SysRoleDao {
	
	/**
	 * 分页查询数据库中的角色数据。
	 * @return
	 * @param startIndex 表示从那个记录开始
	 * @param pageSize 表示页面中显示几条数据
	 */
	List<SysRole> doFindPageObjects(
			@Param("name") String name, 
			@Param("startIndex") Integer startIndex, 
			@Param("pageSize") Integer pageSize);
	
	/**
	 * 查询总记录数
	 * @return
	 * @param name 表示查询条件
	 */
	int getRowCount(@Param("name") String name);
	
	/**
	 * 根据id删除角色信息
	 * @param id
	 * @return
	 */
	int deleteObjects(@Param("id") Integer id);
	
	/**
	 * 将角色信息插入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysRole entity);
	
	/**
	 * 基于角色id查询角色信息
	 * @param id
	 * @return
	 */
	SysRoleMenuResult findObjectById(@Param("id") Integer id);
	
	/**
	 * 基于id修改角色信息。
	 * @param entity
	 * @return
	 */
	int updateObject(SysRole entity);
	
	/**
	 * 将角色的id和名字从数据库中取出。
	 * @return
	 */
	List<CheckBox> findObjects();
}
