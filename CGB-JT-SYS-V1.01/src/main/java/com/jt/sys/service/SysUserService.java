package com.jt.sys.service;


import java.util.Map;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysUser;
import com.jt.sys.vo.SysUserDeptResult;

public interface SysUserService {

	PageObject<SysUserDeptResult> findPageObjects(String username,Integer pageCurrent);

	/**
	 * 角色禁用和启用
	 * @param user
	 * @return
	 */
	int doValidById(SysUserDeptResult user);
	
	/**
	 * 保存用户以及用户和角色的关系数据。
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObject(SysUser entity, Integer[] roleIds);
	
	Map<String, Object> findObjectById(Integer userid);
	
	int updateObject(SysUser entity, Integer[] roleIds);
}
