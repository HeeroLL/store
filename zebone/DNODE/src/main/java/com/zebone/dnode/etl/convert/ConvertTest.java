package com.zebone.dnode.etl.convert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zebone.dnode.etl.convert.pojo.ConvertConfig;
import com.zebone.dnode.etl.convert.service.ConvertService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-21
 * Time: 下午1:27
 * To change this template use File | Settings | File Templates.
 */
public class ConvertTest {

    public static void main(String[] args) {
        System.out.println("------执行数据清洗转换------");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:com/zebone/dnode/etl/convert/config/ApplicationContext.xml");
        ClassPathResource convertConf = new ClassPathResource("com/zebone/dnode/etl/convert/config/convertConf.xml");
        XStream xs = new XStream(new StaxDriver());
        xs.processAnnotations(ConvertConfig.class);
        ConvertConfig convertConfig = null;
        try {
            convertConfig = (ConvertConfig) xs.fromXML(convertConf.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String,String> oMap = new HashMap<String,String>();
        oMap.put("orgCode","100");
        oMap.put("startTime","2014/01/05");
        oMap.put("endTime","2014/03/05");

        ConvertService convertService = ctx.getBean("convertService", ConvertService.class);
        convertService.convertData(convertConfig,oMap);
    }
}
