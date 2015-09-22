package com.zebone.empi.webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.zebone.register.RegisterNotice;


/**
 * 测试杨英接口
 * @author YinCM
 *
 */
public class WebServiceTest {
	@Test
	public void testWS(){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://localhost:8080/EMPI/ws/registerOrUpdate?wsdl");
		factory.setServiceClass(RegisterNotice.class);
		RegisterNotice echoService = (RegisterNotice)factory.create();
		String empiXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><empi_info><name>殷昌明</name><card_list>";
		String empiXML1 = "<card><no>522723199002134414</no><type>1</type><org>A1</org></card></card_list></empi_info>";
		
		String str="";
		try {
			str = echoService.DocumentRegistryNotice(empiXML+empiXML1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(str);
		//String str = echoService.searchEmpiId(empiXML+empiXML1);
		//echoService.registerOrUpdate("aa");
		//System.out.println(str);
		
	}
}
