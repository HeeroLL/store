package com.zebone.dip.release.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


/**
 * 字典操作类
 * @author YinCM
 *
 */
public class DictSyncOperation {
	/**
	 * 更新库，包含插入操作
	 * @param sourceCon
	 * @param targetCon
	 * @param dbTableNames 要更新的表名称
	 * @param previousUpdateTimestamp 之前更新的时间格式为： “25-9-2010 15:57:30”
	 * @throws SQLException 
	 */
	public static void updateTargetDBTablesCommon(Connection sourceCon, Connection targetCon, String[] dictTypeIds, String previousUpdateTimestamp) throws SQLException{
		 Statement st = null;
		 ResultSet rs = null;
		 
		 //用于插入P_DICTIONARY表
		 PreparedStatement insertSingleRowStatement = null;
		 //用于更新P_DICTIONARY表
		 PreparedStatement updateSingleRowStatement = null;
		 try{
		  //获取操作
		  st = sourceCon.createStatement();
			 
		  //更新sql语句
		  String  updateString="update P_DICTIONARY set dict_name = ?, dict_code=?, name_pinyin = ?, name_jianpin = ?, remark =?,  dicttype_id =?,order_no = ?,timestamp=?,is_deleted=? where dict_id =?";
		  
		  //插入sql语句
		  String insertStr = "insert into P_DICTIONARY values (?,?,?,?,?,?,?,?,?,?)";
			  
		  //------------以上updateString为拼装好的prepareStatement语句
		  //接下来获取原有数据，填充prepareStatement语句中的变量
		  //PreparedStatement updateSt = targetCon.prepareStatement(updateString);
		  //timestamp 格式 “25-9-2010 15:57:30”
		  //dicttype_id格式为: 12341&&性别
		  for(String dicttype_id : dictTypeIds){
			  rs = st.executeQuery("SELECT * FROM P_DICTIONARY where dicttype_id='"+dicttype_id.split("\\$\\$")[0]+"' and timestamp>=TO_DATE('"+previousUpdateTimestamp+"','YYYY-MM-DD HH24:MI:SS')");
			  //rs = st.executeQuery("SELECT * FROM "+dbTableName);
//			  int k = 0;
			  while(rs.next()){
				  //测试用，只需测试两条数据
//				  if(k==2){
//					  break;
//				  }
//				  k++;
				  //准备数据statement
				  //用于插入target库中的表
				  insertSingleRowStatement = targetCon.prepareStatement(insertStr);
				  System.out.println(insertStr);
				  //检查是否在库中，在则更新，不在则插入
				  //插入，最后一位，因为插入id在最前面，不要移位
				  try{
					  prepareParamToStatementInsert(insertSingleRowStatement, rs);
					  insertSingleRowStatement.execute();
				  }catch(SQLException e){
					  //如果违反了唯一性约束，则更新
					 if(e.getErrorCode()==1){
						  //准备数据statement
						  //用于更新target库中的表
						  updateSingleRowStatement = targetCon.prepareStatement(updateString);
						  //向preparedStatement中载入对应的值和对应的类型
						  prepareParamToStatementUpdate(updateSingleRowStatement,rs);
						  //此处要考虑是否可以采用事物方式提交
						  updateSingleRowStatement.executeUpdate();
					 }
				  }
			  }
			}
		  }
		  catch (SQLException s){
			  s.printStackTrace();
			  System.out.println("SQL statement is not executed!");
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
	 * 更新
	 * @param ps
	 * @param rs
	 * @throws SQLException
	 */
	public static void prepareParamToStatementUpdate(PreparedStatement ps, ResultSet rs) throws SQLException{
		ps.setString(1,rs.getString("dict_name"));
		ps.setString(2,rs.getString("dict_code"));
		ps.setString(3,rs.getString("name_pinyin"));
		ps.setString(4,rs.getString("name_jianpin"));
		ps.setString(5,rs.getString("remark"));
		ps.setString(6,rs.getString("dicttype_id"));
		ps.setString(7,rs.getString("order_no"));
		ps.setTimestamp(8,rs.getTimestamp("timestamp"));
		ps.setInt(9,rs.getInt("is_deleted"));
		ps.setString(10,rs.getString("dict_id"));
	}
	
	/**
	 * 插入
	 * @param ps
	 * @param rs
	 * @throws SQLException
	 */
	public static void prepareParamToStatementInsert(PreparedStatement ps, ResultSet rs) throws SQLException{
		ps.setString(1,rs.getString("dict_id"));
		ps.setString(2,rs.getString("dict_name"));
		ps.setString(3,rs.getString("dict_code"));
		ps.setString(4,rs.getString("name_pinyin"));
		ps.setString(5,rs.getString("name_jianpin"));
		ps.setString(6,rs.getString("remark"));
		ps.setString(7,rs.getString("dicttype_id"));
		ps.setString(8,rs.getString("order_no"));
		ps.setTimestamp(9,rs.getTimestamp("timestamp"));
		ps.setInt(10,rs.getInt("is_deleted"));
		
	}
}


