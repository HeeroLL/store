package com.zebone.dip.ws.resource;


import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.zebone.dip.ws.resource.service.ResourceDataService;


public class Test {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2014-3-26 下午3:27:14 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.setAddress("http://localhost:8080/DIP/ws/resourceData");
		factory.setServiceClass(ResourceDataService.class);
		ResourceDataService uploadImg = (ResourceDataService) factory.create();
		String x = "<request><head><requestId>A10001</requestId><tradeNo>KSZY_1</tradeNo><orgCode>00254555733040090P9399</orgCode><authorizationCode>A0001201</authorizationCode>"+
"<systemCode></systemCode></head><body><item><deptCode>002</deptCode><deptName>海宁市卫生局的子科室</deptName><deptDesc>测试测试的</deptDesc>" +
"<orgCode>75303716633048111X1009</orgCode><parentDeptCode>K01</parentDeptCode>"+
"</item></body></request>";
		String y = "<request><head><requestId>A10001</requestId><tradeNo>WSRY_1</tradeNo><orgCode>00254555733040090P9399</orgCode><authorizationCode>A0001202</authorizationCode><systemCode></systemCode></head>" +
				"<body><item>" +
				"<alias>人员名称别名</alias><idType>01</idType><idNo>320282198908100076</idNo>" +
				"<medicalOrgan>75303716633048111X1009</medicalOrgan><sex>12</sex><nationality>12</nationality><birthday>1989-08-10</birthday><maritalStatus>20</maritalStatus>" +
				"<jobCategory>8-7</jobCategory><postCode>1</postCode><positionalTitles>1</positionalTitles><politicalStatus>05</politicalStatus><address>江苏省常州市武进区牛塘镇横溪</address><tel>12121212121</tel>" +
				"<mobile>13121211112121</mobile>" +
				"<email>D!D@c/@.om</email><qq>478956680</qq><msn>478956680</msn><entryDate>1999-01-12</entryDate><qualificationDate>1989-01-12</qualificationDate><certificateNo>1231231312312</certificateNo><school>江苏大学</school><profession>100807</profession><education>20</education><degree>1</degree><diplomaNo>6786867867</diplomaNo><degreeCertificateNo>7564654354</degreeCertificateNo><graduationDate>2011-02-10</graduationDate>" +
				"<schoolSystem>90</schoolSystem><healthCondition>1</healthCondition><firstWorkDate>2011-08-15</firstWorkDate><resume>的规范化个非结构化结过婚扣扣建行卡</resume></item></body></request>";
		
