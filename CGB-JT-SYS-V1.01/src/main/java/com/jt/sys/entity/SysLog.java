package com.jt.sys.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 日志的实体类。
 * @author Administrator
 *
 */
public class SysLog implements Serializable{
	private static final long serialVersionUID = -4527208385611897813L;
	
	private Integer id;
	/** 操作用户 */
	private String username;
	/** 执行的操作 */
	private String operation;
	/** 执行这个操作对应的方法 */
	private String method;
	/** 调用方法时传入的参数 */
	private String params;
	/** 方法执行的时间 */
	private Long time;
	/** 客户端的IP地址 */
	private String IP;
	/** 这个日志的创建时间 */
	private Date createdTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
