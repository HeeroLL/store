
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
 *         &lt;element name="erpKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="erpSecret" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="shopID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Tid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "erpKey",
    "erpSecret",
    "shopID",
    "tid"
})
@XmlRootElement(name = "GetKJB2CLogisticsInfo")
public class GetKJB2CLogisticsInfo {

    protected String erpKey;
    protected String erpSecret;
    protected int shopID;
    @XmlElement(name = "Tid")
    protected String tid;

    /**
     * 获取erpKey属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErpKey() {
        return erpKey;
    }

    /**
     * 设置erpKey属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErpKey(String value) {
        this.erpKey = value;
    }

    /**
     * 获取erpSecret属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErpSecret() {
        return erpSecret;
    }

    /**
     * 设置erpSecret属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErpSecret(String value) {
        this.erpSecret = value;
    }

    /**
     * 获取shopID属性的值。
     * 
     */
    public int getShopID() {
        return shopID;
    }

    /**
     * 设置shopID属性的值。
     * 
     */
    public void setShopID(int value) {
        this.shopID = value;
    }

    /**
     * 获取tid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTid() {
        return tid;
    }

    /**
     * 设置tid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTid(String value) {
        this.tid = value;
    }

}
