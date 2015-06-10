package com.zebone.empi.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 主索引审核服务接口
 *
 * @author 杨英
 * @version 2014-8-4 上午9:08
 */
@WebService
public interface AuditService {

    /**
     * 实名信息更新审核
     * @param paramXml
     * @return xml格式字符串
     */
    public String auditForUpdate(@WebParam(name="paramXml")String paramXml) throws Exception;
    
    
    
    /**
     * empi解绑 
     * @param paramXml
     * @return
     * @author 陈阵 
     * @date 2014-8-6 下午1:58:32
     */
    public String auditForunBind(@WebParam(name="paramXml")String paramXml);
}
