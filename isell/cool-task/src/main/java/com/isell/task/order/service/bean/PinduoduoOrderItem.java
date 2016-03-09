package com.isell.task.order.service.bean;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 拼多多订单明细
 * 
 * @author lilin
 * @version [版本号, 2015年12月23日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PinduoduoOrderItem {
    /**
     * 库存编码
     */
    @XmlElement(name = "GoodsID")
    private String goodsID;
    
    /**
     * 货品名称
     */
    @XmlElement(name = "GoodsName")
    private String goodsName;
    
    /**
     * 货品规格
     */
    @XmlElement(name = "GoodsSpec")
    private String goodsSpec;
    
    /**
     * 数量
     */
    @XmlElement(name = "Count")
    private Integer count;
    
    /**
     * 单价
     */
    @XmlElement(name = "Price")
    private BigDecimal price;
    
    public String getGoodsID() {
        return goodsID;
    }
    
    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }
    
    public String getGoodsName() {
        return goodsName;
    }
    
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public String getGoodsSpec() {
        return goodsSpec;
    }
    
    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
