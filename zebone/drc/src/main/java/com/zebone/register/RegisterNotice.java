package com.zebone.register;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 注册通知接口
 *
 * @author 杨英
 * @version 2013-10-23 上午08:55
 */
@WebService
public interface RegisterNotice {
    /**
     * 根据传入参数获取相应的文档编号和系统编码后通知存储中心更改文档注册状态
     * @param xml
     * @return "1" 表示已成功通知, "0"表示未成功通知
     */
    String DocumentRegistryNotice(@WebParam(name = "xml") String xml);
}
