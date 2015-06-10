package com.zebone.dnode.etl.load.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zebone.dnode.etl.load.convert.Convert;
import com.zebone.dnode.etl.load.pojo.LoadConfig;
import com.zebone.dnode.etl.load.pojo.LoadConfig.ColumnMapping;
import com.zebone.dnode.etl.load.pojo.LoadConfig.Covert;
import com.zebone.dnode.etl.load.pojo.LoadConfig.Load;
import com.zebone.dnode.etl.load.pojo.LoadConfig.Sql;
import com.zebone.dnode.etl.load.pojo.LoadConfig.TableMapping;
import com.zebone.dnode.etl.load.pojo.LoadParameter;
import com.zebone.dnode.etl.load.service.LoadService;

/**
 * 装载数据service
 * @author 陈阵 
 * @date 2014-2-20 下午3:36:21
 */
@Service("loadService")
public class LoadServiceImpl implements LoadService {
	
	private static final Log log = LogFactory.getLog(LoadServiceImpl.class);
	
	private static Map<String,List<String>> tablePk = new HashMap<String, List<String>>();
	
    
	@Resource(name="etlJdbcTemplate")
	private JdbcTemplate etlJdbcTemplate;
  
	public void loadData(LoadConfig loadConfig) {
		List<LoadParameter> loadParList = preLoadConfig(loadConfig);
		if(loadParList != null && loadParList.size() > 0){
			for(LoadParameter lp : loadParList){
				log.info(lp.getFromSql());
				String countSql = null;
				String fromSql = lp.getFromSql();
				Pattern pattern = Pattern.compile("^select(.*)from.*$",Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(fromSql);
				while(matcher.find()){
					String replaceStr = matcher.group(1);
					countSql = fromSql.replace(replaceStr, " count(*) ");
				}
	
                int dataCount = etlJdbcTemplate.queryForObject(countSql, Integer.class);
                int forNums = 1;
				int mod = dataCount % BATCH_NUM;
				if (mod == 0) {
					forNums = dataCount / BATCH_NUM;
				} else {
					forNums = dataCount / BATCH_NUM + 1;
				}
				String fQuerySql = getSkipSql(fromSql);
								
			    Map<String,String> toTableMap = lp.getToTable();
			    List<String> insertSql = new ArrayList<String>();
			    for(Map.Entry<String, String> entry : toTableMap.entrySet()) {
		           String tableName = entry.getKey();
		           String colName = entry.getValue();
		           
		           String repeat = StringUtils.repeat("?,", colName.split("\\,").length);
				   repeat = repeat.substring(0, repeat.length() - 1);
		           String sql = "insert into " + tableName + "(" + colName + ") values("+repeat+")";
		           insertSql.add(sql);
			    }
		
				for(int i = 0 ; i < forNums; i++){
					int start = i*BATCH_NUM+1;
					int end = (i+1)*BATCH_NUM;
					if (i == forNums - 1 && mod != 0) {
						end = i*BATCH_NUM + mod;
					}
					Object[] skip = new Object[]{end,start};
					
					List<Map<String, Object>> result = etlJdbcTemplate.queryForList(fQuerySql,skip);
					
				    for(Map<String,Object> rmap : result){
				    	insertOneData(rmap,insertSql);
					}
  
				}

			}
		}
	}

	
	@Transactional(value="transactionManager_etl")
	public void insertOneData(Map<String,Object> dataMap,List<String> insertSql){
		try{
   	 	Map<String,Object[]> insertPara = new HashMap<String, Object[]>();

		for(String sql : insertSql){
			Pattern pattern1 = Pattern.compile("^insert\\s+into\\s+(\\w+)\\((.*)\\)\\s+values.*$",Pattern.CASE_INSENSITIVE);
			Matcher matcher1 = pattern1.matcher(sql);
			while(matcher1.find()){
				String table = matcher1.group(1);	
				String cols = matcher1.group(2);
				
				List<String> pkList = new ArrayList<String>();
				if(tablePk.get(table) == null){
					List<Map<String,Object>> pkMap = etlJdbcTemplate.queryForList(" select A.Column_Name as pkcol from All_Constraints B left join All_Cons_Columns A on A.Constraint_Name = B.Constraint_Name Where B.Table_Name = ? And Constraint_Type = 'P' And B.OWNER = 'ETL'", table);
					for(Map<String,Object> mapData : pkMap){
						String colname = (String)mapData.get("pkcol");
						pkList.add(colname);
					}
					tablePk.put(table, pkList);
				}else{
					pkList = tablePk.get(table);
				}
				
				StringBuilder selectSqlSB = new StringBuilder("select count(*) from ").append(table);
				if(pkList.size() > 0){
					selectSqlSB.append(" where ");
				}
				Object[] selectSqlPara = new Object[pkList.size()]; 
			    for(int i = 0; i< pkList.size();i++){
			    	if(i == 0){
			    		selectSqlSB.append(pkList.get(i)).append(" =? ");
			    	}else{
			    		selectSqlSB.append(" and ").append(pkList.get(i)).append(" =? ");
			    	}
			    	selectSqlPara[i] = dataMap.get(pkList.get(i));
			    }
			    
			    int count  = etlJdbcTemplate.queryForObject(selectSqlSB.toString(), selectSqlPara,Integer.class);
				String[] col = cols.split(",");
			    if(count == 1){
				   Object[] paraObj = new Object[col.length + pkList.size()];
			       StringBuilder updateSqlSB = new StringBuilder("update ").append(table);
			       updateSqlSB.append(" set ");
			       for(int i=0;i<col.length;i++){
			    	   if(i == 0){
			    		   updateSqlSB.append(col[i]).append("=?");
			    	   }else{
			    		   updateSqlSB.append(",").append(col[i]).append("=?");
			    	   }
			    	   paraObj[i] = dataMap.get(col[i]);
			       }
			       if(pkList.size() > 0){
				       updateSqlSB.append(" where ");
			       }
			       for(int i = 0; i< pkList.size();i++){
			    		if(i == 0){
			    			updateSqlSB.append(pkList.get(i)).append(" =? ");
				    	}else{
				    		updateSqlSB.append(" and ").append(pkList.get(i)).append(" =? ");
				    	}
			    		paraObj[col.length+i]= dataMap.get(pkList.get(i));
			       }
			       insertPara.put(updateSqlSB.toString(), paraObj);
			    }else if(count == 0){
			    	Object[] paraObj = new Object[col.length];
					for(int k=0;k<col.length;k++){
						paraObj[k] = dataMap.get(col[k]);
					}
					insertPara.put(sql, paraObj);
			    }
				
			}
		}
		for(Map.Entry<String, Object[]> entry : insertPara.entrySet()) {
	           String sql = entry.getKey();
	           Object[] value = entry.getValue();
	           //print(sql,value);
	           etlJdbcTemplate.update(sql, value);
		}
		}catch(Exception e){
			 log.error(e.getMessage(),e);
			 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
	}
	
	private void  print(String sql,Object[] value){
		System.out.println(sql);
		for(int i=0;i<value.length;i++){
			System.out.print("###"+value[i]+"###");
		}
	}
	
	private String getSkipSql(String sql){
		return "SELECT * FROM(SELECT A.*,ROWNUM RN FROM ("+sql+") A WHERE ROWNUM <= ? )WHERE RN >= ?";
	}
	
	/**
	 * 重新装配加载参数
	 * @param loadConfig
	 * @author 陈阵 
	 * @date 2014-2-20 下午3:49:05
	 */
	private List<LoadParameter> preLoadConfig(LoadConfig loadConfig){
		List<LoadParameter> loadParList = new ArrayList<LoadParameter>();
		try {
			// TODO Auto-generated method stub
			Map<String,String> sqlMap = new HashMap<String, String>();
			List<Load> loadList = loadConfig.getLoad();
			for(Load load : loadList){
				List<Sql> sqlList = load.getSql();
				if(sqlList != null && sqlList.size() > 0){
					for(Sql sql: sqlList){
						String sqlId = sql.getId();
						String exeSql = sql.getSql().trim();
						sqlMap.put(sqlId, exeSql);
					}
				}
				
			    List<TableMapping> tableMappingList =load.getTableMapping();
			    if(tableMappingList != null && tableMappingList.size() > 0){
			    	for(TableMapping tm : tableMappingList){
				    	LoadParameter lp = new LoadParameter();
			    		Map<String,String> colMappingMap = new HashMap<String, String>();
			    		Map<String, List<Convert>> colConvertMap = new HashMap<String, List<Convert>>();
			    		String fid = tm.getFid();
			    	    Map<String,String> toTable = new HashMap<String, String>();
			    	    List<ColumnMapping> colMappingList =  tm.getColumnMapping();
			    	    if(colMappingList != null && colMappingList.size() > 0){
			    	    	for(ColumnMapping cm : colMappingList){
		    	    			List<Convert> convertList = new ArrayList<Convert>();
			    	    		/** 源字段  **/
			    	    		String from = cm.getFrom();
			    	    		/**目标字段  **/
			    	    		String to = cm.getTo();
			    	    		String cols[] = to.split("\\.");
			    	    		String values = toTable.get(cols[0]);
			    	    		if(values == null){
			    	    			values = cols[1];
			    	    		}else{
			    	    			values = values+","+cols[1]; 
			    	    		}
		    	    			toTable.put(cols[0], values);

			    	    		/** 获取转化类  **/
			    	    		List<Covert> covertList = cm.getConvert();
			    	    		if(covertList != null && covertList.size() > 0){
			    	    			for(Covert covert: covertList){
			    	    				 String covertClass = covert.getTclass();
			    	    				 if(StringUtils.isNotEmpty(covertClass)){
			    	    					 Convert convertClass = (Convert)ClassUtils.getClass(covertClass).newInstance();
				    	    				 convertList.add(convertClass);
			    	    				 }	
			    	    			}
			    	    		}
			    	    		colConvertMap.put(to, convertList);
			    	    		colMappingMap.put(to, from);
			    	    	}
			    	    }
			    	    /** 获取数据来源sql **/
				        lp.setFromSql(sqlMap.get(fid)) ;
				        /** 目标数据表名 参数**/
				        lp.setToTable(toTable);
				        /** 原表数据到目标表需要的转化  **/
				        lp.setColConvertMap(colConvertMap);
				        loadParList.add(lp);
			    	}		    	
			    }
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		return loadParList;
	}
	
	
	public  static  void main(String[] args){
		try {
			ClassPathResource loadConfCpr = new ClassPathResource("com/zebone/dnode/etl/load/config/loadConf.xml");
			XStream xs = new XStream(new StaxDriver());
			xs.processAnnotations(LoadConfig.class);
			LoadConfig loadData = (LoadConfig) xs.fromXML(loadConfCpr.getInputStream());
			new LoadServiceImpl().loadData(loadData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
