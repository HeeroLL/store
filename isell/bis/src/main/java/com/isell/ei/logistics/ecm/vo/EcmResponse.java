package com.isell.ei.logistics.ecm.vo;

import org.codehaus.jackson.annotate.JsonProperty;

import com.isell.core.base.BaseInfo;

/**
 * Ecm的返回消息体
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmResponse extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 6714954488746595966L;
    
    /** ROWSET */
    @JsonProperty("ROWSET")
    private EcmResponseBody rowset;

    public EcmResponseBody getRowset() {
        return rowset;
    }

    public void setRowset(EcmResponseBody rowset) {
        this.rowset = rowset;
    }
}
