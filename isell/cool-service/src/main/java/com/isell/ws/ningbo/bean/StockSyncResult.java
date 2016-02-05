package com.isell.ws.ningbo.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 库存同步查询结果
 * 
 * @author lilin
 * @version [版本号, 2015年11月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Message")
@XmlType(propOrder = {})
public class StockSyncResult {
    /**
     * T（成功），F（失败），S（Stop，平台时间超过数据库时间）
     */
    @XmlElement(name = "Result")
    private String result;
    
    /**
     * 若为F，显示失败信息
     */
    @XmlElement(name = "ResultMsg")
    private String resultMsg;
    
    /**
     * 2014-06-06 00:00:00（当前为止同步至数据库时间，操作时获取到的数据库时间）
     */
    @XmlElement(name = "SqlTime")
    private String sqlTime;
    
    /**
     * body部分
     */
    @XmlElement(name = "Body")
    private StockSyncBody body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

	public String getSqlTime() {
		return sqlTime;
	}

	public void setSqlTime(String sqlTime) {
		this.sqlTime = sqlTime;
	}

	public StockSyncBody getBody() {
		return body;
	}

	public void setBody(StockSyncBody body) {
		this.body = body;
	}
}
