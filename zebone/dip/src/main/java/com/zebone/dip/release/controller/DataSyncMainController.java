package com.zebone.dip.release.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.dip.dictionary.dao.DipDictionaryTypeMapper;
import com.zebone.dip.ds.service.DsObjService;
import com.zebone.dip.ds.vo.DsObj;
import com.zebone.dip.md.dao.MasterDataMapper;
import com.zebone.dip.md.vo.MasterData;
import com.zebone.dip.release.jdbc.DictSyncOperation;
import com.zebone.dip.release.jdbc.JdbcOperation;
import com.zebone.dip.release.service.DataSyncItemService;
import com.zebone.dip.release.service.DataSyncMainService;
import com.zebone.dip.release.vo.DataSyncItem;
import com.zebone.dip.release.vo.DataSyncMain;
import com.zebone.util.UUIDUtil;

/**
 * DataSyncMain 控制层类
 * @author YinCM
 * @since 2010-9-13 16:16:43
 *
 */
@Controller
public class DataSyncMainController {
	
	private static Logger logger = Logger.getLogger(DataSyncMainController.class);
	
	@Resource
	DataSyncMainService dataSyncMainSevice;
	
	@Resource
	DataSyncItemService dataSyncItemService;
	
	@Resource
	private DsObjService dsObjService;
	
	@Resource
	private DipDictionaryTypeMapper dipDictionaryTypeMapper ;
	
	@Resource
    private MasterDataMapper masterDataMapper;
	
	@RequestMapping("dataSyncMain/index")
	public String dataSyncMainIndex(Model model){
		
		//数据源列表
		List<DsObj> dsObjList = dsObjService.findDsObjAll();
		model.addAttribute("dsObjList",dsObjList);
		
		//数据表列表
		List<MasterData> MDList = masterDataMapper.getAllMasterData();
		model.addAttribute("MDList", MDList);
		
		return "dip/release/dataSyncMain";
	}
	
	@ResponseBody
	@RequestMapping("dataSyncMain/all")
	public JsonGrid getAllDataSyncMain(){
		List<DataSyncMain> list = dataSyncMainSevice.getAllDataSyncMain();
		JsonGrid jGrid = new JsonGrid("success",0,list);
		return jGrid;
	}
	
