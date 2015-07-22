package com.sell.ei.logistics.ecm.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sell.core.base.BaseInfo;

/**
 * SCM推送销售订单外层接口
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmOrders extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4740807290819840102L;
    
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
