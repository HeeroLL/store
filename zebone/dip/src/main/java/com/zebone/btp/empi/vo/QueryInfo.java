package com.zebone.btp.empi.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author ouyangxin
 * 
 * CreateDate: 2013-1-21
 */
public class QueryInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 逗号分隔id的字符串 */
	private String id;

	/** 主键id集合 */
	private List<String> idList;

	public void addId(String id) {
		if (idList == null) {
			this.idList = new ArrayList<String>();
		}
		idList.add(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	
    /**
     * 重写toString方法
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
