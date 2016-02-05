package com.isell.ei.logistics.yitong.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * 易通订单请求订单详情信息
 * 
 * @author lilin
 * @version [版本号, 2015年12月3日]
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class YitongRequestItem {
    /**
     * id
     */
    @XmlAttribute(name = "id")
    private String id;
    
    /**
     * 货号
     */
    private String itemNo;
    
    /**
     * 货品名
     */
    private String itemName;
    
    /**
     * 货物规格
     */
    private String itemModel;
    
    /**
     * 货物数量
     */
    private Integer itemQuantity;
    
    /**
     * 货物描述
     */
    private String itemDescribe = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemModel() {
        return itemModel;
    }

    public void setItemModel(String itemModel) {
        this.itemModel = itemModel;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemDescribe() {
        return itemDescribe;
    }

    public void setItemDescribe(String itemDescribe) {
        this.itemDescribe = itemDescribe;
    }
}
