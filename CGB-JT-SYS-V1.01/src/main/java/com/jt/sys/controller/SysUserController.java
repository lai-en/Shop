package com.jt.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;
import com.jt.sys.vo.SysUserDeptResult;

@Controller
@RequestMapping("/user")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("/doUserListUI")
	public String doUserListUI() {
		return "sys/user_list";
	}

	@RequestMapping("/doUserEditUI")
	public String doUserEditUI() {
		return "sys/user_edit";
	}

	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
		PageObject<SysUserDeptResult> findPageObjects = sysUserService.findPageObjects(username, pageCurrent);
		return new JsonResult(findPageObjects);
	}

	@RequestMapping("/doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id, Integer valid, String modifiedUser) {
		SysUserDeptResult user = new SysUserDeptResult();
		user.setId(id);
		user.setValid(valid);
		user.setModifiedUser(modifiedUser);
		sysUserService.doValidById(user);
		return new JsonResult("Update Ok!");
	}

	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysUser entity, Integer[] roleIds) {
		sysUserService.saveObject(entity, roleIds);
		return new JsonResult("save ok");
	}

	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		Map<String, Object> map = sysUserService.findObjectById(id);
		return new JsonResult(map);
	}

	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysUser entity, Integer[] roleIds) {
		sysUserService.updateObject(entity, roleIds);
		return new JsonResult("update ok");
	}
}
