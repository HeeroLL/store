
package com.isell.ws.ningbo.ws.logistic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetKJB2CLogisticsInfoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getKJB2CLogisticsInfoResult"
})
@XmlRootElement(name = "GetKJB2CLogisticsInfoResponse")
public class GetKJB2CLogisticsInfoResponse {

    @XmlElement(name = "GetKJB2CLogisticsInfoResult")
    protected String getKJB2CLogisticsInfoResult;

    /**
     * 获取getKJB2CLogisticsInfoResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetKJB2CLogisticsInfoResult() {
        return getKJB2CLogisticsInfoResult;
    }

    /**
     * 设置getKJB2CLogisticsInfoResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetKJB2CLogisticsInfoResult(String value) {
        this.getKJB2CLogisticsInfoResult = value;
    }

}
