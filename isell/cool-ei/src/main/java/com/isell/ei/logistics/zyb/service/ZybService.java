package com.isell.ei.logistics.zyb.service;

import com.isell.ei.logistics.zyb.bean.ZybResult;

/**
 * 转运帮接口
 * 
 * @author lilin
 * @version [版本号, 2015年10月27日]
 */
public interface ZybService {
    /**
     * 用户标识
     */
    String USER = "THKJ";
    
    /**
     * 密钥
     */
    String SECRET_KEY = "gHLAS2WC6k2rD7bqPiCa14M8k7HVRV3W";
    
    /**
     * domain
     */
    String URL = "http://api.dev.zhuanyunbang.com";// http://api.zhuanyunbang.com
    
    /**
     * 状态查询
     */
    String QUERY = URL + "/open/zyb/query";
    
    /**
     * 状态查询
     *
     * @param content 参数
     * @return 状态数据
     */
    ZybResult queryStatus(String content);
}
