/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-5       单点登录系统Service实现类
 */
package com.zebone.btp.sso.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.sso.dao.SsoAccountMapper;
import com.zebone.btp.sso.pojo.SsoAccountInfo;
import com.zebone.btp.sso.service.SsoAccountService;

/**
 * 单点登录系统Service实现类
 *
 * @author lilin
 * @version [版本号, 2013-1-5]
 */
@Service("ssoAccountService")
public class SsoAccountServiceImpl implements SsoAccountService
{
    /**
     * 单点登录系统账户Mapper
     */
    @Autowired
    private SsoAccountMapper ssoAccountMapper;

    /**
     * 删除单点登录系统账户
     *
     * @param accountId 单点登录系统账户id
     */
    @Override
    public void deleteSsoAccountInfo(String accountId)
    {
        ssoAccountMapper.deleteSsoAccountInfo(accountId);
    }

    /**
     * 根据单点登录系统账户id查询单点登录系统账户信息
     *
     * @param accountId 单点登录系统账户id
     * @return 单点登录系统账户信息
     */
    @Override
    public SsoAccountInfo findSsoAccountInfoById(String accountId)
    {
        return ssoAccountMapper.findSsoAccountInfoById(accountId);
    }

    /**
     * 保存单点登录系统账户
     *
     * @param ssoAccountInfo 单点登录系统账户信息
     */
    @Override
    public void saveSsoAccountInfo(SsoAccountInfo ssoAccountInfo)
    {
        ssoAccountMapper.saveSsoAccountInfo(ssoAccountInfo);
    }

    /**
     * 分页查询单点登录系统账户信息
     *
     * @param rowBounds 分页对象
     * @param ssoAccountInfo 查询条件
     * @return list
     */
    @Override
    public Pagination<SsoAccountInfo> searchSsoAccountInfo(RowBounds rowBounds, SsoAccountInfo ssoAccountInfo)
    {
        // 声明分页对象
        Pagination<SsoAccountInfo> page = new Pagination<SsoAccountInfo>();
        List<SsoAccountInfo> ssoAccountInfoList = ssoAccountMapper.searchSsoAccountInfo(rowBounds, ssoAccountInfo);
        page.setData(ssoAccountInfoList);
        page.setTotalCount(ssoAccountMapper.getTotalCount(ssoAccountInfo));

        return page;
    }

    /**
     * 更新单点登录系统账户
     *
     * @param ssoAccountInfo 单点登录系统账户信息
     */
    @Override
    public void updateSsoAccountInfo(SsoAccountInfo ssoAccountInfo)
    {
        SsoAccountInfo ssoAccountInfoDb = ssoAccountMapper.findSsoAccountInfoById(ssoAccountInfo.getAccountId());

        // ssoAccountInfoDb.setAccount(ssoAccountInfo.getAccount());
        ssoAccountInfoDb.setPassword(ssoAccountInfo.getPassword());
        ssoAccountInfoDb.setDescription(ssoAccountInfo.getDescription());
        ssoAccountInfoDb.setEmail(ssoAccountInfo.getEmail());
        ssoAccountInfoDb.setMobile(ssoAccountInfo.getMobile());

        ssoAccountMapper.updateSsoAccountInfo(ssoAccountInfoDb);
    }

}
