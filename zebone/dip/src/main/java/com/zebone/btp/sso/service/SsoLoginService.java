/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-5       单点登录账号登录service
 */
package com.zebone.btp.sso.service;

import java.util.List;

import com.zebone.btp.sso.pojo.SsoAccountInfo;
import com.zebone.btp.sso.pojo.SysAccountInfo;
import com.zebone.btp.sso.pojo.SysRegInfo;

/**
 * 单点登录账号登录service
 *
 * @author lilin
 * @version [版本号, 2013-1-5]
 */
public interface SsoLoginService
{
    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录成功返回用户信息，否则返回null
     */
    SsoAccountInfo login(String userName, String password);

    /**
     * 根据单点登录账号id，获取该账户所有的子系统信息列表
     *
     * @param ssoAccountId 单点登录账号id
     * @return 子系统信息列表
     */
    List<SysRegInfo> getSysAccountInfoList(String ssoAccountId);

    /**
     * 根据单点登录账户id和子系统标识查询子系统账户信息
     *
     * @param ssoAccountId 单点登录账户id
     * @param sysId 子系统标识
     * @return 子系统账户信息
     */
    SysAccountInfo getSysAccountInfo(String ssoAccountId, String sysId);
}
