/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-4       单点登录系统账户Mapper
 */
package com.zebone.btp.sso.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.sso.pojo.SsoAccountInfo;

/**
 * 单点登录系统账户Mapper
 *
 * @author lilin
 * @version [版本号, 2013-1-4]
 */
@Mapper
public interface SsoAccountMapper
{
    /**
     * 保存单点登录系统账户
     *
     * @param ssoAccountInfo 单点登录系统账户信息
     */
    void saveSsoAccountInfo(SsoAccountInfo ssoAccountInfo);

    /**
     * 更新单点登录系统账户
     *
     * @param ssoAccountInfo 单点登录系统账户信息
     */
    void updateSsoAccountInfo(SsoAccountInfo ssoAccountInfo);

    /**
     * 删除单点登录系统账户
     *
     * @param accountId 单点登录系统账户id
     */
    void deleteSsoAccountInfo(String accountId);

    /**
     * 根据单点登录系统账户id查询单点登录系统账户信息
     *
     * @param accountId 单点登录系统账户id
     * @return 单点登录系统账户信息
     */
    SsoAccountInfo findSsoAccountInfoById(String accountId);

    /**
     * 根据单点登录系统账户查询单点登录系统账户信息
     *
     * @param account 单点登录系统账户
     * @return 单点登录系统账户信息
     */
    SsoAccountInfo getSsoAccountInfoByAccount(String account);

    /**
     * 分页查询单点登录系统账户信息
     *
     * @param rowBounds 分页对象
     * @param ssoAccountInfo 查询条件
     * @return list
     */
    List<SsoAccountInfo> searchSsoAccountInfo(RowBounds rowBounds, SsoAccountInfo ssoAccountInfo);

    /**
     * 获得记录总数
     *
     * @param ssoAccountInfo 查询条件
     * @return count
     */
    int getTotalCount(SsoAccountInfo ssoAccountInfo);
}
