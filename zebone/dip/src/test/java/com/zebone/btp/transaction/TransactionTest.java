package com.zebone.btp.transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zebone.btp.app.personnel.service.PersonnelService;
import com.zebone.btp.app.personnel.vo.Personnel;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;
import com.zebone.btp.transaction.pojo.DeptInfo;
import com.zebone.btp.transaction.service.DeptInfoService;
import com.zebone.util.UUIDUtil;

@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class TransactionTest extends AbstractJUnit4SpringContextTests{
	protected ApplicationContext context;
	@Resource(name="deptInfoService")
	private DeptInfoService deptInfoService;

	@Resource
	private PersonnelService personnelService;



	public void testTransaction(){
		deptInfoService.delete("0008");
		try{
			deptInfoService.saveTwoDept();
		}catch(Exception e){
			e.printStackTrace();
		}

		DeptInfo dept1 = deptInfoService.get("0008");
		//应该都没有保存上
		Assert.assertNull(dept1);

	}

	@Test
	public void testPersonnel(){
		Personnel  personnel = new Personnel();
		personnel.setFullname("张三");
		personnel.setAlias("san");
		personnel.setMobile("1111");
		personnel.setSex("1");
		personnel.setPhone("66666666");
		personnel.setLoginName("3asd");
		personnel.setPassword("apwd");
		personnel.setPersonnelId(UUIDUtil.getUuid());
		personnel.setCreatorId("1212");
		personnel.setCreateTime(new Date());

		PersonnelMhoR o = new PersonnelMhoR();
		o.setPersonnelId(personnel.getPersonnelId());
		o.setMhoId("123");
		o.setDeptPersonnelCode("2");
		o.setDepartment("s");
		o.setPersonnelType("2");
		o.setCreatorId("1");
		o.setCreateTime(new Date());

		PersonnelMhoR o1 = new PersonnelMhoR();
		o1.setPersonnelId(personnel.getPersonnelId());
		o1.setMhoId("123");
		o1.setDeptPersonnelCode("2");
		o1.setDepartment("s");
		o1.setPersonnelType("2");
		o1.setCreatorId("1");
		o1.setCreateTime(new Date());
		List<PersonnelMhoR> personnelMhoRs = new ArrayList<PersonnelMhoR>();
		personnelMhoRs.add(o);
		personnelMhoRs.add(o1);
		//personnelService.savePersonnel(personnel, personnelMhoRs);
	}
}
