/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-4       系统注册表信息
 */
package com.zebone.btp.sso.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统注册表信息
 *
 * @author lilin
 * @version [版本号, 2013-1-4]
 */
public class SysAccountInfo
{
    /**
     * 主键id
     */
    private String accountId;

    /**
     * 用户名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 系统标识
     */
    private String sysId;

    /**
     * 认证系统账户ID
     */
    private String ssoAccountId;

    /**
     * 重写toString方法
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSysId()
    {
        return sysId;
    }

    public void setSysId(String sysId)
    {
        this.sysId = sysId;
    }

    public String getSsoAccountId()
    {
        return ssoAccountId;
    }

    public void setSsoAccountId(String ssoAccountId)
    {
        this.ssoAccountId = ssoAccountId;
    }
}
