package com.sell.ei.logistics.ecm.vo;

import java.util.Date;
import java.util.List;

import com.sell.core.base.BaseInfo;

/**
 * ECM订单批量发货接口-订单参数
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
public class EcmShipOrder extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7827497655745760239L;
    
    /**
     * 订单编号
     */
    private String orderCode;
    
    /**
     * 快递公司编号
     */
    private String companyNo;
    
    /**
     * 快递运单号
     */
    private String expressNo;
    
    /**
     * 发货时间
     */
    private Date shippingTime;
    
    /**
     * 包裹实际重量
     */
    private Double realWeight;
    
    /**
     * 商品列表
     */
    private List<EcmCommodity> details;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Double getRealWeight() {
        return realWeight;
    }

    public void setRealWeight(Double realWeight) {
        this.realWeight = realWeight;
    }

    public List<EcmCommodity> getDetails() {
        return details;
    }

    public void setDetails(List<EcmCommodity> details) {
        this.details = details;
    }
}