		String z = "<request>" +
				"<head><requestId>A10001</requestId><tradeNo>JTDA_1</tradeNo><orgCode>00254555733040090P9399</orgCode><authorizationCode>A0001201</authorizationCode><systemCode></systemCode></head>" +
				"<body>" +
				"<item>" +
				"<famiCode>2020</famiCode>" +
				"<landlordName>ssss东2</landlordName><landlordTEL>12345678912</landlordTEL>" +
				"<householdName>户主</householdName>" +
				"<housePopuNumber>1</housePopuNumber><residentPopuNumber>1</residentPopuNumber>" +
				"<liveType>1</liveType>" +
				"<totalArea>20.1</totalArea><!--总面积( 平方米) 可以为空-->  <perArea>30.2</perArea><!--人均面积(平方米) 可以为空-->  "+
				"<familyTEL>12345678912</familyTEL><paperFileNo>123456789</paperFileNo><housingPropertyCode>1</housingPropertyCode>" +
				"<housingNumber>2</housingNumber>"+
				"<housingLightingCode>3</housingLightingCode>" +
				"<familyOrgCode>75303716633048111X1009</familyOrgCode>" +
				"<doctorCode>20140417</doctorCode><familyAddr>镇横溪常州市武进区牛塘镇横溪江苏省常州市武进区牛塘镇横溪江苏省常州市武进区牛塘镇横溪江苏省常州市武进区牛塘镇横溪江苏省常州市武进区牛塘镇横溪</familyAddr><housingCode>1</housingCode><kitchenCode>1</kitchenCode><kitchenVentCode>1</kitchenVentCode><familyProCode>1</familyProCode><waterCode>1</waterCode><fuelCode>1</fuelCode><toiletsCode>1</toiletsCode><livestockCode>1</livestockCode><elecDeviceCode>1</elecDeviceCode><vehicleCode>1</vehicleCode><econStatusCode>1</econStatusCode><perMonthIncomeCode>1</perMonthIncomeCode>" +
				"<famiTotalPay>3000</famiTotalPay>" +
				"<famiFoodPay>30.1</famiFoodPay><garDisposalCode>1</garDisposalCode><sewageDiposalCode>1</sewageDiposalCode>" +
				"<cultureSportCode>1,3,9,8</cultureSportCode><fileDate>2013-12-11</fileDate>" +
				"<fileDoctorCode>20140417</fileDoctorCode><inputDate>2013-11-11</inputDate>" +
				"<inputUserCode>20140417</inputUserCode><familyTypeCode>1</familyTypeCode><remark>分将地方的发动机发的发的京东方的</remark>" +
				"<members>" +
				"<member><orderNumber>3</orderNumber>        <householdCode>23</householdCode><name>家庭成员5</name><idcardType>02</idcardType><cardNo>320282198908100076</cardNo><sex>1</sex><birthDate>1989-08-10</birthDate><educationDegree>60</educationDegree><careerCode>9-3</careerCode><marriageCode>23</marriageCode><remark>封疆大吏</remark><state>2</state></member>" +
				"<member><orderNumber>2</orderNumber><householdCode>23</householdCode><name>家庭成员33</name><idcardType>03</idcardType><cardNo>320282198908100076</cardNo><sex>7</sex><birthDate>1989-08-15</birthDate><educationDegree>70</educationDegree><careerCode>9-2</careerCode><marriageCode>23</marriageCode><remark>奖金</remark><state>1</state></member>" +
				"</members>" +
				"</item>" +
				"</body></request>";
		String test = "<request><head> <requestId>A10001</requestId><tradeNo>JTDA_2</tradeNo><!--交易编号  资源名称+操作编号   01 增加  02 修改 00 删除  04查询--><orgCode>73381695533048211K1009</orgCode><!--机构编号--><authorizationCode>A0001201</authorizationCode><!--交易授权码，一个机构分配一个合法，目前不实现--><systemCode></systemCode><!--来源系统编码 ,默认可以不填--></head><body> <!--数据体--><item>  <!--包装标签 --><famiCode>12345678901819</famiCode><!--家庭档案编号 必填-->  <landlordName>简爱</landlordName><!--房东姓名 可以为空-->  <landlordTEL>12345565656</landlordTEL><!--房东电话 可以为空-->  <householdName>简爱</householdName><!--户主姓名 可以为空-->  <housePopuNumber>4</housePopuNumber><!--户籍人口数 可以为空-->  <residentPopuNumber>2</residentPopuNumber><!--常住人口数 可以为空-->  <liveType>2</liveType><!--居住类型 可以为空-->  <totalArea>12345.22</totalArea><!--总面积( 平方米) 可以为空-->  <perArea>3086.31</perArea><!--人均面积(平方米) 可以为空-->  <familyTEL>12332121231</familyTEL><!--家庭联系电话 可以为空-->  <paperFileNo>123</paperFileNo><!--纸质档案编号 可以为空-->  <housingPropertyCode>1</housingPropertyCode><!--住房性质可以为空-->  <housingNumber>1</housingNumber><!--住房间数 可以为空-->  <housingLightingCode>1</housingLightingCode><!--住房采光 可以为空-->  <familyOrgCode>73381695533048211K1009</familyOrgCode><!--管理机构 必填-->  <doctorCode>REN0008</doctorCode><!--责任医生 必填-->  <familyAddrCode>330402008015</familyAddrCode><!--家庭地址编码 可以为空-->  <familyAddr>shjx</familyAddr><!--家庭地址详细地址 可以为空-->  <housingCode>1</housingCode><!--房屋类型 可以为空-->  <kitchenCode>1</kitchenCode><!--厨房 可以为空-->  <kitchenVentCode>2</kitchenVentCode><!--厨房排风设施 可以为空-->  <familyProCode>5</familyProCode><!--家庭属性 可以为空-->  <waterCode>1</waterCode><!--饮水 可以为空-->  <fuelCode>1</fuelCode><!--燃料 可以为空-->  <toiletsCode>1</toiletsCode><!--家庭卫生厕所 可以为空-->  <livestockCode>1</livestockCode><!--禽畜栏位置代码 可以为空-->  <elecDeviceCode>1</elecDeviceCode><!--家用电器代码 可以为空-->  <vehicleCode>1</vehicleCode><!--交通工具 可以为空-->  <econStatusCode>1</econStatusCode><!--经济状况 可以为空-->  <perMonthIncomeCode>5</perMonthIncomeCode><!--人均月收入 可以为空-->  <famiTotalPay>1234567.12</famiTotalPay><!--家庭总支出 （元/人/月） 可以为空-->  <famiFoodPay>1000000.00</famiFoodPay><!--饮食支出   （元/人/月）可以为空-->  <garDisposalCode>1</garDisposalCode><!--垃圾处理 可以为空-->  <sewageDiposalCode>1</sewageDiposalCode><!--污水处理 可以为空-->  <cultureSportCode>1</cultureSportCode><!--文体设备(多选） 可以为空-->  <fileDate>2014-04-10</fileDate><!--建档日期 必填-->  <fileDoctorCode>REN0008</fileDoctorCode><!--建档医生 必填-->  <inputDate>2014-04-10</inputDate><!--登记日期 必填-->  <inputUserCode>REN0008</inputUserCode><!--登记人 必填-->  <familyTypeCode>1</familyTypeCode><!--家庭类型代码 可以为空-->   <remark>无</remark><!--备注 可以为空-->  <!-- 家庭成员列表 --><members>		<member><orderNumber>1</orderNumber><!--家庭成员序号 必填-->  <householdCode>96</householdCode><!--家庭成员与户主的关系 必填-->  <name>小奥</name><!--姓名 必填-->   <cardNo>01</cardNo><!--身份证件类别 可以为空-->  <sex>2</sex><!--性别 必填-->  <birthDate>1989-05-25</birthDate><!--出生日期 必填-->  <educationDegree>70</educationDegree><!--文化程度 必填-->  <careerCode>X</careerCode><!--职业 必填-->  <marriageCode>21</marriageCode><!--婚姻 必填-->  <remark>无</remark><!--备注 必填-->  <state>1</state><!--个人状态 可以为空-->  </member><member><orderNumber>2</orderNumber><!--家庭成员序号 必填-->  <householdCode>90</householdCode><!--家庭成员与户主的关系 必填-->  <name>小斯</name><!--姓名 必填-->   <cardNo>01</cardNo><!--身份证件类别 可以为空-->  <sex>1</sex><!--性别 必填-->  <birthDate>1991-11-11</birthDate><!--出生日期 必填-->  <educationDegree>70</educationDegree><!--文化程度 必填-->  <careerCode>X</careerCode><!--职业 必填-->  <marriageCode>21</marriageCode><!--婚姻 必填-->  <remark>无</remark><!--备注 必填-->  <state>1</state><!--个人状态 可以为空-->  </member><member><orderNumber>3</orderNumber><!--家庭成员序号 必填-->  <householdCode>90</householdCode><!--家庭成员与户主的关系 必填-->  <name>小安</name><!--姓名 必填-->   <cardNo>01</cardNo><!--身份证件类别 可以为空-->  <sex>1</sex><!--性别 必填-->  <birthDate>1980-12-11</birthDate><!--出生日期 必填-->  <educationDegree>70</educationDegree><!--文化程度 必填-->  <careerCode>X</careerCode><!--职业 必填-->  <marriageCode>21</marriageCode><!--婚姻 必填-->  <remark>无</remark><!--备注 必填-->  <state>1</state><!--个人状态 可以为空-->  </member><member><orderNumber>4</orderNumber><!--家庭成员序号 必填-->  <householdCode>90</householdCode><!--家庭成员与户主的关系 必填-->  <name>小莉</name><!--姓名 必填-->   <cardNo>01</cardNo><!--身份证件类别 可以为空-->  <sex>2</sex><!--性别 必填-->  <birthDate>1971-11-18</birthDate><!--出生日期 必填-->  <educationDegree>70</educationDegree><!--文化程度 必填-->  <careerCode>X</careerCode><!--职业 必填-->  <marriageCode>21</marriageCode><!--婚姻 必填-->  <remark>无</remark><!--备注 必填-->  <state>1</state><!--个人状态 可以为空-->  </member></members></item></body></request>";
		String yy = "<request><head><requestId>A10001</requestId><tradeNo>JTDA_3</tradeNo><orgCode>00254555733040090P9399</orgCode><authorizationCode>A0001202</authorizationCode><systemCode></systemCode></head>" +
		"<body>" +
		"<item>" +"<familyOrgCode>75303716633048111X1009</familyOrgCode>" +
		"</item>" +
		"</body></request>";

		String result = uploadImg.request(yy);
		System.out.println(result);
		
         
	}

}
