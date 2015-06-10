package com.zebone.dip.wsmd;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zebone.dip.ws.resource.pojo.FamilyRequest;
import com.zebone.dip.ws.resource.pojo.RequestHead;
import com.zebone.dip.ws.resource.pojo.FamilyRequest.FamilyBody;
import com.zebone.dip.ws.resource.pojo.FamilyRequest.FamilyMember;
import com.zebone.dip.ws.resource.pojo.FamilyRequest.Members;
import com.zebone.dip.ws.resource.pojo.FamilyRequest.RequestFamilyParam;
import com.zebone.dip.ws.resource.service.ResourceFamilyService;
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class familyTest  extends AbstractJUnit4SpringContextTests{

	@Resource
	ResourceFamilyService resourceFamilyService;
	
	//@Test
	public void save() {
		System.out.println("尚未实现");
		FamilyRequest familyRequest = new FamilyRequest();
		RequestHead requestHead = new RequestHead();
		FamilyBody body = new FamilyBody();
		RequestFamilyParam RequestFamilyParam = new RequestFamilyParam();
		body.setFamily(RequestFamilyParam);
		familyRequest.setBody(body);
		familyRequest.setRequestHead(requestHead);
		
		requestHead.setRequestId("A10001");
		requestHead.setOrgCode("10115000");
		requestHead.setTradeNo("R00201");
		
		RequestFamilyParam.setFamiCode("789456");
		RequestFamilyParam.setDoctorCode("101150001881");
		RequestFamilyParam.setFamilyOrgCode("10115000");
		RequestFamilyParam.setFileDate("20140404");
		RequestFamilyParam.setFileDoctorCode("101150001881");
		RequestFamilyParam.setInputDate("20140404");
		RequestFamilyParam.setInputUserCode("101150001881");
		RequestFamilyParam.setLandlordName("大概");
		RequestFamilyParam.setHouseholdName("规范");
		
		List<FamilyMember> list = new ArrayList<FamilyRequest.FamilyMember>();
		FamilyMember member = new FamilyMember();
		member.setOrderNumber(1);
		member.setHouseholdCode("1");
		member.setName("12345");
		member.setSex("1");
		member.setBirthDate("1");
		member.setEducationDegree("1");
		member.setCareerCode("1");
		member.setMarriageCode("1");
		member.setRemark("1");
		list.add(member);
		Members members = new Members();
		members.setFamilyMemberList(list);
		RequestFamilyParam.setMembers(members);
		resourceFamilyService.saveFamily(familyRequest);
		System.out.println("结束");
	}
	
	//@Test
	public void delFamily() {
		System.out.println("尚未实现");
		FamilyRequest familyRequest = new FamilyRequest();
		RequestHead requestHead = new RequestHead();
		FamilyBody body = new FamilyBody();
		RequestFamilyParam RequestFamilyParam = new RequestFamilyParam();
		body.setFamily(RequestFamilyParam);
		familyRequest.setBody(body);
		familyRequest.setRequestHead(requestHead);
		
		requestHead.setRequestId("A10001");
		requestHead.setOrgCode("10115000");
		requestHead.setTradeNo("R00201");
		
		RequestFamilyParam.setFamiCode("789456");
		RequestFamilyParam.setDoctorCode("101150001881");
		RequestFamilyParam.setFamilyOrgCode("10115000");
		RequestFamilyParam.setFileDate("20140404");
		RequestFamilyParam.setFileDoctorCode("101150001881");
		RequestFamilyParam.setInputDate("20140404");
		RequestFamilyParam.setInputUserCode("101150001881");
		RequestFamilyParam.setLandlordName("大概");
		RequestFamilyParam.setHouseholdName("规范");
		
		List<FamilyMember> list = new ArrayList<FamilyRequest.FamilyMember>();
		FamilyMember member = new FamilyMember();
		member.setOrderNumber(1);
		member.setHouseholdCode("1");
		member.setName("1");
		member.setSex("1");
		member.setBirthDate("1");
		member.setEducationDegree("1");
		member.setCareerCode("1");
		member.setMarriageCode("1");
		member.setRemark("1");
		list.add(member);
		Members members = new Members();
		members.setFamilyMemberList(list);
		RequestFamilyParam.setMembers(members);
		resourceFamilyService.delFamily(familyRequest);
		System.out.println("结束");
	}
	
	//@Test
	public void searchFamily() {
		System.out.println("尚未实现");
		FamilyRequest familyRequest = new FamilyRequest();
		RequestHead requestHead = new RequestHead();
		FamilyBody body = new FamilyBody();
		RequestFamilyParam RequestFamilyParam = new RequestFamilyParam();
		body.setFamily(RequestFamilyParam);
		familyRequest.setBody(body);
		familyRequest.setRequestHead(requestHead);
		
		requestHead.setRequestId("A10001");
		requestHead.setOrgCode("10115000");
		requestHead.setTradeNo("R00201");
		
		RequestFamilyParam.setFamiCode("789456");
		RequestFamilyParam.setDoctorCode("101150001881");
		RequestFamilyParam.setFamilyOrgCode("10115000");
		RequestFamilyParam.setFileDate("20140404");
		RequestFamilyParam.setFileDoctorCode("101150001881");
		RequestFamilyParam.setInputDate("20140404");
		RequestFamilyParam.setInputUserCode("101150001881");
		RequestFamilyParam.setLandlordName("大概");
		RequestFamilyParam.setHouseholdName("规范");
		
		List<FamilyMember> list = new ArrayList<FamilyRequest.FamilyMember>();
		FamilyMember member = new FamilyMember();
		member.setOrderNumber(1);
		member.setHouseholdCode("1");
		member.setName("1");
		member.setSex("1");
		member.setBirthDate("1");
		member.setEducationDegree("1");
		member.setCareerCode("1");
		member.setMarriageCode("1");
		member.setRemark("1");
		list.add(member);
		Members members = new Members();
		members.setFamilyMemberList(list);
		RequestFamilyParam.setMembers(members);
		resourceFamilyService.searchFamily(familyRequest);
		System.out.println("结束");
	}
	
	@Test
	public void updateFamily() {
		System.out.println("尚未实现");
		FamilyRequest familyRequest = new FamilyRequest();
		RequestHead requestHead = new RequestHead();
		FamilyBody body = new FamilyBody();
		RequestFamilyParam RequestFamilyParam = new RequestFamilyParam();
		body.setFamily(RequestFamilyParam);
		familyRequest.setBody(body);
		familyRequest.setRequestHead(requestHead);
		
		requestHead.setRequestId("A10001");
		requestHead.setOrgCode("10115000");
		requestHead.setTradeNo("R00201");
		
		RequestFamilyParam.setFamiCode("789456");
		RequestFamilyParam.setDoctorCode("101150001881");
		RequestFamilyParam.setFamilyOrgCode("10115000");
		RequestFamilyParam.setFileDate("20140404");
		RequestFamilyParam.setFileDoctorCode("101150001881");
		RequestFamilyParam.setInputDate("20140404");
		RequestFamilyParam.setInputUserCode("101150001881");
		RequestFamilyParam.setLandlordName("大概1");
		RequestFamilyParam.setHouseholdName("规范1");
		
		List<FamilyMember> list = new ArrayList<FamilyRequest.FamilyMember>();
		FamilyMember member = new FamilyMember();
		member.setOrderNumber(1);
		member.setHouseholdCode("1");
		member.setName("丽丽2");
		member.setSex("1");
		member.setBirthDate("1");
		member.setEducationDegree("1");
		member.setCareerCode("1");
		member.setMarriageCode("1");
		member.setRemark("1");
		list.add(member);
		FamilyMember member2 = new FamilyMember();
		member2.setOrderNumber(8);
		member2.setHouseholdCode("1");
		member2.setName("hhhh");
		member2.setSex("1");
		member2.setBirthDate("1");
		member2.setEducationDegree("1");
		member2.setCareerCode("1");
		member2.setMarriageCode("1");
		member2.setRemark("1");
		list.add(member2);
		Members members = new Members();
		members.setFamilyMemberList(list);
		RequestFamilyParam.setMembers(members);
		resourceFamilyService.updateFamily(familyRequest);
		System.out.println("结束");
	}

}
