package com.zebone.dip.release;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zebone.dip.release.dao.DataSyncItemMapper;
import com.zebone.dip.release.dao.DataSyncMainMapper;
import com.zebone.dip.release.vo.DataSyncItem;
import com.zebone.dip.release.vo.DataSyncMain;

@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class ReleaseServiceTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private DataSyncItemMapper dataSyncItemMapper;
	
	@Resource
	private DataSyncMainMapper dataSyncMainMapper;

	@Test
	public void testDataSyncItemInsert(){
		DataSyncItem dsi = new DataSyncItem();
		dsi.setId("111111");
		dsi.setDataType("33");
		dsi.setDataContent("444");
		dsi.setMainId("2");
		dataSyncItemMapper.insertDataSyncItem(dsi);
	}
	@Test
	public void testDataSyncMainInsert(){
		DataSyncMain main = new DataSyncMain();
		main.setId("222222");
		//main.setLastSyncTime(new Date());
		main.setIsAll("yes");
		main.setSyncFlag("hellw");
		dataSyncMainMapper.insertDataSyncMain(main);
	}
	@Test
	public void testDataSyncItemUpdate(){
		DataSyncItem dsi = new DataSyncItem();
		dsi.setId("111111");
		dsi.setDataType("11");
		dsi.setDataContent("11");
		dsi.setMainId("11");
		dataSyncItemMapper.updateDataSyncItem(dsi);
	}
	@Test
	public void testDataSyncMainUpdate(){
		DataSyncMain main = new DataSyncMain();
		main.setId("222222");
		//main.setLastSyncTime(new Date());
		main.setIsAll("11");
		main.setSyncFlag("11");
		dataSyncMainMapper.updateDataSyncMain(main);
	}
	
	@Test
	public void testDataSyncItemEquals(){
		DataSyncItem dsi = new DataSyncItem();
		dsi.setId("111111");
		dsi.setDataType("11");
		dsi.setDataContent("11");
		dsi.setMainId("11");
		
		List<DataSyncItem> list = dataSyncItemMapper.getDataSyncItemEquals(dsi);
		System.out.println(list.size());
		for(DataSyncItem dsm : list){
			System.out.println(dsm.getId());
		}
		
	}
	@Test
	public void testDataSyncMainEquals(){
		DataSyncMain main = new DataSyncMain();
		main.setId("222222");
		//main.setLastSyncTime(new Date());
		main.setIsAll("11");
		main.setSyncFlag("11");
		List<DataSyncMain> list = dataSyncMainMapper.getDataSyncMainEquals(main);
		System.out.println(list.size());
		for(DataSyncMain dsm : list){
			System.out.println(dsm.getId());
		}
	}
	@Test
	public void testDataSyncItemLikes(){
		DataSyncItem dsi = new DataSyncItem();
		dsi.setId("111111");
		dsi.setDataType("11");
		dsi.setDataContent("11");
		dsi.setMainId("11");
		List<DataSyncItem> list = dataSyncItemMapper.getDataSyncItemLikes(dsi);
		System.out.println(list.size());
		for(DataSyncItem dsm : list){
			System.out.println(dsm.getId());
		}
	}
	@Test
	public void testDataSyncMainLikes(){
		DataSyncMain main = new DataSyncMain();
		main.setId("222222");
		//main.setLastSyncTime(new Date());
		main.setIsAll("11");
		main.setSyncFlag("11");
		List<DataSyncMain> list = dataSyncMainMapper.getDataSyncMainLikes(main);
		System.out.println(list.size());
		for(DataSyncMain dsm : list){
			System.out.println(dsm.getId());
		}
	}
	
	
	public DataSyncItemMapper getDataSyncItemMapper() {
		return dataSyncItemMapper;
	}

	public void setDataSyncItemMapper(DataSyncItemMapper dataSyncItemMapper) {
		this.dataSyncItemMapper = dataSyncItemMapper;
	}

	public DataSyncMainMapper getDataSyncMainMapper() {
		return dataSyncMainMapper;
	}

	public void setDataSyncMainMapper(DataSyncMainMapper dataSyncMainMapper) {
		this.dataSyncMainMapper = dataSyncMainMapper;
	}
	
	
}
