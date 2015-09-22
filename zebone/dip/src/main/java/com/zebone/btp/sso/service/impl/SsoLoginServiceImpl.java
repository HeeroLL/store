/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-6       单点登录账号登录service实现类
 */
package com.zebone.btp.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.btp.sso.dao.SsoAccountMapper;
import com.zebone.btp.sso.dao.SysAccountMapper;
import com.zebone.btp.sso.dao.SysRegMapper;
import com.zebone.btp.sso.pojo.SsoAccountInfo;
import com.zebone.btp.sso.pojo.SysAccountInfo;
import com.zebone.btp.sso.pojo.SysRegInfo;
import com.zebone.btp.sso.service.SsoLoginService;

/**
 * 单点登录账号登录service实现类
 *
 * @author lilin
 * @version [版本号, 2013-1-6]
 */
@Service("ssoLoginService")
public class SsoLoginServiceImpl implements SsoLoginService
{
    /**
     * 系统注册Mapper
     */
    @Autowired
    private SysRegMapper sysRegMapper;

    /**
     * 单点登录系统账户Mapper
     */
    @Autowired
    private SsoAccountMapper ssoAccountMapper;

    /**
     * 子系统账户Mapper
     */
    @Autowired
    private SysAccountMapper sysAccountMapper;

    /**
     * 根据单点登录账号id，获取该账户所有的子系统信息列表
     *
     * @param ssoAccountId 单点登录账号id
     * @return 子系统信息列表
     */
    @Override
    public List<SysRegInfo> getSysAccountInfoList(String ssoAccountId)
    {
        return sysRegMapper.getSysAccountInfoList(ssoAccountId);
    }

    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录成功返回用户信息，否则返回null
     */
    @Override
    public SsoAccountInfo login(String userName, String password)
    {
        if (userName == null || password == null)
        {
            return null;
        }
        SsoAccountInfo ssoAccountInfo = ssoAccountMapper.getSsoAccountInfoByAccount(userName);

        if (ssoAccountInfo == null || !password.equals(ssoAccountInfo.getPassword()))
        {
            return null;
        }
        return ssoAccountInfo;
    }

    /**
     * 根据单点登录账户id和子系统标识查询子系统账户信息
     *
     * @param ssoAccountId 单点登录账户id
     * @param sysId 子系统标识
     * @return 子系统账户信息
     */
    @Override
    public SysAccountInfo getSysAccountInfo(String ssoAccountId, String sysId)
    {
        return sysAccountMapper.getSysAccountInfo(ssoAccountId, sysId);
    }
}
