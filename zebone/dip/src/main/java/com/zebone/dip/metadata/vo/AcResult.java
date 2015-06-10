package com.zebone.dip.metadata.vo;

import java.util.List;



/**
 * 手拼 匹配组件 对像
 * @author cz
 *
 */

public class AcResult {
	
	/** 是否成功  **/
	public boolean success;
	
	/** 返回的总数  **/
	public long total;
	
	/** 返回数据  **/
    public List<AcData> data;	
    
    /** 查询key **/
    public String query;
        
    
    public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public long getTotal() {
		return total;
	}


	public void setTotal(long total) {
		this.total = total;
	}



	public List<AcData> getData() {
		return data;
	}


	public void setData(List<AcData> data) {
		this.data = data;
	}




	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}



	public static final class AcData{
    	/** id **/
    	public String id;
    	/** 返回名称 **/
    	public String name;
    	
    	
		public AcData(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
    	    	
    }
}
