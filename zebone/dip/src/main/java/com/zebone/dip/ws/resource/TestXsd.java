package com.zebone.dip.ws.resource;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import com.zebone.check.CheckResult;
import com.zebone.check.component.SchemaCheck;

public class TestXsd {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2014-4-14 上午9:21:47 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStream checkDetpInputStream =  new ClassPathResource("com/zebone/dip/ws/resource/xsd/resourceDept.xsd").getInputStream();
		    SchemaCheck sc = new SchemaCheck();
		    String checkDeptXml = "<request><head><requestId>A10001</requestId><tradeNo>KSZY_1</tradeNo><orgCode>00254555733040090P9399</orgCode>"+
"<systemCode></systemCode></head><body><item><deptName>测试科室110</deptName><deptDesc>sdf</deptDesc><orgCode>75303716633048111X1009</orgCode><deptCode></deptCode><authorizationCode>A0001201</authorizationCode>"+
"</item></body></request>";
		   CheckResult deptResult  = sc.check(checkDeptXml, checkDetpInputStream);
		   System.out.println(deptResult.getErrorMessage());
		   
		   System.out.println("#########################################################################################################");
		   InputStream checkFamilyInputStream =  new ClassPathResource("com/zebone/dip/ws/resource/xsd/resourceFamily.xsd").getInputStream();
		   String checkFamilyXml ="<request>" +
				"<head><requestId>A10001</requestId><tradeNo>JTDA_1</tradeNo><orgCode>00254555733040090P9399</orgCode><authorizationCode>A0001201</authorizationCode><systemCode></systemCode></head>" +
				"<body>" +
				"<item>" +
				"<famiCode>00000000000000000000000000000000</famiCode><landlordTEL>12345678912</landlordTEL><householdName>户主</householdName><housePopuNumber>3</housePopuNumber><landlordName>测试房东110</landlordName><residentPopuNumber>3</residentPopuNumber><liveType>1</liveType><totalArea>98.5</totalArea><perArea>33.5</perArea><familyTEL>12345678912</familyTEL><paperFileNo>123456789</paperFileNo><housingPropertyCode>1</housingPropertyCode><housingNumber>1</housingNumber><housingLightingCode>1</housingLightingCode><familyOrgCode>75303716633048111X1009</familyOrgCode><doctorCode>003</doctorCode><familyAddrCode>320412113221</familyAddrCode><familyAddr>江苏省常州市武进区牛塘镇横溪</familyAddr><housingCode>1</housingCode><kitchenCode>1</kitchenCode><kitchenVentCode>1</kitchenVentCode><familyProCode>1</familyProCode><waterCode>1</waterCode><fuelCode>1</fuelCode><toiletsCode>1</toiletsCode><livestockCode>1</livestockCode><elecDeviceCode>1</elecDeviceCode><vehicleCode>1</vehicleCode><econStatusCode>1</econStatusCode><perMonthIncomeCode>1</perMonthIncomeCode><famiTotalPay>1000.21</famiTotalPay><famiFoodPay>3020.21</famiFoodPay><garDisposalCode>1</garDisposalCode><sewageDiposalCode>1</sewageDiposalCode><cultureSportCode>9</cultureSportCode><fileDate>2013-12-11</fileDate><fileDoctorCode>003</fileDoctorCode><inputDate>2013-11-11</inputDate><inputUserCode>003</inputUserCode><familyTypeCode>1</familyTypeCode><remark>分将地方的发动机发的发的京东方的</remark>" +
				"<members><member><orderNumber>1</orderNumber><householdCode>23</householdCode><name>家庭成员1</name><idcardType>02</idcardType><cardNo>320282198908100076</cardNo><sex>1</sex><birthDate>1989-08-10</birthDate><educationDegree>60</educationDegree><careerCode>9-3</careerCode><marriageCode>23</marriageCode><remark>封疆大吏</remark><state>1</state></member>" +
				"<member><orderNumber>3</orderNumber><householdCode>23</householdCode><name>家庭成员33</name><idcardType>03</idcardType><cardNo>320282198908100078</cardNo><sex>2</sex><birthDate>1989-08-15</birthDate><educationDegree>70</educationDegree><careerCode>9-2</careerCode><marriageCode>23</marriageCode><remark>奖金</remark><state>1</state></member></members>" +
				"</item>" +
				"</body></request>";
		   CheckResult familyResult =sc.check(checkFamilyXml, checkFamilyInputStream);
		   System.out.println(familyResult.getErrorMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
