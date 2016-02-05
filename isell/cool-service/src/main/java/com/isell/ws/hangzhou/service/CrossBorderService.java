package com.isell.ws.hangzhou.service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/**
 * 海关信息交互接口定义
 * @author 一代魔笛
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface CrossBorderService {

}
