/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-26     日志配置初始化信息
 */
package com.zebone.btp.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.zebone.btp.log.pojo.LogConfigInfo;

/**
 * 日志配置初始化信息
 *
 * @author lilin
 * @version [版本号, 2012-11-26]
 */
//@Configuration
public class LogContext implements ApplicationContextAware
{
    /**
     * 配置文件存放地址（纯注解只能硬编码）
     */
    private static final String LOG_FILE_PATH = "btplog.xml";

    /**
     * 存放配置信息的map
     */
    private Map<String, LogConfigInfo> classMap;

    /**
     * 标识是否初始化
     */
    private boolean isInit;

    /**
     * spring容器
     */
    private ApplicationContext context;

    /**
     * 初始化xml
     *
     * @throws IOException 抛出io异常
     * @throws DocumentException 初始化中抛出解析xml的异常
     */
    @PostConstruct
    public synchronized void initLogContext()
        throws IOException, DocumentException
    {
        // 如果已经初始化，则放弃再次初始化
        if (isInit)
        {
            return;
        }

        // 初始化map
        classMap = new HashMap<String, LogConfigInfo>();

        // 找到资源文件
        Resource resource = new ClassPathResource(LOG_FILE_PATH);
        SAXReader reader = new SAXReader();
        // 获得xml的根
        Document root = reader.read(resource.getInputStream());
        Element rootElement = root.getRootElement();

        // 循环class节点
        for (Object classNode : rootElement.elements("class"))
        {
            Element clazz = (Element)classNode;
            LogConfigInfo logConfigInfo = new LogConfigInfo();

            String id = clazz.attributeValue("id");

            String method = clazz.attributeValue("method");
            if (StringUtils.isNotEmpty(method))
            {
                id += '.' + method;
            }

            logConfigInfo.setId(id);
            logConfigInfo.setModelId(clazz.attributeValue("modelId"));
            logConfigInfo.setOpFlag(clazz.attributeValue("opflag"));

            String paramIndex = clazz.attributeValue("paramIndex");
            if (StringUtils.isNotEmpty(paramIndex) && StringUtils.isNumeric(paramIndex))
            {
                logConfigInfo.setParamIndex(Integer.parseInt(paramIndex));
            }
            logConfigInfo.setServiceId(clazz.attributeValue("serviceId"));
            logConfigInfo.setUpdateBeanId(clazz.attributeValue("updateBeanId"));
            logConfigInfo.setUpdateMethod(clazz.attributeValue("updateMethod"));

            logConfigInfo.setFieldList(new ArrayList<String>());

            // 循环field节点
            for (Object fieldNode : clazz.elements("field"))
            {
                Element field = (Element)fieldNode;
                logConfigInfo.getFieldList().add(field.attributeValue("name"));
            }
            classMap.put(logConfigInfo.getId(), logConfigInfo);
        }

        // 设置初始化成功
        isInit = true;
    }

    /**
     * 获取单个节点信息
     *
     * @return logConfigInfo
     */
    public LogConfigInfo getLogConfigInfo(String id)
    {
        return classMap.get(id);
    }

    /**
     * 获取spring的bean
     *
     * @param id beanId
     * @return obj
     */
    public Object getBean(String id)
    {
        return context.getBean(id);
    }

    /**
     * 获得spring容器
     *
     * @param context spring容器
     * @throws BeansException 抛出BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext context)
        throws BeansException
    {
        this.context = context;
    }
}
