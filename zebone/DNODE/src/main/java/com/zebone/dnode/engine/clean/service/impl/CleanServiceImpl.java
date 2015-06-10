package com.zebone.dnode.engine.clean.service.impl;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.clean.BusinessParse;
import com.zebone.dnode.engine.clean.DSRegister;
import com.zebone.dnode.engine.clean.DataConvert;
import com.zebone.dnode.engine.clean.DataConvertParse;
import com.zebone.dnode.engine.clean.SourceDataTransfer;
import com.zebone.dnode.engine.clean.exception.CleanException;
import com.zebone.dnode.engine.clean.pojo.ClearCloumn;
import com.zebone.dnode.engine.clean.pojo.ClearConfig;
import com.zebone.dnode.engine.clean.pojo.ClearLog;
import com.zebone.dnode.engine.clean.pojo.TableConfig;
import com.zebone.dnode.engine.clean.repository.CenterCleanRepository;
import com.zebone.dnode.engine.clean.repository.CleanRepository;
import com.zebone.dnode.engine.clean.repository.FetchingDataRepository;
import com.zebone.dnode.engine.clean.repository.impl.FetchingOracleDataRepositoryImpl;
import com.zebone.dnode.engine.clean.service.CleanService;
import com.zebone.dnode.engine.clean.util.Util;
import com.zebone.util.UUIDUtil;


@Service("cleanService")
public class CleanServiceImpl implements CleanService {
	
    private static final Log logger = LogFactory.getLog(CleanServiceImpl.class);
 
	@Autowired
	private CleanRepository cleanRepository;

	@Autowired
	private CenterCleanRepository centerCleanRepository;

	private static final String tableName = "C_DC_EHR_ALL_INFO";

	@Override
	public void exeucte(String taskId) {
		// TODO Auto-generated method stub
		ClearConfig clearConfig = cleanRepository.getClearConfig(taskId);
		if(clearConfig != null){
			TableConfig tableConfig = cleanRepository.getTableConfig(clearConfig.getTable_id());
			if(tableConfig != null){
				DataSource ds = null;
				try {
					/** 注册需要抓取的数据源  **/
					ds = new DSRegister().register(tableConfig.getDsObj());
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage(), e);
				}
				/** 获取需要清洗列的类型和内容  **/
				List<ClearCloumn> clearCloumns = cleanRepository.getClearCloumn(clearConfig.getId());
				FetchingDataRepository fdr = new FetchingOracleDataRepositoryImpl(ds);
				List<Map<String, Object>> dataMap = fdr.getFetchData(tableConfig.getTableName());
				clean(clearCloumns,dataMap,clearConfig.getId(),tableConfig.getTableName());
				if(dataMap != null && dataMap.size() > 0){
					SourceDataTransfer sdt = new SourceDataTransfer();
					List<String> sql = sdt.dataTansferToSql(tableName, dataMap);
					centerCleanRepository.execute(sql);
				}	
			}
		}
	}
	
	/**
	 * 清洗
	 * @param ccs
	 * @param dataMap
	 * @return
	 */
	private List<Map<String, Object>> clean(List<ClearCloumn> ccs, List<Map<String, Object>> dataMap, 
			String clearId, String tableName){
		List<ClearLog> clearLogs = new ArrayList<ClearLog>();
		for(ClearCloumn cc : ccs){
			/**列清洗类型 **/
			String clearType = cc.getClearType();
			/** 配置内容 **/
			String clearContent = cc.getClearContent();
			/** 列名称 **/
			String cloumnName = cc.getCloumnName();
			if("2".equals(clearType)){	
			    try {
					/** 数据转化  **/
					Object val = Util.getMapValue(dataMap, cloumnName);	
					/** 值不为空才转化 **/
				    if(val != null){
				    	DataConvertParse dcp = new DataConvertParse();
				    	DataConvert dc = dcp.parse(clearContent);
						Object cval = dc.convert(val);
						Util.setMapValue(dataMap, cloumnName, cval);
				    }
				}catch (CleanException e){
					ClearLog cl = new ClearLog();
					cl.setId(UUIDUtil.getUuid());
					cl.setClearType(clearType);
					cl.setCloumnName(cloumnName);
					cl.setTableName(tableName);
					cl.setErrCode(e.getErrorCode());
					cl.setErrDesc(e.getErrorDesc());
					cl.setClearId(clearId);	
					if(cl != null){
						clearLogs.add(cl);
					}
				}
			}else if("3".equals(clearType)){
				Object val = Util.getMapValue(dataMap, cloumnName);	
				if(val != null){
					try {
						new BusinessParse().parse(clearContent, val);
					} catch (CleanException e) {
						// TODO Auto-generated catch block
						ClearLog cl = new ClearLog();
						cl.setId(UUIDUtil.getUuid());
						cl.setClearType(clearType);
						cl.setCloumnName(cloumnName);
						cl.setTableName(tableName);
						cl.setErrCode(e.getErrorCode());
						cl.setErrDesc(e.getErrorDesc());
						cl.setClearId(clearId);
						if(cl != null){
							clearLogs.add(cl);
						}
					} 
				}
			}
			
			
		}	
		cleanRepository.saveClearLog(clearLogs);
		return dataMap;
	}

}
