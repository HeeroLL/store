package com.zebone.dip.release.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.log4j.Logger;


/**
 * JDBC操作类
 * @author YinCM
 *
 */
public class JdbcOperation {
	
	private static Logger logger = Logger.getLogger(JdbcOperation.class);
	
	
	public static Connection getConnection(String dbDriver, String dbUrl, String dbUser, String dbPassword) throws Exception{
		  Connection con = null;
		  String url = dbUrl;
		  String driver = dbDriver;
		  String user = dbUser;
		  String pass = dbPassword;
		  try{
			  Class.forName(driver);
			  con = DriverManager.getConnection(url, user, pass);
			  logger.debug(dbDriver+""+dbUrl+":"+dbUser+"----"+"连接成功获取！");
		  }
		  catch (Exception e){
			  con=null;
			  logger.error(dbDriver+""+dbUrl+":"+dbUser+"----"+"该数据库连接有问题，请检查！"+e.getMessage());
			  e.printStackTrace();
			  throw e;
		  }
		  return con;
	}
	
	
	/**
	 * 更新库，包含插入操作
	 * @param sourceCon
	 * @param targetCon
	 * @param dbTableNames 要更新的表名称
	 * @param previousUpdateTimestamp 之前更新的时间格式为： “25-9-2010 15:57:30”
	 * @throws SQLException 
	 */
	public static void updateTargetDBTablesCommon(Connection sourceCon, Connection targetCon, String[] dbTableNames, String previousUpdateTimestamp) throws SQLException{
		 Statement st = null;
		 ResultSet rs = null;
		 //用于插入target库中的表
		 PreparedStatement insertSingleRowStatement = null;
		 //用于更新target库中的表
		 PreparedStatement updateSingleRowStatement = null;
		 try{
			for(String dbTableName : dbTableNames){
			  logger.debug("正在更新表:"+dbTableName);
			  //获取表字段，然后拼装成update语句
			  st = sourceCon.createStatement();
			  rs = st.executeQuery("SELECT * FROM "+dbTableName+" where 1=2");
			  ResultSetMetaData md = rs.getMetaData();
			  int col = md.getColumnCount();
			  //更新sql语句
			  StringBuilder updateStringBuilder = new StringBuilder();
			  updateStringBuilder.append("update "+dbTableName+" set ");
			  //插入sql语句
			  StringBuilder insertStringBuilder = new StringBuilder();
			  insertStringBuilder.append("insert into " + dbTableName +" values (");
			  
//			  REMARK:VARCHAR2
//			  ORDER_NO:NUMBER
//			  TIMESTAMP:TIMESTAMP
			  String primaryKey = null;
			  //字段名称数组,二维数组，第一位为名称，第二位为类型
			  String[][] fieldArray = new String[col][2];
			  //获取主键
			  fieldArray[0][0] = md.getColumnName(1);
			  fieldArray[0][1] = md.getColumnTypeName(1);
			  insertStringBuilder.append("?,");
			  //组装updateStringBuilder和insertStringBuildrer
			  for(int i = 2; i <= col; i++){
				  fieldArray[i-1][0] = md.getColumnName(i);
				  fieldArray[i-1][1] = md.getColumnTypeName(i);
				  updateStringBuilder.append(fieldArray[i-1][0]+"=?,");
				  insertStringBuilder.append("?,");
			  }
			  //最后一个逗号的处理
			  //更新
			  String tempStr = updateStringBuilder.substring(0,updateStringBuilder.length()-1);
			  String updateString = tempStr+" where "+fieldArray[0][0]+"=?";
			  logger.debug(dbTableName+"--更新语句生成："+updateString);
			  //新增
			  String insertStr = insertStringBuilder.substring(0,insertStringBuilder.length()-1)+")"; 
			  logger.debug(dbTableName+"--插入语句生成："+insertStr);
			  //------------以上updateString为拼装好的prepareStatement语句
			  //接下来获取原有数据，填充prepareStatement语句中的变量
			  //PreparedStatement updateSt = targetCon.prepareStatement(updateString);
			  //timestamp 格式 “25-9-2010 15:57:30”
			  rs = st.executeQuery("SELECT * FROM "+dbTableName +" where timestamp>=TO_DATE('"+previousUpdateTimestamp+"','YYYY-MM-DD HH24:MI:SS')");
			  //rs = st.executeQuery("SELECT * FROM "+dbTableName);
//			  int k = 0;
			  logger.debug(dbTableName+"开始执行同步操作~");
			  while(rs.next()){
				  //测试用，只需测试两条数据
//				  if(k==2){
//					  break;
//				  }
//				  k++;
				  //准备数据statement
				  //用于插入target库中的表
				  insertSingleRowStatement = targetCon.prepareStatement(insertStr);
				  //检查是否在库中，在则更新，不在则插入
				  //插入，最后一位，因为插入id在最前面，不要移位
				  try{
					  logger.debug("准备insert的statemente参数");
					  prepareParamToStatement(insertSingleRowStatement, fieldArray,rs,0);
					  insertSingleRowStatement.execute();
					  logger.debug("上条statement执行为插入操作！");
				  }catch(SQLException e){
					  //如果违反了唯一性约束，则更新
					 if(e.getErrorCode()==1){
						  //准备数据statement
						 logger.debug("准备update的statemente参数");
						  //用于更新target库中的表
						  updateSingleRowStatement = targetCon.prepareStatement(updateString);
						  //向preparedStatement中载入对应的值和对应的类型
						  prepareParamToStatement(updateSingleRowStatement,fieldArray,rs,1);
						  //载入主键
						  String str = rs.getString(fieldArray[0][0]);
						  updateSingleRowStatement.setString(col, str);
						  //此处要考虑是否可以采用事物方式提交
						  updateSingleRowStatement.executeUpdate();
						  logger.debug("上条statement执行为更新操作！");
					 }
				  }
			  }
			}
		  }
		  catch (SQLException s){
			  s.printStackTrace();
			  System.out.println("SQL statement is not executed!");
			  logger.error("上条数据同步过程中，数据库执行出错，请检查！");
			  throw s;
		  }finally{
			  JdbcUtils.closeConnection(sourceCon);
			  JdbcUtils.closeConnection(targetCon);
			  JdbcUtils.closeResultSet(rs);
			  JdbcUtils.closeStatement(st);
			  JdbcUtils.closeStatement(insertSingleRowStatement);
			  JdbcUtils.closeStatement(updateSingleRowStatement);
		  }
		
	}
	/**
	 * 向preparedStatement中载入对应的值和对应的类型
	 * @param ps 需要载入值的PreparedStatement
	 * @param fieldArray 值和值类型的二维数组
	 * @param rs  resultset 用于获取值
	 * @param arrayStartIndex 
	 * @return
	 * @throws SQLException
	 */
	public static void prepareParamToStatement(PreparedStatement ps, String[][] fieldArray, ResultSet rs, int arrayStartIndex) throws SQLException{
		//行中的位置，用于定位preparedStatement中的位置
		int j = 1;
		  //fieldArray 二维数组，第二维是类型
		
		for(int i=arrayStartIndex; i<fieldArray.length;i++){  
		  //value
		  //String strddd = rs.getString(fieldArray[i][0]);
		  if(fieldArray[i][1].contains("VARCHAR")){
			  String str = rs.getString(fieldArray[i][0]);
			  ps.setString(j, str);
			  logger.debug(str+",");
		  }else if(fieldArray[i][1].equals("NUMBER")){
			  int str = rs.getInt(fieldArray[i][0]);
			  ps.setInt(j, str);
			  logger.debug(str+",");
		  }else if(fieldArray[i][1].equals("TIMESTAMP")){
			  Timestamp str = rs.getTimestamp(fieldArray[i][0]);
			  ps.setTimestamp(j, str);
			  logger.debug(str+",");
		  }
		  //行中的位置递增
		  j++;
	   }
	   logger.debug("statemente参数准备完成");
	   
	}
	
}


