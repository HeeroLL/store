package com.zebone.dnode.etl.extract.pojo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class ExtractParameter {
	
	@XStreamImplicit(itemFieldName="table")
	private List<TableParameter> tableParameter;

	public List<TableParameter> getTableParameter() {
		return tableParameter;
	}

	public void setTableParameter(List<TableParameter> tableParameter) {
		this.tableParameter = tableParameter;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExtractParameter [tableParameter=");
		builder.append(tableParameter);
		builder.append("]");
		return builder.toString();
	}

}
