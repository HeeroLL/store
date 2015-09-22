package com.zebone.dnode.engine.reversebuild.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class BusDataPara {

	public List<BusData> data;

	public List<BusData> getData() {
		return data;
	}

	public void setData(List<BusData> data) {
		this.data = data;
	}

	public static class BusData {

		@JSONField(name = "TABLENAME")
		private String tableName;

		@JSONField(name = "DOC_NO")
		private String docNo;

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public String getDocNo() {
			return docNo;
		}

		public void setDocNo(String docNo) {
			this.docNo = docNo;
		}

	}

}
