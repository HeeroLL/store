package com.sell.ei.logistics.ecm.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sell.core.base.BaseInfo;

/**
 * ECM订单批量发货接口参数
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
public class EcmShipOrders extends BaseInfo {

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7827497655745760239L;
    
    /**
     * 批量发货参数集合
     */
    @JsonProperty("SHIPPORDERS")
    private List<EcmShipOrder> shipporders;

    public List<EcmShipOrder> getShipporders() {
        return shipporders;
    }

    public void setShipporders(List<EcmShipOrder> shipporders) {
        this.shipporders = shipporders;
    }
}
