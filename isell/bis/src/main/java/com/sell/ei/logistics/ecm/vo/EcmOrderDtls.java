package com.sell.ei.logistics.ecm.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sell.core.base.BaseInfo;

/**
 * SCM推送销售订单接口<br>
 * 订单详情
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class EcmOrderDtls extends BaseInfo {

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -4872446096049304216L;
    
    /** 订单详情 */
    @JsonProperty("OrderDtls")
    private List<EcmCommodity> orderDtls;

    public List<EcmCommodity> getOrderDtls() {
        return orderDtls;
    }

    public void setOrderDtls(List<EcmCommodity> orderDtls) {
        this.orderDtls = orderDtls;
    }
}
