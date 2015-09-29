package com.isell.ei.logistics.ecm.vo;

import java.util.List;

import com.isell.core.base.BaseInfo;

/**
 * ECM订单生产状态接口-订单参数
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
public class EcmOrderStatusListInfo extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -8110369640907473582L;
    
    /**
     * 订单编号
     */
    private String orderCode;
    
    /**
     * 快递公司
     */
    private String carrier;
    
    /**
     * 运单号
     */
    private String logisticsNO;
    
    /**
     * 状态列表
     */
    private List<EcmOrderStatus> status;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getLogisticsNO() {
        return logisticsNO;
    }

    public void setLogisticsNO(String logisticsNO) {
        this.logisticsNO = logisticsNO;
    }

    public List<EcmOrderStatus> getStatus() {
        return status;
    }

    public void setStatus(List<EcmOrderStatus> status) {
        this.status = status;
    }
}
