package com.isell.ei.logistics.ecm.vo;

import java.util.List;

import com.isell.core.base.BaseInfo;

/**
 * ECM订单生产状态接口参数
 * 
 * @author lilin
 * @version [版本号, 2015年7月24日]
 */
public class EcmOrderStatuses extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4869584339405636088L;
    
    /**
     * 状态列表
     */
    private List<EcmOrderStatusListInfo> statusList;

    public List<EcmOrderStatusListInfo> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<EcmOrderStatusListInfo> statusList) {
        this.statusList = statusList;
    } 
}
