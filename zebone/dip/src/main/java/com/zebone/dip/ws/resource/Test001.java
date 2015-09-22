package com.zebone.dip.ws.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.check.CheckConfig;
import com.zebone.check.CheckConfig.Check;
import com.zebone.dip.ws.resource.pojo.UserRequest;

public class Test001 {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2014-4-2 上午10:05:04 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStream checkInputStream =  new ClassPathResource("com/zebone/dip/ws/resource/xsd/resourceDeptCheck.xml").getInputStream();
			 XStream xs = new XStream(new DomDriver());
			 xs.processAnnotations(CheckConfig.class);
			 xs.processAnnotations(UserRequest.class);
			CheckConfig checkConfig = (CheckConfig)xs.fromXML(checkInputStream);
			//checkConfig.setPath(null);
			List<Check> checkList = checkConfig.getCheckList();
			Check check = checkList.get(0);
			check.setFieldName(null);
			System.out.println(xs.toXML(checkConfig));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
