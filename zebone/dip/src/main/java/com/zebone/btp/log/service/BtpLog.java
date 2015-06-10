/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-22     BTP日志接口
 */
package com.zebone.btp.log.service;

/**
 * BTP日志接口，目前只用作记录审计日志
 *
 * @author lilin
 * @version [版本号, 2012-11-22]
 */
public interface BtpLog
{
    /**
     * 记录操作日志(修改的情况)
     *
     * @param id 一种操作的唯一标识，在常量中定义
     * @param serviceId 业务id
     * @param newObj 存放新值的对象
     * @param oldObj 存放旧值的对象
     */
    void log(String id, String serviceId, Object newObj, Object oldObj);

    /**
     * 记录操作日志(新增、删除的情况)
     *
     * @param id 一种操作的唯一标识，在常量中定义
     * @param serviceId 业务id
     * @param newObj 存放新值的对象
     */
    void log(String id, String serviceId, Object newObj);

    /**
     * 记录操作日志(只需记录是什么操作的情况)
     *
     * @param id 一种操作的唯一标识，在常量中定义
     */
    void log(String id);

}
