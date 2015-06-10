package com.zebone.dnode.engine.analyze.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 多数据源测试
 * @author songjunjie
 * @version 2013-8-18 下午03:33:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class MultiDataSourceTest {
	@Resource
	private MappingTableMapper mappingTableMapper;
	@Resource
	private DocAnalyzedMapper docAnalyzedMapper;
	@Resource(name="dataSource_dc")
	private DataSource ds;
	@Resource
	private SqlSessionFactory sqlSessionFactory_dc;
	@Test
	public void test(){
		Connection con;
		try {
			con = ds.getConnection();
			System.out.println(con.getCatalog());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
