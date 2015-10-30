package com.isell.ws.zhengzhou.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * 订单回执webservice
 * 
 * @author lilin
 * @version [版本号, 2015年10月23日]
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ZzWebService {
    /**
     * 订单回执
     * 
     * @param xml xml数据
     * @return 返回结果
     */
    @WebMethod
    String orderBack(String xml);
}