	/**
	 * 同步操作
	 * @param mainId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="dataSyncMain/syncData", method=RequestMethod.POST)
	@Transactional
	public String synchronizeData(String mainId){
		
		DataSyncMain dsm = dataSyncMainSevice.getDataSyncMainById(mainId);
		List<DataSyncItem> dsiList = dataSyncItemService.getAllDataSyncItemByMainId(mainId);
		DsObj dsObj = dsObjService.getDsObjInfoById(dsm.getDataSourceId());
		
		//需要同步的字典类型Set
		Set<String> dictStringSet = new HashSet<String>();
		//需要同步的表值
		Set<String> dsiStringSet = new HashSet<String>();

		//获取列表item中表的string数组
		for(DataSyncItem dsi : dsiList){
			if(dsi.getDataType().trim().equals("M")){
				dsiStringSet.add(dsi.getDataContent());
			}else if(dsi.getDataType().trim().equals("D")){
				dictStringSet.add(dsi.getDataContent());
			}
		}
		
		//需要全部同步的表
		String isAll = dsm.getIsAll();
		if(isAll!=null && isAll.contains("M")){
			 List<MasterData> mdList = masterDataMapper.getAllMasterData();
			 for(MasterData md : mdList){
				 dsiStringSet.add(md.getTableName());
			 }
		}
		
		if(isAll!=null && isAll.contains("F")){
			dsiStringSet.add("P_FIELD_CONF");
		}
		
		if(isAll!=null && isAll.contains("D")){
			dsiStringSet.add("P_DICTIONARY");
			dsiStringSet.add("P_DICTIONARY_TYPE");
			//清空所有的字典类型更新Set，因为全部更新
			dictStringSet.clear();
		}
		
		//获取数据库操作链接
		Connection sourceConn = null;
		Connection targetConn =null;
		try{
			sourceConn =  JdbcOperation.getConnection("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.1.6:1521:ZB", "dip_lr", "dip_lr");
		}catch(Exception e){
			 logger.error("url:jdbc:oracle:thin:@192.168.1.6:1521:ZB--User:dsp"+":"+"----"+"该数据库连接有问题，请检查！"+e.getMessage());
		}
		
		try{
			targetConn =  JdbcOperation.getConnection(dsObj.getpDriver(), dsObj.getpUrl(), dsObj.getpUserName(), dsObj.getpPassword());
		}catch(Exception e){
			 logger.error("URL:"+dsObj.getpUrl()+"--UserName:"+dsObj.getpUserName()+"----"+"该数据库连接有问题，请检查！"+e.getMessage());
		}
		
		String[] str = dsiStringSet.toArray(new String[0]);
		if(logger.isDebugEnabled()){
			logger.debug("本次同步以下的数据表：");
			while(dictStringSet.iterator().hasNext()){
				logger.debug(dsiStringSet.iterator().next());
			}
		}
		
		//上次更新时间
		String currentSyncTimeString = dsm.getCurrentSyncTime();
		//"25-6-2010 15:57:30"
		try {
			JdbcOperation.updateTargetDBTablesCommon(sourceConn, targetConn, str, currentSyncTimeString);
		} catch (SQLException e) {
			e.printStackTrace();
			//同步失败，保存
			dsm.setSyncFlag("0");
			dataSyncMainSevice.updateDataSyncMain(dsm);
			return "同步过程中有错误"+e.getMessage();
		}
		
		//如果有字典类型需要更新
		if(dictStringSet.size()>0){
			if(logger.isDebugEnabled()){
				logger.debug("本次同步以下的数据字典类型：");
				while(dictStringSet.iterator().hasNext()){
					logger.debug(dictStringSet.iterator().next());
				}
			}
			
			Connection sourceConn1 = null;
			Connection targetConn2 =null;
			try{
				sourceConn1 =  JdbcOperation.getConnection("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@192.168.1.6:1521:ZB", "dsp", "dsp");
			}catch(Exception e){
				 logger.error("url:jdbc:oracle:thin:@192.168.1.6:1521:ZB--User:dsp"+":"+"----"+"该数据库连接有问题，请检查！"+e.getMessage());
			}
			
			try{
				targetConn2 =  JdbcOperation.getConnection(dsObj.getpDriver(), dsObj.getpUrl(), dsObj.getpUserName(), dsObj.getpPassword());
			}catch(Exception e){
				 logger.error("URL:"+dsObj.getpUrl()+"--UserName:"+dsObj.getpUserName()+"----"+"该数据库连接有问题，请检查！"+e.getMessage());
			}
			
			String[] dictStrs = dictStringSet.toArray(new String[0]);
			try {
				DictSyncOperation.updateTargetDBTablesCommon(sourceConn1, targetConn2, dictStrs, currentSyncTimeString);
			} catch (SQLException e) {
				//同步失败，保存
				dsm.setSyncFlag("0");
				dataSyncMainSevice.updateDataSyncMain(dsm);
				e.printStackTrace();
				return "字典类型同步过程中有错误"+e.getMessage();
			}
		}
		
		//更新成功
		//"25-6-2010 15:57:30"
		Date date = new Date();
		String nowString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		dsm.setLastSyncTime(dsm.getLastSyncTime());
		dsm.setCurrentSyncTime(nowString);
		dsm.setSyncFlag("1");
		dataSyncMainSevice.updateDataSyncMain(dsm);
		return "所有表同步成功!";
	}
	
	//录入item数据
	private void insertSyncItem(DataSyncMain dataSyncMain,String input_tableNames ){
		//字典值是1，主数据是0，
		if(input_tableNames!=null && input_tableNames.trim()!=""){
			//获取
			String[] tnArray = input_tableNames.split(",");
			//主数据
			if(!dataSyncMain.getIsAll().contains("M")){
				Set<String> mainDataSet = new HashSet<String>();
				for(String str : tnArray){
					String tempStr = str.split(":")[0].trim();
					if(tempStr.equals("0")){
						mainDataSet.add(str.split(":")[1]);
					}
				}
				Iterator<String> it = mainDataSet.iterator();
				while(it.hasNext()){
					String name = it.next().toString();
					DataSyncItem dsi = new DataSyncItem();
					dsi.setId(UUIDUtil.getUuid());
					dsi.setMainId(dataSyncMain.getId());
					dsi.setDataType("M");
					dsi.setDataContent(name);
					dataSyncItemService.insertDataSyncItem(dsi);
				}
			}
			//数据字典
			if(!dataSyncMain.getIsAll().contains("D")){
				Set<String> mainDataSet = new HashSet<String>();
				for(String str : tnArray){
					//str格式为1:afde1ad&&身高
					String tempStr = str.split(":")[0].trim();
					String tempStr2 = str.split(":")[1].trim();
					if(tempStr.equals("1")&&(!tempStr2.trim().equals("$$"))){
						mainDataSet.add(str.split(":")[1]);
					}
				}
				Iterator<String> it = mainDataSet.iterator();
				while(it.hasNext()){
					String name = it.next().toString();
					DataSyncItem dsi = new DataSyncItem();
					dsi.setId(UUIDUtil.getUuid());
					dsi.setMainId(dataSyncMain.getId());
					dsi.setDataType("D");
					dsi.setDataContent(name);
					dataSyncItemService.insertDataSyncItem(dsi);
				}		
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value="dataSyncMain/save", method=RequestMethod.POST)
	@Transactional
	public Map<String,String> saveDataSyncMain(DataSyncMain dataSyncMain,@RequestParam(value="tableNames", required=false)String tableNames){
		
		Map<String,String> map = new HashMap<String,String>();
		try{
			if(dataSyncMain.getId()!=null && dataSyncMain.getId().trim()!=""){
				dataSyncMainSevice.updateDataSyncMain(dataSyncMain);
			}else{
				dataSyncMain.setId(UUIDUtil.getUuid());
				dataSyncMainSevice.insertDataSyncMain(dataSyncMain);
			}
			dataSyncItemService.deleteDataSyncItemByMainId(dataSyncMain.getId());
			//插入
			insertSyncItem(dataSyncMain,tableNames);	
		}catch(Exception e){
			map.put("success", "cannot");
			return map;
		}
		
		map.put("success", "true");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="dataSyncMain/getById", method=RequestMethod.POST)
	public DataSyncMain saveDataSyncMain(String id){
		DataSyncMain dataSyncMain = null;
		try{
			dataSyncMain = dataSyncMainSevice.getDataSyncMainById(id);
			List<DataSyncItem> list = dataSyncItemService.getAllDataSyncItemByMainId(id);
			dataSyncMain.setDataSyncItemList(list);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return dataSyncMain;
	}
	
	@ResponseBody
	@RequestMapping("dataSyncMain/update")
	public String updateDataSyncMain(DataSyncMain dataSyncMain,@RequestParam(value="tableNames")String tableNames){
		try{
			dataSyncMainSevice.updateDataSyncMain(dataSyncMain);
			dataSyncItemService.deleteDataSyncItemByMainId(dataSyncMain.getId());
			//插入
			insertSyncItem(dataSyncMain,tableNames);
		}catch(Exception e){
			return "failed";
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("dataSyncMain/delete")
	@Transactional
	public String updateDataSyncMain(String ids){
		String[] idArray = ids.split(",");
		try{
			dataSyncMainSevice.deleteDataSyncMainByIds(idArray);
			for(String id : idArray){
				dataSyncItemService.deleteDataSyncItemByMainId(id);
			}
		}catch(Exception e){
			return "failed";
		}
		return "success";
	}
	
	/**
	 * 通过字典类型名称模糊查询字典类型
	 * @param query
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="dataSyncMain/getDictTypeByNameLikes", method=RequestMethod.GET)
	public Map<String,Object> getDipDictionaryTypeByNameLikes(String query){
		List<DictionaryType> list = dipDictionaryTypeMapper.getDictInfoByName(query);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("query", query);
		resultMap.put("data", list);
		return resultMap;
	}
	
	public DataSyncMainService getDataSyncMainSevice() {
		return dataSyncMainSevice;
	}

	public void setDataSyncMainSevice(DataSyncMainService dataSyncMainSevice) {
		this.dataSyncMainSevice = dataSyncMainSevice;
	}

	public DsObjService getDsObjService() {
		return dsObjService;
	}

	public void setDsObjService(DsObjService dsObjService) {
		this.dsObjService = dsObjService;
	}

	public MasterDataMapper getMasterDataMapper() {
		return masterDataMapper;
	}

	public void setMasterDataMapper(MasterDataMapper masterDataMapper) {
		this.masterDataMapper = masterDataMapper;
	}

	public DataSyncItemService getDataSyncItemService() {
		return dataSyncItemService;
	}

	public void setDataSyncItemService(DataSyncItemService dataSyncItemService) {
		this.dataSyncItemService = dataSyncItemService;
	}

	public DipDictionaryTypeMapper getDipDictionaryTypeMapper() {
		return dipDictionaryTypeMapper;
	}

	public void setDipDictionaryTypeMapper(
			DipDictionaryTypeMapper dipDictionaryTypeMapper) {
		this.dipDictionaryTypeMapper = dipDictionaryTypeMapper;
	}
	
}
