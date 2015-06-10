package com.zebone.docSub;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 文档订阅服务
 * @author 杨英
 * @version 2014-8-12 上午10:25
 */
@WebService
public interface DocSubService {

    /**
     * 根据传入参数获取文档
     * @param xml
     * @return
     */
    String docSub(@WebParam(name = "xml") String xml);
}
