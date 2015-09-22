package com.zebone.dnode.etl.extract.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zebone.dnode.etl.extract.pojo.ExtractConfig;
import com.zebone.dnode.etl.extract.pojo.JdbcParameter;
import com.zebone.dnode.etl.extract.service.ExtractService;

@Service("extractService")
public class ExtractServiceImpl implements ExtractService {

	private static final Log log = LogFactory.getLog(ExtractServiceImpl.class);
	
	private static final int getRepeatNum = 1;
	
	private static final int iuRepeatNum = 1;
	
	
	private String getSkipSql(String sql,String url){
		String rSql = null;
		if(url.indexOf("jdbc:oracle:thin") != -1){
			rSql = "SELECT * FROM(SELECT A.*,ROWNUM RN FROM ("+sql+") A WHERE ROWNUM <= ? )WHERE RN >= ?";
		}
		return rSql;
	}
	
	
    private List<String> getTableCol(Connection con,String table,String dbSchema){
    	List<String> tableColList = new ArrayList<String>();
    	tableColList.add("ID_");
    	try {
			ResultSet rs = con.getMetaData().getColumns(null, dbSchema, table, null);
			while(rs.next()){
				tableColList.add(rs.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
    	return tableColList;
    }
    
    private String getInsertColStr(Connection con,String table,String dbSchema){
        List <String> tableColList = getTableCol(con, table,dbSchema);
        String insertColStr = tableColList.toString().replace("[", "(").replace("]", ")");
        return insertColStr;
        
    }
	
	private Connection getCon(JdbcParameter jdbcParameter){
		System.out.println(jdbcParameter);
		Connection con = null;
		try {
			String url = jdbcParameter.getUrl();
			if(url.indexOf("jdbc:oracle:thin") != -1){
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			con = DriverManager.getConnection(
					jdbcParameter.getUrl(), jdbcParameter.getUserName(),
					jdbcParameter.getPassword());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(),e);
		}
		return con;
	}
	

	@Override
	public void extractDataInDB(ExtractConfig extractConfig) {
			/*
			Connection conFrom = null;
			Connection conTo = null;
			try {
				QueryRunner run = new QueryRunner();

				Map<String, Connection> dbConMap = new HashMap<String, Connection>();
				Map<String,String> dbUrlMap = new HashMap<String, String>();
				Map<String,String> dbSchemaMap = new HashMap<String, String>();
				
				List<JdbcParameter> jdbcParameterList = extractConfig.getJdbcParameter();

				for (JdbcParameter jdbcParameter : jdbcParameterList) {
					dbConMap.put(jdbcParameter.getName(), getCon(jdbcParameter));
					dbUrlMap.put(jdbcParameter.getName(), jdbcParameter.getUrl());
					dbSchemaMap.put(jdbcParameter.getName(), jdbcParameter.getUserName());
				}
				
				List<TableParameter> tpList = extractConfig.getExtractParameter().getTableParameter();
				for(TableParameter tp : tpList){
					String fromDataSource = tp.getFromDataSource();
					String toDataSource = tp.getToDataSource();

					conFrom = dbConMap.get(fromDataSource);
					conTo = dbConMap.get(toDataSource);
	                String fromDbSchema = dbSchemaMap.get(fromDataSource);
	                
					String fromTableName = tp.getFromtTableName();
				    String toTableName =   tp.getToTableName();
					
					String insertColStr = getInsertColStr(conFrom,fromTableName,fromDbSchema);

					String tableCountQuery = "SELECT COUNT(*) AS count FROM " + fromTableName + " where substr(HR_NO,0,2)='19'";
					Map<String,Object> tableCountMap = run.query(conFrom,tableCountQuery, new MapHandler());
					BigDecimal count = (BigDecimal)tableCountMap.get("count");
					int dataCount = count.intValue();
					int forNums = 1;
					int mod = dataCount % BATCH_NUM;
					if (mod == 0) {
						forNums = dataCount / BATCH_NUM;
					} else {
						forNums = dataCount / BATCH_NUM + 1;
					}
					String fQuerySql = "SELECT * FROM " + fromTableName + " where substr(HR_NO,0,2)='19'";

					for(int i = 0 ; i < forNums; i++){
						int start = i*BATCH_NUM+1;
						int end = (i+1)*BATCH_NUM;
						if (i == forNums - 1 && mod != 0) {
							end = i*BATCH_NUM + mod;
						}
						Object[] skip = new Object[]{end,start};
						int getNum = 1 ;
						List<Object[]> fromResult = null;	
						do{
							try{			
								String skipSql = getSkipSql(fQuerySql,dbUrlMap.get(fromDataSource));
								fromResult = run.query(conFrom, skipSql, new ArrayListHandler(),skip);
								getNum = getRepeatNum+1;		
							}catch(Exception e){
								log.error("get data from table->" + fromTableName + ":["+start+","+end+"] error" ,e);
								getNum++;
							}
						}while(getNum <= getRepeatNum);
						
						int iuNum = 1;
						do{
							try{
								int colNum = fromResult.get(0).length;
								int dataNum = end-start+1;
								Object[][] params = new Object[dataNum][colNum];
								for(int m = 0 ;m < dataNum; m++){
									Object[] result= fromResult.get(m);
									for(int n = 0 ; n < result.length - 1; n++){
										params[m][0] = Identities.uuid();
										params[m][n+1] = result[n];
									}
								}
								String repeat = StringUtils.repeat("?,", colNum);
								repeat = repeat.substring(0, repeat.length() - 1);
								String tInsertSql = "INSERT INTO " + toTableName + insertColStr + " VALUES("+ repeat + ")";
					            run.batch(conTo, tInsertSql, params);
					            iuNum = iuRepeatNum + 1;
							}catch(Exception e){
								log.error("insert data to table->" + toTableName + ":["+start+","+end+"] error" ,e);
					            iuNum++;
							}
						}while(iuNum <= iuRepeatNum);	
						log.info(fromTableName+"->"+toTableName + ": update ok");
					}	
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(),e);
			}finally{
					try {
						DbUtils.close(conFrom);
						DbUtils.close(conTo);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage(),e);
					}				
			}
			*/
	}
}
