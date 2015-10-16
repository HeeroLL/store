package com.isell.service.common.po;

import java.math.BigDecimal;

public class ItemInfo {
    private String itemName;
    private int number;
    private BigDecimal itemValue;
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public BigDecimal getItemValue() {
        return itemValue;
    }
    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }
   
}
