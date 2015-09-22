package com.zebone.dip.ws.cleardata.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 清理文档相关数据服务
 * @author 杨英
 * @version 2014-7-10 上午09:15
 */
@WebService
public interface ClearDataService {

    public String clearData(@WebParam(name="requestPara") String requestParam);
}
