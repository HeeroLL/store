package com.isell.ei.logistics.zhongtong.service;

import java.util.Map;

import com.isell.ei.logistics.zhongtong.bean.ZTOrderResponse;

/**
 * 中通物流服务接口
 * 
 * @author lilin
 * @version [版本号, 2015年10月23日]
 */
public interface ZhongtongService {
    /**
     * 商家账号
     */
    String COMPANY_ID = "HECBSCCL";
    
    /**
     * 商家密钥
     */
    String KEY = "k3Nzc0QkQxRDgzNkQzNTA=";
    
    /**
     * 请求地址
     */
    String GET_BIGPEN_URL = "http://japi.zto.cn/zto/api_utf8/mark";
    
    /**
     * 获取大头笔内容
     * 
     * @param param 
     * @return 中通返回信息
     */
    ZTOrderResponse getMarkService(Map<String, String> param);
}
