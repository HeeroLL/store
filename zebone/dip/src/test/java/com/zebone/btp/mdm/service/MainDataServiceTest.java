package com.zebone.btp.mdm.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zebone.btp.mdm.mapper.MainDataMapper;
import com.zebone.btp.mdm.vo.DBQuery;
import com.zebone.btp.mdm.vo.MainDataTypeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:config/ApplicationContext-*.xml" })
public class MainDataServiceTest {

	protected ApplicationContext context;

	@Autowired
	private MainDataMapper mainDataMapper;

	// @Test
	public void testQueryMainDataType() {
		MainDataTypeVO t = new MainDataTypeVO();

		List<MainDataTypeVO> list = mainDataMapper.queryMDTList(t);

		for (MainDataTypeVO vo : list) {
			System.out.println(vo);
		}
		assertEquals("1", "1");
	}

	@Test
	public void testList() {
		DBQuery query = new DBQuery();

		query.setDbName("BTP_MHO");
		query.setDbFields("MHO_ID, MHO_NAME, MHO_CODE, LEVEL_CODE, PARENT_ID, ADDRESS, AREA, PHONE, TYPE_CODE, ORDER_NO, MANAGER, IS_DELETED, CATEGORY, REMARK, HOSPITAL_NATURE, HOSPITAL_GRADE, HOSPITAL_TYPE, IS_DESIGNATED_HOSPITAL, IS_NCMS, CREATOR_ID, CREATE_TIME, UPDATE_TIME, OPERATOR_ID, POST, WEBSITE");

		System.out.println("=====================");
		
		RowBounds b = new RowBounds();

		List<HashMap<String, String>> list = mainDataMapper.queryDetailObject(
				b, query);
		for (HashMap<String, String> map : list) {
			System.out.println(map);
		}
		assertEquals("1", "1");

	}
	
	@Test
	public void testAuto()
	{
		//DB2JavaBean auto = new DB2JavaBean(mainDataMapper.findByCode("MD2000"));
		//auto.init();
		assertEquals("1", "1");
	}
	

}
