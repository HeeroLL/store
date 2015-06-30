package com.sell.core.web;

import org.apache.ibatis.session.RowBounds;

/**
 * 分页参数
 * 
 * @author songjunjie
 * @version 2013-12-20 上午9:45:11
 */
public class PageParam {
	private Integer page;// 页码
	private Integer rows;// 每页多少条数据

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	/**
	 * 得到 RowBounds 对象
	 * @return
	 */
	public  RowBounds getRowBounds(){
		int offset = (page - 1) * rows;
		return new RowBounds(offset,rows);
	}
}
