/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-7       用户名密码生成/获取工具
 */
package com.zebone.btp.sso.util;

/**
 * 用户名密码生成/获取工具
 *
 * @author lilin
 * @version [版本号, 2013-1-7]
 */
public final class UserNamePasswordUtil
{
    /**
     * 用户名密码之间的分割符
     */
    private static final String SEPARATOR = "|";

    /**
     * 私有化构造方法避免实例化
     */
    private UserNamePasswordUtil()
    {

    }

    /**
     * 根据用户名密码生成加密票据
     *
     * @param userName 用户名
     * @param password 密码
     * @return 加密票据
     */
    public static String generateTicket(String userName, String password)
    {
        return AesUtil.encrypt(userName + SEPARATOR + password);
    }

    /**
     * 根据ticket获取用户名
     *
     * @param ticket 票据
     * @return 用户名
     */
    public static String getUserName(String ticket)
    {
        return AesUtil.decrypt(ticket).split("\\" + SEPARATOR)[0];
    }

    /**
     * 根据ticket获取密码
     *
     * @param ticket 票据
     * @return 密码
     */
    public static String getPassword(String ticket)
    {
        return AesUtil.decrypt(ticket).split("\\" + SEPARATOR)[1];
    }
}
