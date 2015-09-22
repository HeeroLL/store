/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-22
 */
package com.zebone.btp.log.dao;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.log.dao.LogMapper;
import com.zebone.btp.log.pojo.AuditLogInfo;

/**
 * UT
 *
 * @author lilin
 * @version [版本号, 2012-11-22]
 */
public class AuditLogMapperTest
{
    private ApplicationContext context;

    private LogMapper mapper;

    /**
     * 得到spring 上下文
     */
    @Before
    public void setUp()
    {
        context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        setUpService();
    }

    /**
     * 装载需要测试的类
     */
    public void setUpService()
    {
        try
        {
            mapper = context.getBean("auditLogMapper", LogMapper.class);
        }
        catch (BeansException e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link com.zebone.btp.log.dao.LogMapper#saveLog(com.zebone.btp.log.pojo.AuditLogInfo)}.
     */
    //@Test
    public void testSaveLog()
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

        mapper.saveLog(auditLogInfo);
    }

//    private void setLogInfoExt(List<AuditLogInfoExt> auditLogInfoExtList)
//    {
//        for (int i = 1; i<5; i++)
//        {
//            AuditLogInfoExt auditLogInfoExt = new AuditLogInfoExt();
//            auditLogInfoExt.setName("name" + i);
//            auditLogInfoExt.setValue("value" + i);
//            auditLogInfoExt.setOldValue("oldValue" + i);
//
//            auditLogInfoExtList.add(auditLogInfoExt);
//        }
//    }

    /**
     * Test method for {@link com.zebone.btp.log.dao.LogMapper#deleteLogById(java.lang.String)}.
     */
    @Test
    public void testDeleteLogById()
    {
        //fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.zebone.btp.log.dao.LogMapper#findLogById(java.lang.String)}.
     */
    @Test
    public void testFindLogById()
    {
        System.out.println(mapper.findLogById("90F3E93722D245CD8FE4C7A6E0C42863"));
    }

    @Test
    public void testSearchLog()
    {
        mapper.searchLog(new Pagination<AuditLogInfo>().getRowBounds(), new AuditLogInfo());
    }

    @Test
    public void testGetTotalCount()
    {
        System.out.println(mapper.getTotalCount(new AuditLogInfo()));
    }
}
