/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-5       子系统账户Service实现类
 */
package com.zebone.btp.sso.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.sso.dao.SysAccountMapper;
import com.zebone.btp.sso.pojo.SysAccountInfo;
import com.zebone.btp.sso.service.SysAccountService;

/**
 * 子系统账户Service实现类
 *
 * @author lilin
 * @version [版本号, 2013-1-5]
 */
@Service("sysAccountService")
public class SysAccountServiceImpl implements SysAccountService
{
    /**
     * 子系统账户Mapper
     */
    @Autowired
    private SysAccountMapper sysAccountMapper;

    /**
     * 删除子系统账户
     *
     * @param accountId 子系统账户id
     */
    @Override
    public void deleteSysAccountInfo(String accountId)
    {
        sysAccountMapper.deleteSysAccountInfo(accountId);
    }

    /**
     * 根据子系统账户id查询子系统账户信息
     *
     * @param accountId 子系统账户id
     * @return 子系统账户信息
     */
    @Override
    public SysAccountInfo findSysAccountInfoById(String accountId)
    {
        return sysAccountMapper.findSysAccountInfoById(accountId);
    }

    /**
     * 保存子系统账户
     *
     * @param sysAccountInfo 子系统账户信息
     */
    @Override
    public void saveSysAccountInfo(SysAccountInfo sysAccountInfo)
    {
        sysAccountMapper.saveSysAccountInfo(sysAccountInfo);
    }

    /**
     * 分页查询子系统账户信息
     *
     * @param rowBounds 分页对象
     * @param sysAccountInfo 查询条件
     * @return list
     */
    @Override
    public Pagination<SysAccountInfo> searchSysAccountInfo(RowBounds rowBounds, SysAccountInfo sysAccountInfo)
    {
        // 声明分页对象
        Pagination<SysAccountInfo> page = new Pagination<SysAccountInfo>();
        List<SysAccountInfo> sysAccountInfoList = sysAccountMapper.searchSysAccountInfo(rowBounds, sysAccountInfo);
        page.setData(sysAccountInfoList);
        page.setTotalCount(sysAccountMapper.getTotalCount(sysAccountInfo));

        return page;
    }

    /**
     * 更新子系统账户
     *
     * @param sysAccountInfo 子系统账户信息
     */
    @Override
    public void updateSysAccountInfo(SysAccountInfo sysAccountInfo)
    {
        SysAccountInfo sysAccountInfoDb = new SysAccountInfo();
        sysAccountInfoDb.setPassword(sysAccountInfo.getPassword());
        sysAccountInfoDb.setSsoAccountId(sysAccountInfo.getAccountId());
        sysAccountInfoDb.setSysId(sysAccountInfo.getSysId());

        sysAccountMapper.updateSysAccountInfo(sysAccountInfoDb);
    }

    /**
     * 批量更新系统账户的认证账户id
     *
     * @param ssoAccountId 认证账户id(sso账户id)
     * @param accountIds 系统账户id集合
     */
    @Override
    public void updateSsoAccountId(String ssoAccountId, String... accountIds)
    {
        for (String accountId : accountIds)
        {
            sysAccountMapper.updateSsoAccountId(ssoAccountId, accountId);
        }
    }
}
