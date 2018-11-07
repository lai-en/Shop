package com.jt.common.vo;

import java.io.Serializable;

/**
 * 值对象（VO）：通过此对象封装checkbox复选框相关的数据。
 * 
 * @author Administrator
 *
 */
public class CheckBox implements Serializable{
	
	private static final long serialVersionUID = -412637841166383222L;
	/** 角色id */
	private Integer id;
	/** 角色名字 */
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
