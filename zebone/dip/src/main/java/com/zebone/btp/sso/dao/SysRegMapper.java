/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-4       系统注册Mapper
 */
package com.zebone.btp.sso.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.btp.sso.pojo.SysRegInfo;

/**
 * 系统注册Mapper
 *
 * @author lilin
 * @version [版本号, 2013-1-4]
 */
@Mapper
public interface SysRegMapper
{
    /**
     * 保存系统
     *
     * @param sysRegInfo 系统信息
     */
    void saveSysRegInfo(SysRegInfo sysRegInfo);

    /**
     * 更新系统
     *
     * @param sysRegInfo 系统信息
     */
    void updateSysRegInfo(SysRegInfo sysRegInfo);

    /**
     * 删除系统
     *
     * @param regId 系统id
     */
    void deleteSysRegInfo(String regId);

    /**
     * 根据系统id查询系统信息
     *
     * @param regId 系统id
     * @return 系统信息
     */
    SysRegInfo findSysRegInfoById(String regId);

    /**
     * 分页查询系统信息
     *
     * @param rowBounds 分页对象
     * @param sysRegInfo 查询条件
     * @return list
     */
    List<SysRegInfo> searchSysRegInfo(RowBounds rowBounds, SysRegInfo sysRegInfo);

    /**
     * 获得记录总数
     *
     * @param sysRegInfo 查询条件
     * @return count
     */
    int getTotalCount(SysRegInfo sysRegInfo);

    /**
     * 根据单点登录账号id，获取该账户所有的子系统信息列表
     *
     * @param ssoAccountId 单点登录账号id
     * @return 子系统信息列表
     */
    List<SysRegInfo> getSysAccountInfoList(String ssoAccountId);
}
