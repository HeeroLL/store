package com.isell.ei.pay.ehking.bean;

/**
 * 支付单商品信息
 * 
 * @author lilin
 * @version [版本号, 2015年11月30日]
 */
public class EhkingProductDetail {
    /**
     * 商品名称
     */
    private String name = "";
    
    /**
     * 商品数量
     */
    private String quantity = "";
    
    /**
     * 商品金额 单位:分，1元=100分
     */
    private String amount = "";
    
    /**
     * 收款人
     */
    private String receiver = "";
    
    /**
     * 商品描述
     */
    private String description = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
