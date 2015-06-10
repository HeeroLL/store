package  com.zebone.dip.tool.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zebone.dip.tool.vo.DictionaryField;
import com.zebone.dip.tool.vo.DictionaryType;
import com.zebone.dip.tool.vo.DocFieldInfo;
import com.zebone.dip.tool.vo.DocInfo;

/**
 * 获取数据库中数据
 * 
 * @author YinCM
 * @since
 */
public class DatabaseSearch {
	public static Connection connection=null;
	
	public static Connection getConnection(){
		if(connection==null){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("Oracle JDBC Driver Registered!");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.90.6:1521:ZB", "dip_jx","dip_jx");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	public static void closeConnection(){
		try {
			if(connection!=null)
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Oracle JDBC Driver closed!");
	}
	
	/**
	 * 获取文档
	 * @return
	 */
	public static List<DocInfo> getDocInfoList(String docTypeCode, String docVersion) {
		List<DocInfo> docInfoList = new ArrayList<DocInfo>();
		Connection connection = null;
		try {
			connection = getConnection();
			if (connection != null) {
				String sqlstr = "select * from P_DOC_CONF where doc_type_code=?";
				if(docVersion==null || docVersion.trim().equals("")){
					sqlstr+="and rownum = 1";
				}else{
					
					sqlstr +="and doc_version=?";
				}
				PreparedStatement ps = connection.prepareStatement(sqlstr);
				ps.setString(1, docTypeCode);
				if(sqlstr.contains("doc_version")){
					ps.setString(2, docVersion);
				}
				ResultSet result = ps.executeQuery();
				while(result.next()){
					DocInfo docInfo = new DocInfo();
					docInfo.setId(result.getString("ID_"));
					docInfo.setDocName(result.getString("DOC_NAME"));
					docInfo.setDocTypeCode(result.getString("DOC_TYPE"));
					docInfo.setDocXML(result.getString("DOC_XML"));
					docInfo.setDocVersion(result.getString("DOC_VERSION"));
					docInfoList.add(docInfo);
				}
				result.close();
				ps.close();
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return docInfoList;
	}
	
	/**
	 * 获取文档
	 * @return
	 */
	public static List<DocInfo> getDocInfoList() {
		List<DocInfo> docInfoList = new ArrayList<DocInfo>();
		Connection connection = null;
		try {
			connection = getConnection();
			if (connection != null) {
				PreparedStatement ps = connection.prepareStatement("select * from P_DOC_CONF");
				ResultSet result = ps.executeQuery();
				while(result.next()){
					DocInfo docInfo = new DocInfo();
					docInfo.setId(result.getString("ID_"));
					docInfo.setDocName(result.getString("DOC_NAME"));
					docInfo.setDocTypeCode(result.getString("DOC_TYPE"));
					docInfo.setDocXML(result.getString("DOC_XML"));
					docInfo.setDocVersion(result.getString("DOC_VERSION"));
					docInfoList.add(docInfo);
				}
				result.close();
				ps.close();
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return docInfoList;
	}
	
	/**
	 * 获取文档拥有的字段
	 * @return
	 */
	public static List<DocFieldInfo> getDocFieldInfo(String docId) {
		List<DocFieldInfo> docInfoList = new ArrayList<DocFieldInfo>();
		Connection connection = null;
		try {
		
			connection = getConnection();

			if (connection != null) {
				PreparedStatement ps = connection.prepareStatement("select * from P_DOC_MAPPING m left join P_FIELD_CONF f on m.field_id=f.id_  left join  p_dictionary_type dt  on f.field_value=dt.type_id  where xpath like '%/ClinicalDocument/structuredBody/%' and doc_id='" + docId + "' order by XPATH asc") ;
				ResultSet result = ps.executeQuery();
				while(result.next()){
					DocFieldInfo docInfo = new DocFieldInfo();
					docInfo.setFieldName(result.getString("FIELD_NAME"));
					docInfo.setFieldCode(result.getString("FIELD_CODE"));
					docInfo.setFieldFormat(result.getString("FIELD_FORMAT"));
					docInfo.setFieldType(result.getString("FIELD_TYPE"));
					docInfo.setFieldValue(result.getString("FIELD_VALUE"));
					docInfo.setFieldValueName(result.getString("FIELD_VALNAME"));
					docInfo.setTypeCode(result.getString("TYPE_CODE"));
					docInfo.setIsOnly(result.getString("IS_ONLY"));
					docInfo.setIsSelect(result.getString("IS_SELECT"));
					docInfo.setRepeat(result.getString("REPEAT"));
					docInfo.setXpath(result.getString("XPATH"));
					
					/*if(docInfo.getFieldName()==null || docInfo.getFieldName().trim().equals("") || docInfo.getXpath()==null || !docInfo.getXpath().contains("@name") ){
						continue;
					}*/
					System.out.print(docInfo.getFieldName()+";");
					docInfoList.add(docInfo);
				}
				result.close();
				ps.close();
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return docInfoList;
	}
	
	
	
	/**
	 * 获取单个字典类型
	 * @return
	 */
	public static DictionaryType getDictionaryType(String dicttype_id) {
		DictionaryType dt = new DictionaryType();
		Connection connection = null;
		try {
			connection = getConnection();
			if (connection != null) {
				PreparedStatement ps = connection.prepareStatement("select TYPE_CODE,STANDARD_CODE,TYPE_NAME from P_DICTIONARY_TYPE where type_id='"+dicttype_id+"'");
				ResultSet result = ps.executeQuery();
				while(result.next()){
					dt.setStandardCode(result.getString("STANDARD_CODE"));
					dt.setTypeName(result.getString("TYPE_NAME"));
					dt.setTypeCode(result.getString("TYPE_CODE"));
				}
				result.close();
				ps.close();
			} else {
				System.out.println("Failed to make connection!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return dt;
	}
	
	/**
	 * 获取单个字典类型
	 * @return
	 */
	public static List<DictionaryField> getDictionaryField(String dicttype_id) {
		List<DictionaryField> dtList = new ArrayList<DictionaryField>();
		Connection connection = null;
		try {
			connection = getConnection();
			if (connection != null) {
				PreparedStatement ps = connection.prepareStatement("select * from P_DICTIONARY t where dicttype_id='"+dicttype_id+"'");
				ResultSet result = ps.executeQuery();
				while(result.next()){
					DictionaryField df = new DictionaryField();
					df.setMark(result.getString("REMARK"));
					df.setName(result.getString("DICT_NAME"));
					df.setValue(result.getString("DICT_CODE"));
					dtList.add(df);
				}
				result.close();
				ps.close();
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return dtList;
	}
	
	/*public static void  main(String args[]) throws SQLException{
		getDocInfo();
	}*/
}
