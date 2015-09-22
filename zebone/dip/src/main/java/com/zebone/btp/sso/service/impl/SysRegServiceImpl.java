/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-5       系统注册Service实现类
 */
package com.zebone.btp.sso.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.sso.dao.SysRegMapper;
import com.zebone.btp.sso.pojo.SysRegInfo;
import com.zebone.btp.sso.service.SysRegService;

/**
 * 系统注册Service实现类
 *
 * @author lilin
 * @version [版本号, 2013-1-5]
 */
@Service("sysRegService")
public class SysRegServiceImpl implements SysRegService
{
    /**
     * 系统注册Mapper
     */
    @Autowired
    private SysRegMapper sysRegMapper;

    /**
     * 删除系统
     *
     * @param regIds 系统id集合
     */
    @Override
    public void deleteSysRegInfo(String regIds)
    {
        sysRegMapper.deleteSysRegInfo(regIds);
    }

    /**
     * 根据系统id查询系统信息
     *
     * @param regId 系统id
     * @return 系统信息
     */
    @Override
    public SysRegInfo findSysRegInfoById(String regId)
    {
        return sysRegMapper.findSysRegInfoById(regId);
    }

    /**
     * 保存系统
     *
     * @param sysRegInfo 系统信息
     */
    @Override
    public void saveSysRegInfo(SysRegInfo sysRegInfo)
    {
        sysRegMapper.saveSysRegInfo(sysRegInfo);
    }

    /**
     * 分页查询系统信息
     *
     * @param rowBounds 分页对象
     * @param sysRegInfo 查询条件
     * @return list
     */
    @Override
    public Pagination<SysRegInfo> searchSysRegInfo(RowBounds rowBounds, SysRegInfo sysRegInfo)
    {
        // 声明分页对象
        Pagination<SysRegInfo> page = new Pagination<SysRegInfo>();
        List<SysRegInfo> sysRegInfoList = sysRegMapper.searchSysRegInfo(rowBounds, sysRegInfo);
        page.setData(sysRegInfoList);
        page.setTotalCount(sysRegMapper.getTotalCount(sysRegInfo));

        return page;
    }

    /**
     * 更新系统
     *
     * @param sysRegInfo 系统信息
     */
    @Override
    public void updateSysRegInfo(SysRegInfo sysRegInfo)
    {
        SysRegInfo sysRegInfoDb = sysRegMapper.findSysRegInfoById(sysRegInfo.getRegId());

        sysRegInfoDb.setSysId(sysRegInfo.getSysId());
        sysRegInfoDb.setSysName(sysRegInfo.getSysName());
        sysRegInfoDb.setSysUrl(sysRegInfo.getSysUrl());
        sysRegInfoDb.setSysLogoutUrl(sysRegInfo.getSysLogoutUrl());
        sysRegInfoDb.setSysImg(sysRegInfo.getSysImg());
        sysRegInfoDb.setSysDescription(sysRegInfo.getSysDescription());

        sysRegMapper.updateSysRegInfo(sysRegInfoDb);
    }

}
