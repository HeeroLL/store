/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-4       系统注册Service
 */
package com.zebone.btp.sso.service;

import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.sso.pojo.SysRegInfo;

/**
 * 系统注册Service
 *
 * @author lilin
 * @version [版本号, 2013-1-4]
 */
public interface SysRegService
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
     * @param regIds 系统id集合
     */
    void deleteSysRegInfo(String regIds);

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
    Pagination<SysRegInfo> searchSysRegInfo(RowBounds rowBounds, SysRegInfo sysRegInfo);
}
