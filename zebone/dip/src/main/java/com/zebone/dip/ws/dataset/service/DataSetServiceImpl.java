package com.zebone.dip.ws.dataset.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.io.xml.DomDriver;

@WebService(endpointInterface = "com.zebone.dip.ws.dataset.service.DataSetService")
@Service("dataSetService")
public class DataSetServiceImpl implements DataSetService {

	private static final Log log = LogFactory.getLog(DataSetServiceImpl.class);

	private XStream xs = new XStream(new DomDriver());

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public String requestDataSet(String requestParam) {
		xs.processAnnotations(Request.class);
		Request request = (Request) xs.fromXML(requestParam);
		DataSet ds = null;
		Respnose response = new Respnose();
		try {
			ds = jdbcTemplate.queryForObject(
					"select * from P_DATASET_CONF where DATASET_CODE = ?",
					new Object[] { request.getDataSetCode() },
					new DataSetMappper());
			System.out.println("2..."+ds);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		if (ds == null) {
			response.setIsSuccess("0");
			response.setErrorCode("001");
			response.setErrorMsg("数据集不存在");
		} else {
			List<DataSetMapping> dataSetMappingList = null;
			try {
				dataSetMappingList = jdbcTemplate.query(
						"select * from P_DATASET_MAPPING where DATASET_ID = ?",
						new Object[] { ds.getId() },
						new DataSetMappingMappper());
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			if (dataSetMappingList != null && dataSetMappingList.size() == 0) {
				response.setIsSuccess("0");
				response.setErrorCode("002");
				response.setErrorMsg("数据集[" + ds.getDataSetName() + ","
						+ ds.getDataSetCode() + "]值不存在");
			} else {
				response.setIsSuccess("1");
				Items items = new Items();
				items.setDataSet(ds);
				DataSetMappings dsm = new DataSetMappings();
				dsm.setDataSetMapping(dataSetMappingList);
				
				items.setDataSetMappings(dsm);
				response.setItems(items);
			}
		}
		xs.processAnnotations(Respnose.class);
		String returnXml = xs.toXML(response);
	    
		return returnXml;
	}

	@XStreamAlias("request")
	public static class Request {

		private String dataSetCode;

		public String getDataSetCode() {
			return dataSetCode;
		}

		public void setDataSetCode(String dataSetCode) {
			this.dataSetCode = dataSetCode;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Request [dataSetCode=");
			builder.append(dataSetCode);
			builder.append("]");
			return builder.toString();
		}
		
		

	}

	@XStreamAlias("response")
	public static class Respnose {

		private String isSuccess;
		private String errorCode;
		private String errorMsg;
		private Items items;
		public String getIsSuccess() {
			return isSuccess;
		}
		public void setIsSuccess(String isSuccess) {
			this.isSuccess = isSuccess;
		}
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		public String getErrorMsg() {
			return errorMsg;
		}
		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}
		public Items getItems() {
			return items;
		}
		public void setItems(Items items) {
			this.items = items;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Respnose [isSuccess=");
			builder.append(isSuccess);
			builder.append(", errorCode=");
			builder.append(errorCode);
			builder.append(", errorMsg=");
			builder.append(errorMsg);
			builder.append(", items=");
			builder.append(items);
			builder.append("]");
			return builder.toString();
		}
        
		
	    
	}
	
	@XStreamAlias("dataSetMappings")
	public static class DataSetMappings{
		@XStreamImplicit(itemFieldName = "dataSetMapping")
		private List<DataSetMapping> dataSetMapping;
		
		public List<DataSetMapping> getDataSetMapping() {
			return dataSetMapping;
		}

		public void setDataSetMapping(List<DataSetMapping> dataSetMapping) {
			this.dataSetMapping = dataSetMapping;
		}

	}

	@XStreamAlias("items")
	public static class Items {

		private DataSet dataSet;
        
		private DataSetMappings dataSetMappings;
	
		public DataSet getDataSet() {
			return dataSet;
		}

		public void setDataSet(DataSet dataSet) {
			this.dataSet = dataSet;
		}

		public DataSetMappings getDataSetMappings() {
			return dataSetMappings;
		}

		public void setDataSetMappings(DataSetMappings dataSetMappings) {
			this.dataSetMappings = dataSetMappings;
		}
		
		
	}

	
	public static class DataSetPara {

		private String dataSetCode;

		public String getDataSetCode() {
			return dataSetCode;
		}

		public void setDataSetCode(String dataSetCode) {
			this.dataSetCode = dataSetCode;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("DataSetPara [dataSetCode=");
			builder.append(dataSetCode);
			builder.append("]");
			return builder.toString();
		}
        
	}

	@XStreamAlias("dataSet")
	public static class DataSet {

		@XStreamOmitField
		private String id;

		private String dataSetName;

		private String dataSetCode;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDataSetName() {
			return dataSetName;
		}

		public void setDataSetName(String dataSetName) {
			this.dataSetName = dataSetName;
		}

		public String getDataSetCode() {
			return dataSetCode;
		}

		public void setDataSetCode(String dataSetCode) {
			this.dataSetCode = dataSetCode;
		}

		
		
		

	}

	@XStreamAlias("dataSetMapping")
	public static class DataSetMapping {
		@XStreamOmitField
		private String id;
		@XStreamOmitField
		private String fieldId;
		@XStreamOmitField
		private String dataSetId;
		private String fieldName;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getFieldId() {
			return fieldId;
		}

		public void setFieldId(String fieldId) {
			this.fieldId = fieldId;
		}

		public String getDataSetId() {
			return dataSetId;
		}

		public void setDataSetId(String dataSetId) {
			this.dataSetId = dataSetId;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("DataSetMapping [id=");
			builder.append(id);
			builder.append(", fieldId=");
			builder.append(fieldId);
			builder.append(", dataSetId=");
			builder.append(dataSetId);
			builder.append(", fieldName=");
			builder.append(fieldName);
			builder.append("]");
			return builder.toString();
		}
        
		
	}

	private static class DataSetMappper implements RowMapper<DataSet> {

		@Override
		public DataSet mapRow(ResultSet paramResultSet, int paramInt)
				throws SQLException {
			// TODO Auto-generated method stub
			DataSet ds = new DataSet();
			String id = paramResultSet.getString("ID_");
			String dataSetName = paramResultSet.getString("DATASET_NAME");
			String dataSetCode = paramResultSet.getString("DATASET_CODE");
			ds.setId(id);
			ds.setDataSetCode(dataSetCode);
			ds.setDataSetName(dataSetName);
			System.out.println("@@@@@@"+dataSetCode+"xxxx"+dataSetName);
			System.out.println("1..."+ds);
			return ds;
		}

	}

	private static class DataSetMappingMappper implements
			RowMapper<DataSetMapping> {

		@Override
		public DataSetMapping mapRow(ResultSet paramResultSet, int paramInt)
				throws SQLException {
			// TODO Auto-generated method stub
			DataSetMapping dsm = new DataSetMapping();
			String id = paramResultSet.getString("ID_");
			String fieldId = paramResultSet.getString("FIELD_ID");
			String dataSetId = paramResultSet.getString("DATASET_ID");
			String fieldName = paramResultSet.getString("FIELD_NAME");
			dsm.setId(id);
			dsm.setFieldId(fieldId);
			dsm.setDataSetId(dataSetId);
			dsm.setFieldName(fieldName);

			return dsm;
		}

	}

}
