package com.zebone.btp.core.util;

import java.util.List;

/**
 * Grid表格数据项
 * 
 * @author 宋俊杰
 * 
 */
public class JsonGrid {

	private String success = "true";

	private int total = 0;

	@SuppressWarnings("unchecked")
	private List data;

	public JsonGrid(){
		
	}
	
	@SuppressWarnings("unchecked")
	public JsonGrid(String success, int total, List data) {

		this.success = success;
		this.total = total;	
		this.data = data;

	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	@SuppressWarnings("unchecked")
	public void setData(List data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
