/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-26
 */
package com.zebone.btp.core.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * UT
 *
 * @author  lilin
 * @version  [版本号, 2012-11-26]
 */
public class MessageUtilTest
{
    /**
     * 得到spring 上下文
     */
    @Before
    public void setUp()
    {
        new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }

    @Test
    public void testGetMessage()
    {
        Assert.assertTrue("只是一个测试".equals(MessageUtil.getMessage("log.test")));
    }
}
