package com.zebone.dnode.engine.analyze.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zebone.dnode.engine.analyze.AnalyzeException;
import com.zebone.dnode.engine.analyze.dao.InsertDataDao;
import com.zebone.dnode.engine.analyze.vo.AnalyzeDataInfo;



/**
 * 业务数据存储服务
 * @author songjunjie
 * @version 2013-8-18 下午03:04:36
 */
@Service
public class BizDataStorageServiceImpl implements BizDataStorageService{
	private static final Log log = LogFactory.getLog(BizDataStorageServiceImpl.class);
	
	@Resource(name = "insertDataDao")
	private InsertDataDao insertDataDao;
	//业务库中文档编号字段的名字
	@Value("${doc_no_column}")
	private String DOC_NO_COLUMN;
	
	/**
	 * 保存数据到业务库。
	 * @param analyzeDataList 解析数据信息列表
	 */
	@Transactional(value = "transactionManager_dc", rollbackFor = Throwable.class)
	public void saveToDataBase(List<AnalyzeDataInfo> analyzeDataList){
		for(AnalyzeDataInfo info : analyzeDataList){
			String docNo = info.getDocNo();
			String tableName = info.getTableName();
			List<String> columnList = info.getColumnList();
			List<List<String>> paramList = info.getParamList();
			// 删除老的数据
			try {
				insertDataDao.deleteData(tableName, DOC_NO_COLUMN, docNo);
			} catch (Exception e) {
				String errorDesc = "删除表"+tableName+"中"+DOC_NO_COLUMN+"="+docNo+"的信息时出错";
				log.error(errorDesc,e);
				AnalyzeException analyzeException = new AnalyzeException(e);
				analyzeException.setErrorDesc(errorDesc);
				throw analyzeException;
			}
			
			List<List<String>> rowList = new ArrayList<List<String>>();
			int len = paramList.get(0).size();
			//循环参数信息，以前是同一个字段的信息保存到一个列表里面，现在要将一组字段信息对应的
			//参数取出。
			for(int i=0;i<len;i++){
				List<String> row = new ArrayList<String>();
				for(List<String> cellList : paramList){
					String cell = null;
					if(cellList.size()>=(i+1)){
						cell = cellList.get(i);
					}
					row.add(cell);
				}
				rowList.add(row);
			}
			
			try {
				for(List<String> row : rowList){
					insertDataDao.insertData(tableName, columnList, row);
				}
			} catch (Exception e) {
				String errorDesc = "保存数据到表"+tableName+"中时出错。文档编号：" + docNo;
				log.error(errorDesc,e);
				AnalyzeException analyzeException = new AnalyzeException(e);
				analyzeException.setErrorDesc(errorDesc);
				throw analyzeException;
			}
		}
	}
}
