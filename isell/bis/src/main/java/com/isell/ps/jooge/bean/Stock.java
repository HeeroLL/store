package com.isell.ps.jooge.bean;

public class Stock {
    /* 商品Id 或 Sku Id */
    private String id;
    
    /* 仓库（自提点） */
    private String warehouse;
    
    /* 库存数量 */
    private Integer qty;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getWarehouse() {
        return warehouse;
    }
    
    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
    
    public Integer getQty() {
        return qty;
    }
    
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    
}
