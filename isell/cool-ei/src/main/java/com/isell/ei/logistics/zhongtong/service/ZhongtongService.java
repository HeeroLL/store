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
    String COMPANY_ID = "aaabbbccc";
    
    /**
     * 商家密钥
     */
    String KEY = "asdasdsadsadasdasd";
    
    /**
     * 请求地址
     */
    String REQUEST_URL = "http://116.228.70.118:9001/zto/api_utf8/importController";
    
    /**
     * 获取大头笔内容
     * 
     * @param param 
     * @return 中通返回信息
     */
    ZTOrderResponse getMarkService(Map<String, String> param);
}
