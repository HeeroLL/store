/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-4
 */
package com.zebone.btp.sso.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.btp.sso.pojo.SsoAccountInfo;
import com.zebone.btp.sso.pojo.SysAccountInfo;
import com.zebone.btp.sso.pojo.SysRegInfo;

/**
 * <一句话功能简述><功能详细描述>
 *
 * @author lilin
 * @version [版本号, 2013-1-4]
 */
public class SsoMapperTest
{
    private SysRegMapper sysRegMapper;

    private SsoAccountMapper ssoAccountMapper;

    private SysAccountMapper sysAccountMapper;

    /**
     * 得到spring 上下文
     */
    @Before
    public void setUp()
        throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        sysRegMapper = context.getBean("sysRegMapper", SysRegMapper.class);
        ssoAccountMapper = context.getBean("ssoAccountMapper", SsoAccountMapper.class);
        sysAccountMapper = context.getBean("sysAccountMapper", SysAccountMapper.class);
    }

    @Test
    public void testSaveSysRegInfo()
    {
        SysRegInfo sysRegInfo = new SysRegInfo();
        sysRegInfo.setRegId("1");
        sysRegInfo.setSysId("btp");
        sysRegInfo.setSysName("BTP");
        sysRegInfo.setSysUrl("http://localhost:8080");
        sysRegInfo.setSysLogoutUrl("http://localhost:8080/logout");
        sysRegInfo.setSysImg("image");
        sysRegInfo.setSysDescription("miaoshu");

        sysRegMapper.saveSysRegInfo(sysRegInfo);
    }

    @Test
    public void testSaveSsoAccountInfo()
    {
        SsoAccountInfo ssoAccountInfo = new SsoAccountInfo();
        ssoAccountInfo.setAccountId("12");
        ssoAccountInfo.setAccount("zhangsan");
        ssoAccountInfo.setPassword("111111");
        ssoAccountInfo.setDescription("miaoshu");
        ssoAccountInfo.setEmail("11@qq.com");
        ssoAccountInfo.setMobile("13312345678");

        ssoAccountMapper.saveSsoAccountInfo(ssoAccountInfo);
    }

    @Test
    public void testSaveSysAccountInfo()
    {
        SysAccountInfo sysAccountInfo = new SysAccountInfo();
        sysAccountInfo.setAccountId("11");
        sysAccountInfo.setAccount("zhangsan");
        sysAccountInfo.setPassword("111111");
        sysAccountInfo.setSsoAccountId("1");
        sysAccountInfo.setSysId("btp");

        sysAccountMapper.saveSysAccountInfo(sysAccountInfo);
    }

    @Test
    public void testUpdateSysRegInfo()
    {
        SysRegInfo sysRegInfo = sysRegMapper.findSysRegInfoById("1");
        Assert.assertTrue(sysRegInfo != null);

        //sysRegInfo.setRegId("1");
        sysRegInfo.setSysId("btp2");
        sysRegInfo.setSysName("BTP2");
        sysRegInfo.setSysUrl("http://localhost:8081");
        sysRegInfo.setSysLogoutUrl("http://localhost:8081/logout");
        sysRegInfo.setSysImg("image2");
        sysRegInfo.setSysDescription("miaoshu2");

        sysRegMapper.updateSysRegInfo(sysRegInfo);
    }

    @Test
    public void testUpdateSsoAccountInfo()
    {
        SsoAccountInfo ssoAccountInfo = ssoAccountMapper.findSsoAccountInfoById("1");
        Assert.assertTrue(ssoAccountInfo != null);

        // ssoAccountInfo.setAccountId("1");
        ssoAccountInfo.setAccount("zhangsan2");
        ssoAccountInfo.setPassword("000000");
        ssoAccountInfo.setDescription("miaoshu2");
        ssoAccountInfo.setEmail("22@qq.com");
        ssoAccountInfo.setMobile("15912345678");

        ssoAccountMapper.updateSsoAccountInfo(ssoAccountInfo);
    }

    @Test
    public void testUpdateSysAccountInfo()
    {
        SysAccountInfo sysAccountInfo = sysAccountMapper.findSysAccountInfoById("11");
        Assert.assertTrue(sysAccountInfo != null);

        //sysAccountInfo.setAccountId("11");
        sysAccountInfo.setAccount("zhangsan2");
        sysAccountInfo.setPassword("000000");
        sysAccountInfo.setSsoAccountId("1");
        sysAccountInfo.setSysId("btp");

        sysAccountMapper.updateSysAccountInfo(sysAccountInfo);
    }

    @Test
    public void testUpdateSsoAccountId()
    {
        sysAccountMapper.updateSsoAccountId("12", "11");
    }

    @Test
    public void testDeleteSysAccountInfo()
    {
        sysAccountMapper.deleteSysAccountInfo("11");
    }

    @Test
    public void testDeleteSsoAccountInfo()
    {
        ssoAccountMapper.deleteSsoAccountInfo("1");
    }

    @Test
    public void testDeleteSysRegInfo()
    {
        sysRegMapper.deleteSysRegInfo("1");
    }

    @Test
    public void testGetSysAccountInfoList()
    {
        System.out.println(sysRegMapper.getSysAccountInfoList("1"));
    }

    @Test
    public void testGetSysAccountInfo()
    {
        System.out.println(sysAccountMapper.getSysAccountInfo("1", "btp"));
    }
}
