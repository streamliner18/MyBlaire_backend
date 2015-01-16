package com.myBlaire.util;

import java.util.List;
/**
 * 分页信息类
 * @author GWise003
 *
 * @param <T>
 */
public class PageInfo<T> {

	private List<T> rows;
	private Integer total;
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public PageInfo(List<T> rows, Integer total) {
		super();
		this.rows = rows;
		this.total = total;
	}
	
}
