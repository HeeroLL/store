package com.isell.ei.logistics.yitong.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 易通订单请求信息
 * 
 * @author lilin
 * @version [版本号, 2015年12月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rows")
public class YitongRequestRows {
    /**
     * 订单行
     */
    @XmlElement(name = "row")
    private List<YitongRequestRow> rows;

    public List<YitongRequestRow> getRows() {
        return rows;
    }

    public void setRows(List<YitongRequestRow> rows) {
        this.rows = rows;
    }
}
