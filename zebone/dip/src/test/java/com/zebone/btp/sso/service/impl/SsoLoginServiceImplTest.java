/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2013-1-6
 */
package com.zebone.btp.sso.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.btp.sso.service.SsoLoginService;

/**
 * <一句话功能简述><功能详细描述>
 *
 * @author  lilin
 * @version  [版本号, 2013-1-6]
 */
public class SsoLoginServiceImplTest
{
    private SsoLoginService ssoLoginService;

    /**
     * 得到spring 上下文
     */
    @Before
    public void setUp()
        throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ssoLoginService = context.getBean("ssoLoginService", SsoLoginService.class);
    }

    /**
     * Test method for {@link com.zebone.btp.sso.service.impl.SsoLoginServiceImpl#getSysAccountInfoList(java.lang.String)}.
     */
    @Test
    public void testGetSysAccountInfoList()
    {
        System.out.println(ssoLoginService.getSysAccountInfoList("1"));
    }

    /**
     * Test method for {@link com.zebone.btp.sso.service.impl.SsoLoginServiceImpl#login(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testLogin()
    {
        System.out.println("login user = " + ssoLoginService.login("zhangsan", "111111"));
    }

}
