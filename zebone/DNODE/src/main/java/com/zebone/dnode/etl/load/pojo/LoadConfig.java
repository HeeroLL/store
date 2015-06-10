package com.zebone.dnode.etl.load.pojo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("loadConfig")
public class LoadConfig {
	@XStreamImplicit(itemFieldName = "load")
	private List<Load> load;

	public List<Load> getLoad() {
		return load;
	}

	public void setLoad(List<Load> load) {
		this.load = load;
	}

	public static class Load {
		@XStreamImplicit(itemFieldName = "tableMapping")
		private List<TableMapping> tableMapping;
        
		@XStreamImplicit(itemFieldName = "sql")
		private List<Sql> sql;
		
		public List<TableMapping> getTableMapping() {
			return tableMapping;
		}

		public void setTableMapping(List<TableMapping> tableMapping) {
			this.tableMapping = tableMapping;
		}

		public List<Sql> getSql() {
			return sql;
		}

		public void setSql(List<Sql> sql) {
			this.sql = sql;
		}
		
		

	}
	
	@XStreamConverter(value=ToAttributedValueConverter.class, strings={"sql"})
	public static class Sql{
		@XStreamAsAttribute
		private String id;
		
		private String sql;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSql() {
			return sql;
		}

		public void setSql(String sql) {
			this.sql = sql;
		}

	}

	public static class TableMapping {
		
		@XStreamAsAttribute
		private String fid;
		
		@XStreamAsAttribute
		private String target;
		
		@XStreamImplicit(itemFieldName = "columnMapping")
		private List<ColumnMapping> columnMapping;

		public List<ColumnMapping> getColumnMapping() {
			return columnMapping;
		}

		public void setColumnMapping(List<ColumnMapping> columnMapping) {
			this.columnMapping = columnMapping;
		}

		public String getFid() {
			return fid;
		}

		public void setFid(String fid) {
			this.fid = fid;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}
		

	}

	public static class ColumnMapping {
		@XStreamAsAttribute
		private String from;
		
		@XStreamAsAttribute
		private String to;
		
		@XStreamImplicit(itemFieldName = "covert")
		private List<Covert> convert;

		public List<Covert> getConvert() {
			return convert;
		}

		public void setConvert(List<Covert> convert) {
			this.convert = convert;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}
		

	}

	public static class Covert {
		@XStreamAsAttribute
		private String tclass;

		public String getTclass() {
			return tclass;
		}

		public void setTclass(String tclass) {
			this.tclass = tclass;
		}

	}
}
