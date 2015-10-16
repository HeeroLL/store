package com.isell.service.common.po;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequest {
    private String TxLogisticID;
    private AddressInfo sender;
    private AddressInfo receiver;
    private BigDecimal goodsValue;
    private List<ItemInfo> items;
    public String getTxLogisticID() {
        return TxLogisticID;
    }
    public void setTxLogisticID(String txLogisticID) {
        TxLogisticID = txLogisticID;
    }
    public AddressInfo getSender() {
        return sender;
    }
    public void setSender(AddressInfo sender) {
        this.sender = sender;
    }
    public AddressInfo getReceiver() {
        return receiver;
    }
    public void setReceiver(AddressInfo receiver) {
        this.receiver = receiver;
    }
    public BigDecimal getGoodsValue() {
        return goodsValue;
    }
    public void setGoodsValue(BigDecimal goodsValue) {
        this.goodsValue = goodsValue;
    }
    public List<ItemInfo> getItems() {
        return items;
    }
    public void setItems(List<ItemInfo> items) {
        this.items = items;
    }
    
}
