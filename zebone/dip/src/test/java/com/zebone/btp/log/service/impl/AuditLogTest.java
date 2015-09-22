/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-27
 */
package com.zebone.btp.log.service.impl;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.btp.log.pojo.AuditLogInfo;
import com.zebone.btp.log.service.BtpLog;

/**
 * <一句话功能简述><功能详细描述>
 *
 * @author lilin
 * @version [版本号, 2012-11-27]
 */
public class AuditLogTest
{
    private BtpLog btpLog;

    /**
     * 得到spring 上下文
     */
    @Before
    public void setUp()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        btpLog = context.getBean("btpLog", BtpLog.class);
    }

    /**
     * Test method for
     * {@link com.zebone.btp.log.service.impl.AuditLogServiceImpl#log(java.lang.String, java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testLogStringObjectObject()
    {
        AuditLogInfo oldAuditLogInfo = new AuditLogInfo();
        oldAuditLogInfo.setActorId("111");
        oldAuditLogInfo.setCreateTime(new Date());
        oldAuditLogInfo.setModelId("module1");
        oldAuditLogInfo.setServiceId("serviceId1");
        oldAuditLogInfo.setIpAddress("1.1.1.1");
        oldAuditLogInfo.setLevelCode("levelCode1");
        oldAuditLogInfo.setOpFlag("1");
        oldAuditLogInfo.setDescription("description1");

        AuditLogInfo newAuditLogInfo = new AuditLogInfo();
        newAuditLogInfo.setActorId("222");
        newAuditLogInfo.setCreateTime(new Date());
        newAuditLogInfo.setModelId("module2");
        newAuditLogInfo.setServiceId("serviceId2");
        newAuditLogInfo.setIpAddress("12.12.12.12");
        newAuditLogInfo.setLevelCode("levelCode2");
        newAuditLogInfo.setOpFlag("2");
        newAuditLogInfo.setDescription("description2");

        btpLog.log("10000001", "aaa", newAuditLogInfo, oldAuditLogInfo);
    }

    /**
     * Test method for
     * {@link com.zebone.btp.log.service.impl.AuditLogServiceImpl#log(java.lang.String, java.lang.Object)}.
     */
    @Test
    public void testLogStringObject()
    {
        AuditLogInfo auditLogInfo = new AuditLogInfo();
        auditLogInfo.setActorId("111");
        auditLogInfo.setCreateTime(new Date());
        auditLogInfo.setModelId("module1");
        auditLogInfo.setServiceId("serviceId1");
        auditLogInfo.setIpAddress("1.1.1.1");
        auditLogInfo.setLevelCode("levelCode1");
        auditLogInfo.setOpFlag("1");
        auditLogInfo.setDescription("description1");

        btpLog.log("10000001", "bbb", auditLogInfo);
    }

    /**
     * Test method for {@link com.zebone.btp.log.service.impl.AuditLogServiceImpl#log(java.lang.String)}.
     */
    @Test
    public void testLogString()
    {
        btpLog.log("10000001");
    }

}
