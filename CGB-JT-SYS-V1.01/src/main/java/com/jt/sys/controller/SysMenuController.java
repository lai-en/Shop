package com.jt.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;

@Controller()
@RequestMapping("/menu")
public class SysMenuController {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping("/doMenuListUI")
	public String doMenuListUI() {
		return "sys/menu_list";
	}

	@RequestMapping("/doMenuEditUI")
	public String doMenuEditUI() {
		return "sys/menu_edit";
	}
	
	@RequestMapping("/doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		return new JsonResult(sysMenuService.findObjects());
	}
	

	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(int id) {
		sysMenuService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	
	@RequestMapping("/dofindZTreeMenuNodes")
	@ResponseBody
	public JsonResult dofindZTreeMenuNodes() {
		List<Node> list = sysMenuService.findZTreeMenuNodes();
		return new JsonResult(list);
	}

	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysMenu entity) {
		sysMenuService.insertObject(entity);
		return new JsonResult("Save Ok!");
	}
	
	@RequestMapping("/doupdateObject")
	@ResponseBody
	public JsonResult doupdateObject(SysMenu entity) {
		sysMenuService.updateObject(entity);
		return new JsonResult("Update Ok!");
	}
}
