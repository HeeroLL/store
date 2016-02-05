package com.isell.ei.logistics.yitong.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 易通响应信息
 * 
 * @author lilin
 * @version [版本号, 2015年12月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ResponseInfo")
public class YitongResponse {
    /**
     * 结果集
     */
    @XmlElement(name = "Result")
    private List<YitongResult> resultList;

    public List<YitongResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<YitongResult> resultList) {
        this.resultList = resultList;
    }
}
