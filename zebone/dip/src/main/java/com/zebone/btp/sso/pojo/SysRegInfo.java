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
public class SysRegInfo
{
    /**
     * 主键id
     */
    private String regId;

    /**
     * 系统标识
     */
    private String sysId;

    /**
     * 系统名称
     */
    private String sysName;

    /**
     * 系统链接地址
     */
    private String sysUrl;

    /**
     * 系统注销地址
     */
    private String sysLogoutUrl;

    /**
     * 系统图片路径
     */
    private String sysImg;

    /**
     * 系统描述
     */
    private String sysDescription;

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

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }

    public String getSysId()
    {
        return sysId;
    }

    public void setSysId(String sysId)
    {
        this.sysId = sysId;
    }

    public String getSysName()
    {
        return sysName;
    }

    public void setSysName(String sysName)
    {
        this.sysName = sysName;
    }

    public String getSysUrl()
    {
        return sysUrl;
    }

    public void setSysUrl(String sysUrl)
    {
        this.sysUrl = sysUrl;
    }

    public String getSysImg()
    {
        return sysImg;
    }

    public void setSysImg(String sysImg)
    {
        this.sysImg = sysImg;
    }

    public String getSysDescription()
    {
        return sysDescription;
    }

    public void setSysDescription(String sysDescription)
    {
        this.sysDescription = sysDescription;
    }

    public String getSysLogoutUrl()
    {
        return sysLogoutUrl;
    }

    public void setSysLogoutUrl(String sysLogoutUrl)
    {
        this.sysLogoutUrl = sysLogoutUrl;
    }
}
