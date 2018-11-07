package com.jt.common.vo;

import java.io.Serializable;

/**
 * 用来封装zTree对象的树节点信息。
 * @author Administrator
 *
 */
public class Node implements Serializable{

	private static final long serialVersionUID = 4351174414771192644L;

	/**菜单id*/
	private Integer id;
	
	/**上级菜单的id*/
	private Integer parentId;
	
	/**菜单的名字*/
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
