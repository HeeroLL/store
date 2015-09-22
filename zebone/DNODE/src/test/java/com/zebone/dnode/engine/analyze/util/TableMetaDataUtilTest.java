package com.zebone.dnode.engine.analyze.util;

import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TableMetaDataUtil测试
 * 
 * @author songjunjie
 * @version 2013-8-12 下午02:35:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/zebone/dnode/engine/analyze/config/ApplicationContext.xml" })
public class TableMetaDataUtilTest {
	@Resource(name = "dataSource_dc")
	private DataSource dcDataSource;
	
	@Test
	public void getTableMetaData() {
		Map<String, String> map = null;
		try {
			map = TableMetaDataUtil.getTableMetaData(dcDataSource, "AAA");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(map.size() > 0);
		for (String c : map.keySet()) {
			String t = map.get(c);
			System.out.println(c + "\t" + t);
		}
	}
	
	@Test
	public void getFKTableTest(){
		TableMetaDataUtil.getFKTable(dcDataSource, "TEST_MAIN");
	}
}
