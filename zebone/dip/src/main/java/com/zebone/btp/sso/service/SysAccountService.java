/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-4       子系统账户Service
 */
package com.zebone.btp.sso.service;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.sso.pojo.SysAccountInfo;

/**
 * 子系统账户Service
 *
 * @author lilin
 * @version [版本号, 2013-1-4]
 */
public interface SysAccountService
{
    /**
     * 保存子系统账户
     *
     * @param sysAccountInfo 子系统账户信息
     */
    void saveSysAccountInfo(SysAccountInfo sysAccountInfo);

    /**
     * 更新子系统账户
     *
     * @param sysAccountInfo 子系统账户信息
     */
    void updateSysAccountInfo(SysAccountInfo sysAccountInfo);

    /**
     * 删除子系统账户
     *
     * @param accountId 子系统账户id
     */
    void deleteSysAccountInfo(String accountId);

    /**
     * 根据子系统账户id查询子系统账户信息
     *
     * @param accountId 子系统账户id
     * @return 子系统账户信息
     */
    SysAccountInfo findSysAccountInfoById(String accountId);

    /**
     * 分页查询子系统账户信息
     *
     * @param rowBounds 分页对象
     * @param sysAccountInfo 查询条件
     * @return list
     */
    Pagination<SysAccountInfo> searchSysAccountInfo(RowBounds rowBounds, SysAccountInfo sysAccountInfo);

    /**
     * 批量更新系统账户的认证账户id
     *
     * @param ssoAccountId 认证账户id(sso账户id)
     * @param accountIds 系统账户id集合
     */
    void updateSsoAccountId(String ssoAccountId, String... accountIds);
}
