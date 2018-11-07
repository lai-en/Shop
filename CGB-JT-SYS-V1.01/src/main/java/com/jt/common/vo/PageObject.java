package com.jt.common.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 该类用于封装分页信息和当前页数据。
 * @author Administrator
 *
 * @param <T>
 */
public class PageObject<T>  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer pageCurrent = 1;
	
	private Integer pageSize = 3;
	
	private Integer rowCount;
	
	/** 总页数 */
	private Integer pageCount;
	
	private List<T> records;

	
	
	
	
	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public Integer getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getPageCount() {
		return (rowCount - 1) / pageSize + 1;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
}
