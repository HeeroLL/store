package com.zebone.dnode.etl;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zebone.dnode.etl.check.pojo.CheckConfig;
import com.zebone.dnode.etl.convert.pojo.ConvertConfig;
import com.zebone.dnode.etl.load.pojo.LoadConfig;

@Configuration
public class App {
    
    @Bean
	public CheckConfig checkConfig(){
		CheckConfig checkConfig = null;
		try {
			ClassPathResource checkConf = new ClassPathResource("com/zebone/dnode/etl/check/config/checkConf.xml");
			XStream xs = new XStream(new StaxDriver());
			xs.processAnnotations(CheckConfig.class);
			checkConfig = (CheckConfig) xs.fromXML(checkConf.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkConfig;
	}
    
    
    @Bean
    public ConvertConfig convertConfig(){
    	ConvertConfig convertConfig = null;
        try {
			ClassPathResource convertConf = new ClassPathResource("com/zebone/dnode/etl/convert/config/convertConf.xml");
			XStream xs = new XStream(new StaxDriver());
			xs.processAnnotations(ConvertConfig.class);
			convertConfig = (ConvertConfig) xs.fromXML(convertConf.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return convertConfig;
    }
    
    @Bean
    public LoadConfig loadConfig(){
    	LoadConfig loadData = null;
    	try {
			ClassPathResource loadConfCpr = new ClassPathResource("com/zebone/dnode/etl/load/config/loadConf.xml");
			XStream xs = new XStream(new StaxDriver());
			xs.processAnnotations(LoadConfig.class);
			loadData = (LoadConfig) xs.fromXML(loadConfCpr.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return loadData;
    }
}
