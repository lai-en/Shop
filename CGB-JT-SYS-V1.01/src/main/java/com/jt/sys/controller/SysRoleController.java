package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.common.vo.SysRoleMenuResult;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;

@Controller
@RequestMapping("/role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("/doRoleListUI")
	public String doRoleListUI() {
		return "sys/role_list";
	}

	@RequestMapping("/doRoleEditUI")
	public String doRoleEditUI() {
		return "sys/role_edit";
	}

	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
		PageObject<SysRole> doFindPageObjects = sysRoleService.doFindPageObjects(name, pageCurrent);
		return new JsonResult(doFindPageObjects);
	}

	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		sysRoleService.deleteObjects(id);
		return new JsonResult("Delete Ok!");
	}

	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysRole entity, Integer[] menuIds) {
		sysRoleService.insertObject(entity, menuIds);
		return new JsonResult("Save Ok!");
	}

	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		SysRoleMenuResult result = sysRoleService.findObjectById(id);
		return new JsonResult(result);
	}

	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysRole entity, Integer[] menuIds) {
		sysRoleService.updateObject(entity, menuIds);
		return new JsonResult("Update Ok！");
	}

	@RequestMapping("/doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		return new JsonResult(sysRoleService.findObjects());
	}
}
