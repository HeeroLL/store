package com.zebone.store;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 存储通知接口
 *
 * @author 杨英
 * @version 2013-10-23 上午09:11
 */
@WebService
public interface StoreNotice {
    /**
     * 根据传入的参数将相应文档的注册状态改为"重新注册"
     * @param docNo 文档编号
     * @return 结果大于"0"表示更改状态成功
     */
    int DocumentRepository_RegisterStateUpdate(@WebParam(name="docNo")String docNo);
}
