package com.isell.ei.logistics.sf.service;

import com.isell.ei.logistics.sf.vo.SOrder;
import com.isell.ei.logistics.sf.vo.SOrderSearch;
import com.isell.ei.logistics.sf.vo.SResponse;
import com.isell.ei.logistics.sf.vo.SRouteRequest;

/**
 * 
 * 顺丰物流接口封装业务层
 * 
 * @author lilin
 * @version [版本号, 2015年7月4日]
 * 
 */
public interface SFService {
    /**
     * 开发环境Checkword：j8DzkIFgmlomPt0aLuwU
     */
    String CHECKWORD = "j8DzkIFgmlomPt0aLuwU";
    
    /**
     * 开发环境接入编码：BSPdevelop
     */
    String HEAD = "BSPdevelop";
    
    /**
     * 顺丰统一URL
     */
    String URL = "https://bsp-oisp.test.sf-express.com/bsp-oisp/sfexpressService";
    
    /**
     * 下订单接口
     * 
     * @param order 订单信息
     * @return 响应信息
     */
    SResponse orderService(SOrder order);
    
    /**
     * 路由查询接口
     * 
     * @param routeRequest 路由查询参数
     * @return 响应信息
     */
    SResponse routeService(SRouteRequest routeRequest);
    
    /**
     * 订单结果查询接口
     *
     * @param orderSearch 订单查询参数
     * @return 响应信息
     */
    SResponse orderSearchService(SOrderSearch orderSearch);
}
