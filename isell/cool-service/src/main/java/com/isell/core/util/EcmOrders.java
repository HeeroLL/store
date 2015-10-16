package com.isell.core.util;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * SCM推送销售订单外层接口
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmOrders {
    
    /** orders */
    @JsonProperty("Orders")
    private List<EcmOrder> orders;
    
    public List<EcmOrder> getOrders() {
        return orders;
    }
    
    public void setOrders(List<EcmOrder> orders) {
        this.orders = orders;
    }
}
