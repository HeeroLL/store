package com.zebone.dip.wsmd;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zebone.dip.ws.resource.pojo.RequestHead;
import com.zebone.dip.ws.resource.pojo.UserRequest;
import com.zebone.dip.ws.resource.pojo.UserResponse;
import com.zebone.dip.ws.resource.pojo.UserRequest.RequestUserParam;
import com.zebone.dip.ws.resource.pojo.UserRequest.UserBody;
import com.zebone.dip.ws.resource.pojo.UserResponse.ResponseUserItem;
import com.zebone.dip.ws.resource.service.ResourcesUserService;

@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class userTest extends AbstractJUnit4SpringContextTests{

	@Resource
	ResourcesUserService resourcesUserService;
	
	//@Test
	public void save() {
		System.out.println("尚未实现");
		UserRequest userRequest = new UserRequest();
		RequestHead requestHead = new RequestHead();
		UserBody body = new UserBody();
		RequestUserParam RequestUserParam = new RequestUserParam();
		body.setUser(RequestUserParam);
		userRequest.setBody(body);
		userRequest.setRequestHead(requestHead);
		
		requestHead.setRequestId("A10001");
		requestHead.setOrgCode("00001");
		requestHead.setTradeNo("R00201");
		
		RequestUserParam.setName("杭贺");
		RequestUserParam.setCode("456");
		RequestUserParam.setMedicalOrganCode("10115000");
		resourcesUserService.saveUser(userRequest);
		System.out.println("结束");
	}
	
	//@Test
	public void update() {
		System.out.println("尚未实现");
		UserRequest userRequest = new UserRequest();
		RequestHead requestHead = new RequestHead();
		UserBody body = new UserBody();
		RequestUserParam RequestUserParam = new RequestUserParam();
		body.setUser(RequestUserParam);
		userRequest.setBody(body);
		userRequest.setRequestHead(requestHead);
		
		requestHead.setRequestId("A10001");
		requestHead.setOrgCode("00001");
		requestHead.setTradeNo("R00201");
		
		RequestUserParam.setName("杭贺1");
		RequestUserParam.setCode("456");
		RequestUserParam.setMedicalOrganCode("10115000");
		resourcesUserService.updateUser(userRequest);
		System.out.println("结束");
	}
	
	//@Test
	public void delUser() {
		System.out.println("尚未实现");
		UserRequest userRequest = new UserRequest();
		RequestHead requestHead = new RequestHead();
		UserBody body = new UserBody();
		RequestUserParam RequestUserParam = new RequestUserParam();
		body.setUser(RequestUserParam);
		userRequest.setBody(body);
		userRequest.setRequestHead(requestHead);
		
		requestHead.setRequestId("A10001");
		requestHead.setOrgCode("00001");
		requestHead.setTradeNo("R00201");
		
		RequestUserParam.setName("杭贺1");
		RequestUserParam.setCode("456");
		RequestUserParam.setMedicalOrganCode("10115000");
		resourcesUserService.delUser(userRequest);
		System.out.println("结束");
	}
	
	@Test
	public void searchUser() {
		System.out.println("尚未实现");
		UserRequest userRequest = new UserRequest();
		RequestHead requestHead = new RequestHead();
		UserBody body = new UserBody();
		RequestUserParam RequestUserParam = new RequestUserParam();
		body.setUser(RequestUserParam);
		userRequest.setBody(body);
		userRequest.setRequestHead(requestHead);
		
		requestHead.setRequestId("A10001");
		requestHead.setOrgCode("00001");
		requestHead.setTradeNo("R00201");
		
		RequestUserParam.setName("杭");
		RequestUserParam.setCode("456");
		RequestUserParam.setMedicalOrganCode("10115000");
		UserResponse aaa = resourcesUserService.searchUser(userRequest);
		for(ResponseUserItem a : aaa.getUserResponseBody().getResponseUserItemList()){
			System.out.println(a.getName());
		}
		System.out.println("结束");
	}

}
