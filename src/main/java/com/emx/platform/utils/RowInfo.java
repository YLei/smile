package com.emx.platform.utils;

import java.io.Serializable;

public class RowInfo implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页数
     */
    private Integer totalPages;
    /**
     * 当前页
     */
    private Integer pageNo;
    /**
     * 页记录数
     */
    private Integer pageSize;
    
	public RowInfo(Long total, Integer totalPages, Integer pageNo, Integer pageSize) {
		super();
		this.total = total;
		this.totalPages = totalPages;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
