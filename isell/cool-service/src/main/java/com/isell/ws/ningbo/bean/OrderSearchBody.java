package com.isell.ws.ningbo.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 物流单查询body部分
 * 
 * @author lilin
 * @version [版本号, 2015年11月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class OrderSearchBody {
    /**
     * ERP物流编号
     */
    @XmlElement(name = "LogId")
    private String logId;
    
    /**
     * ERP物流名称
     */
    @XmlElement(name = "LogName")
    private String logName;
    
    /**
     * 物流单号
     */
    @XmlElement(name = "LogNo")
    private String logNo;
    
    /**
     * 创建时间
     */
    @XmlElement(name = "CreateTime")
    private String createTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getLogNo() {
        return logNo;
    }

    public void setLogNo(String logNo) {
        this.logNo = logNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
