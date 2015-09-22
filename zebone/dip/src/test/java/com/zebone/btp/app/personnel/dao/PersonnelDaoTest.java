package com.zebone.btp.app.personnel.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zebone.btp.app.personnel.vo.Personnel;
import com.zebone.btp.app.personnel.vo.PersonnelMhoR;

@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class PersonnelDaoTest extends AbstractJUnit4SpringContextTests{

	protected ApplicationContext context;
	@Resource
	private PersonnelMapper personnelMapper;

	@Test
	public void testPersonnelDao(){
		String mhoId="402882e438d6a8ec0138dc21f0bc01cc";
		List<PersonnelMhoR> list = personnelMapper.findPersonByMhoId(mhoId);
		PersonnelMhoR personnelMhoR = new PersonnelMhoR();
		Personnel Personnel =new Personnel();
		//personnelMhoR.setMhoId(mhoId);
		personnelMhoR.setFullname("张");
		List<PersonnelMhoR> list2 = personnelMapper.findPersonByPersonMhoR(null,personnelMhoR);
		System.out.println("1111111111111");

	}

}
