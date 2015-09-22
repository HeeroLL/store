/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-4       子系统账户Mapper
 */
package com.zebone.btp.sso.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.sso.pojo.SysAccountInfo;

/**
 * 子系统账户Mapper
 *
 * @author lilin
 * @version [版本号, 2013-1-4]
 */
@Mapper
public interface SysAccountMapper
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
     * 根据单点登录账户id和子系统标识查询子系统账户信息
     *
     * @param ssoAccountId 单点登录账户id
     * @param sysId 子系统标识
     * @return 子系统账户信息
     */
    SysAccountInfo getSysAccountInfo(String ssoAccountId, String sysId);

    /**
     * 分页查询子系统账户信息
     *
     * @param rowBounds 分页对象
     * @param sysAccountInfo 查询条件
     * @return list
     */
    List<SysAccountInfo> searchSysAccountInfo(RowBounds rowBounds, SysAccountInfo sysAccountInfo);

    /**
     * 获得记录总数
     *
     * @param sysAccountInfo 查询条件
     * @return count
     */
    int getTotalCount(SysAccountInfo sysAccountInfo);

    /**
     * 批量更新系统账户的认证账户id
     *
     * @param ssoAccountId 认证账户id(sso账户id)
     * @param accountId 系统账户id
     */
    void updateSsoAccountId(String ssoAccountId, String accountId);
}
