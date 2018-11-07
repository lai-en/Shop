package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysLog;
import com.jt.sys.service.SysLogService;

@Controller
@RequestMapping("/log")
public class SysLogController {
	
	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
		PageObject<SysLog> findPageObjects = sysLogService.findPageObjects(name, pageCurrent);
		return new JsonResult(findPageObjects);
	}
	
	@RequestMapping("/doFindPageLogs")
	public ModelAndView doFindPageLogs(String name, Integer pageCurrent) {
		PageObject<SysLog> findPageObjects = sysLogService.findPageObjects(name, pageCurrent);
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageObject",findPageObjects);
		mv.setViewName("jsp/log");
		return mv;
	}
	
	@RequestMapping("/doLogListUI")
	public String doLogListUI() {
		return "sys/log_list";
	}

	@RequestMapping("/doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer... ids) {
		sysLogService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}
}
