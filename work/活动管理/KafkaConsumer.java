package com.ihidea.starcharge.monitor.dxp;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ihidea.core.util.DateUtilsEx;
import com.ihidea.core.util.JSONUtilsEx;
import com.ihidea.core.util.PropertyUtils;
import com.ihidea.generator.dao.model.StubErrorLog;
import com.ihidea.starcharge.monitor.dao.StubAuditErrorDao;
import com.ihidea.starcharge.monitor.dao.StubErrorDao;
import com.mongodb.BasicDBObject;

public class KafkaConsumer {

	private final static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Autowired
	@Qualifier("jdbcTemplateMonitor")
	private JdbcTemplate jdbcTemplateMonitor;
	
	@Autowired
	private StubErrorDao stubErrorDao;
	
	@Autowired
	private StubAuditErrorDao stubAuditErrorDao;
	
	private static String fileDir = PropertyUtils.getProperty("/config/database.properties", "file.orderHis");
	
	public static Map<String, Object> PRIVATE_STUB_MAP = new HashMap<>();  //私桩缓存
	
	
	@KafkaListener(id = "open.distributionBoxInfo", topics = "open.distributionBox.info", group = "distributionBox")
	public void listen4(ConsumerRecord<?, String> record) {

		if (record != null && record.value() != null) {
			/**
			 {
			    "id": "90000005",
			    "meterId": "0000009000068268",
			    "currentA": 0,
			    "voltageA": 241.2,
			    "currentB": 0,
			    "voltageB": 241.8,
			    "currentC": 0,
			    "voltageC": 242.1,
			    "electricA": 2407.4,
			    "electricB": 1710.2,
			    "electricC": 2206.6,
			    "powerA": 0,
			    "powerB": 0,
			    "powerC": 0,
			    "electricTotal": 6324.4,
			    "powerTotal": 0,
			    "timestamp": 1488273861421
			}
			 */
			logger.info("kafka open.distributionBox.info:" + record.value());
			
			String boxId = "", _id = "", meterId = "";
			
			Map<String, Object> params = null;
			Object[] objs = new Object[16];
			
			try{
				params = JSONUtilsEx.deserialize(record.value(), Map.class);
				boxId = String.valueOf(params.get("id"));
				_id = boxId + "_" + DateUtilsEx.formatToString(new Date(), "yyyyMM");
				
				objs[0] = Double.parseDouble(String.valueOf(params.get("meterId")));
				objs[1] = Double.parseDouble(String.valueOf(params.get("currentA")));
				objs[2] = Double.parseDouble(String.valueOf(params.get("voltageA")));
				objs[3] = Double.parseDouble(String.valueOf(params.get("currentB")));
				objs[4] = Double.parseDouble(String.valueOf(params.get("voltageB")));
				objs[5] = Double.parseDouble(String.valueOf(params.get("currentC")));
				objs[6] = Double.parseDouble(String.valueOf(params.get("voltageC")));
				objs[7] = Double.parseDouble(String.valueOf(params.get("electricA")));
				objs[8] = Double.parseDouble(String.valueOf(params.get("electricB")));
				objs[9] = Double.parseDouble(String.valueOf(params.get("electricC")));
				objs[10] = Double.parseDouble(String.valueOf(params.get("powerA")));
				objs[11] = Double.parseDouble(String.valueOf(params.get("powerB")));
				objs[12] = Double.parseDouble(String.valueOf(params.get("powerC")));
				objs[13] = Double.parseDouble(String.valueOf(params.get("electricTotal")));
				objs[14] = Double.parseDouble(String.valueOf(params.get("powerTotal")));
				objs[15] = Double.parseDouble(String.valueOf(params.get("timestamp")));
				
				mongoTemplate.upsert(Query.query(Criteria.where("_id").is(_id)), (new Update().push("data", objs)), BasicDBObject.class, "t_distributionbox_status_m");
			 
			}catch(com.mongodb.DuplicateKeyException e1){
				logger.info("重复键异常,不处理...");
			}catch(org.springframework.dao.DuplicateKeyException e2){
				logger.info("重复键异常,不处理...");
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
	}
	
	//topics=open.gis.queryStubGroupList
	@KafkaListener(id = "open.gis.queryStubGroupList", topics = "open.gis.queryStubGroupList", group = "gis")
	public void listen0(ConsumerRecord<?, String> record) {

		if (record != null && record.value() != null) {
			logger.info("kafka open.gis.queryStubGroupList:" + record.value());   
			
			long time = 0l;
			String stubId = "", _id = "";
			Map<String, Object> params = null;
			Object[] objs = new Object[3];
			String status = "";
			
			try{
				params = JSONUtilsEx.deserialize(record.value(), Map.class);
			 
			}catch(com.mongodb.DuplicateKeyException e1){
				logger.info("重复键异常,不处理...");
			}catch(org.springframework.dao.DuplicateKeyException e2){
				logger.info("重复键异常,不处理...");
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
	}
	
	@KafkaListener(id = "listenStubStatus", topics = "open.stubStatus", group = "stub_status_group")
	public void listen1(ConsumerRecord<?, String> record) {

		if (record != null && record.value() != null) {
			logger.info("kafka stubStatus:" + record.value());  //{"time":1478053182952,"status":"0004","stubId":"10022236"} info
			
			long time = 0l;
			String stubId = "", _id = "";
			Map<String, Object> params = null;
			Object[] objs = new Object[3];
			String status = "";
			
			try{
				params = JSONUtilsEx.deserialize(record.value(), Map.class);
				stubId = String.valueOf(params.get("stubId"));
				
				//私桩状态不入状态库
				//if(!(PRIVATE_STUB_MAP != null && PRIVATE_STUB_MAP.containsKey(stubId))){
					
					time = Long.parseLong(String.valueOf(params.get("time")));
					_id = stubId + "_" + DateUtilsEx.formatToString(new Date(time), "yyyyMM");
					
					objs[0] = time;
					objs[1] = params.get("status");
					objs[2] = params.get("info");
					
					mongoTemplate.upsert(Query.query(Criteria.where("_id").is(_id)), (new Update().push("data", objs)), BasicDBObject.class, "t_stub_status_m");
					
					status = String.valueOf(objs[1]);
					
					// 插入故障信息表
					if (!StringUtils.isEmpty(status) && status.startsWith("021")) {
						StubErrorLog errorLog = new StubErrorLog();
						errorLog.setStubId(stubId);
						errorLog.setErrorCode(status);
						errorLog.setStatus(0);
						
						String existId = stubErrorDao.getErrExistByStubIdAndCodeAndDate(stubId, status, DateUtilsEx.formatToString(new Date(time), "yyyyMMdd"));
						if(StringUtils.isEmpty(existId)){
							//插入
							stubErrorDao.insertErrLog(errorLog);
						}else{
							//更新同一桩同一故障当天的告警次数
							stubErrorDao.updateMQstubErrorCount(existId);
						}
					}
					
					// 插入审计故障信息表
					if (!StringUtils.isEmpty(status) && status.startsWith("02090")) {
						StubErrorLog errorLog = new StubErrorLog();
						errorLog.setStubId(stubId);
						errorLog.setErrorCode(status);
						errorLog.setStatus(0);
						errorLog.setErrorInfo(params.get("info")==null?"":String.valueOf(params.get("info")));
						stubAuditErrorDao.insertErrLog(errorLog);
					}
					
				//}
			}catch(com.mongodb.DuplicateKeyException e1){
				logger.info("重复键异常,不处理...");
			}catch(org.springframework.dao.DuplicateKeyException e2){
				logger.info("重复键异常,不处理...");
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
	}
	
	@KafkaListener(id = "listenOrderInfo", topics = "open.orderInfo", group = "order_info_group")
	public void listen2(ConsumerRecord<?, String> record) {

		if (record != null && record.value() != null) {
			//logger.info("kafka orderInfo:" + record.value());
			
			Map<String, Object> params = JSONUtilsEx.deserialize(record.value(), Map.class);
			
			BasicDBObject doc = new BasicDBObject();
			
			String orderId = "";
			
			try{
				orderId =  String.valueOf(((Map<?,?>)params.get("info")).get("id"));
				doc.put("_id", orderId);
				doc.put("info", params.get("info"));
				
				List<String> lstr = (List<String>) params.get("his");
				List<Object[]> tailList = null;
				
				if(lstr != null && lstr.size() > 0){
					tailList = new ArrayList<Object[]>();
					JSONObject j = null;
					Object[] objs = null;				
					
					//0=ctl,1=voltage,2=current,3=soc,4=tempCar,5=tempStub,6=tempGun
					for(String s : lstr){
						j = JSONObject.parseObject(s);
						objs = new Object[11];
						objs[0] = j.get("ctl");
						objs[1] = j.get("voltage");
						objs[2] = j.get("current");
						objs[3] = j.get("soc");
						objs[4] = j.get("tempCar");
						objs[5] = j.get("tempStub");
						objs[6] = j.get("tempGun");
						objs[7] = j.get("power");
						objs[8] = j.get("voltageCar");
						objs[9] = j.get("currentCar");
						objs[10] = j.get("electricEnd");
						//车辆需要的电压、电流字段：voltageCar,currentCar
						
						tailList.add(objs);
					}
				}
				doc.put("his", tailList);
				
				mongoTemplate.insert(doc, "t_order_info");
				
			}catch(com.mongodb.DuplicateKeyException e1){
				logger.info("重复键异常,不处理...");
			}catch(org.springframework.dao.DuplicateKeyException e2){
				logger.info("重复键异常,不处理...");
			}catch(Exception e){
				try {
					FileUtils.writeStringToFile(new File(fileDir + orderId), JSONUtilsEx.serialize(params));
				} catch (IOException e1) {
					logger.error("订单orderId=" + orderId + ", 订单记录存mongo异发生常,转存到文件时再次异常");
					logger.error(e1.getMessage(), e1);
				}
				logger.error(e.getMessage(), e);
				logger.error("订单orderId=" + orderId + ", 订单记录存mongo异发生常,转为存文件");
			}
			
		}
	}
	
	@KafkaListener(id = "listenDataInfo", topics = "open.dataInfo", group = "order_info_group")
	public void listen3(ConsumerRecord<?, String> record) {

		if (record != null && record.value() != null) {
			logger.info("kafka dataInfo:" + record.value());
			
			try{
				
				Map<String, Object> params = JSONUtilsEx.deserialize(record.value(), Map.class);
				
				String type = params == null?"":(params.get("type") == null?"":String.valueOf(params.get("type")));
				
				if(!StringUtils.isEmpty(type)){
					Map<String, Object> data = (Map<String, Object>)params.get("data");
					String id = String.valueOf(data.get("id"));
					int count = 0;
					
					if("stub".equals(type)){
						count = jdbcTemplateMonitor.queryForObject("select count(id) from t_stub t where t.id = ?" , new Object[] {id}, Integer.class);
						if(count > 0){
							jdbcTemplateMonitor.update("delete from t_stub where id = ?", new Object[] {id});
						}
						//插入
						//kafka dataInfo:{"data":{"id":"10050285","stubGroupId":"beta","name":"充电桩10050285","type":0,"status":"05","parkingNo":"",
						//"manufactureDate":"2016.11.28 15:31:10","modelNo":"DH-AC0070XG13-A","existsGun":1,"powerType":1,"ratedCurrent":32.00,"kw":7.00,
						//"interfaceCnt":1,"interfaceType":null,"interfaceStandard":null,"orderId":"2016.12.13 15:31:17","createAccount":"299",
						//"createTime":"2016.12.13 15:31:17","modifyAccount":null,"modifyTime":null,"updateType":null,"frameWork":null,"parkingInfo":"",
						//"qrCode":null,"img":null,"lng":0.0,"lat":0.0,"address":"","city":"","isShow":null,"voltageUpperLimit":253,"voltageLowerLimit":187,
						//"voltageAuxiliary":0,"equipmentType":3,"totalFeeInfo":null,"serviceTime":"-","operationMode":null,"canBilling":null},"type":"stub"}
						
						jdbcTemplateMonitor.update( "insert into t_stub (ID,STUB_GROUP_ID,NAME,TYPE,STATUS,PARKING_NO,MANUFACTURE_DATE," + 
								 "MODEL_NO,EXISTS_GUN,POWER_TYPE,RATED_VOLTAGE,RATED_CURRENT,KW,INTERFACE_CNT,INTERFACE_TYPE,INTERFACE_STANDARD,ORDER_ID," + 
								 "CREATE_ACCOUNT,CREATE_TIME,MODIFY_ACCOUNT,MODIFY_TIME,UPDATE_TYPE,FRAME_WORK,PARKING_INFO,SERVICE_FEE,QR_CODE,IMG,LNG,LAT,ADDRESS,CITY,IS_SHOW," + 
								 "VOLTAGE_UPPER_LIMIT,VOLTAGE_LOWER_LIMIT,VOLTAGE_AUXILIARY,EQUIPMENT_TYPE,TOTAL_FEE_INFO,SERVICE_TIME,OPERATION_MODE,CAN_BILLING) values " + 
								 "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ,
				                new Object[] {
								id, data.get("stubGroupId"), data.get("name"), data.get("type"), data.get("status")==null?"00":data.get("status"), 
								data.get("parkingNo"), data.get("manufactureDate"), data.get("modelNo"), data.get("existsGun"), data.get("powerType"), 
								data.get("ratedVoltage"),data.get("ratedCurrent"), data.get("kw"), data.get("interfaceCnt"), data.get("interfaceType"), 
								data.get("interfaceStandard"), data.get("orderId"), data.get("createAccount"), data.get("createTime")==null?new Date():data.get("createTime"),
								data.get("modifyAccount"), 
								data.get("modifyTime"), data.get("updateType"), data.get("frameWork"), data.get("parkingInfo"), data.get("serviceFee"), 
								data.get("qrCode"), data.get("img"), data.get("lng"), data.get("lat"), data.get("address"), data.get("city"), data.get("isShow"), 
								data.get("voltageUpperLimit"), data.get("voltageLowerLimit"), data.get("voltageAuxiliary"), data.get("equipmentType"), 
								data.get("totalFeeInfo"), data.get("serviceTime"), data.get("operationMode"), data.get("canBilling") });
					}else if("stubGroup".equals(type)){
						count = jdbcTemplateMonitor.queryForObject("select count(id) from t_stub_group t where t.id = ?" , new Object[] {id}, Integer.class);
						if(count > 0){
							jdbcTemplateMonitor.update("delete from t_stub_group where id = ?", new Object[] {id});
						}
						//插入
						//kafka dataInfo:{"data":{"id":"a4fea58e-0671-4a5d-bac1-af8918a5fb55","city":"310115","img":null,"name":"浦东新区 张江集电港(2区)",
						//"address":"上海市浦东新区龙东大道3000弄11号楼","tel":"4008280768","gisBd09Lat":31.699901,"gisBd09Lng":119.973138,"gisGcj02Lat":31.693960629339937,
						//"gisGcj02Lng":119.96660848466011,"isEnable":1,"status":0,"orderId":"2016.12.08 14:34:51","createAccount":"108","createTime":"2016.12.08 14:34:51",
						//"modifyAccount":"108","modifyTime":"2016.12.13 15:41:55","avgScore":null,"type":1,"powerCabinetId":null,"stubGroupType":0,"kwLimit":17.00,
						//"parkingCnt":2,"parkingType":4,"parkingInfo":"","parkingFree":1,"stubGroupInfo":"11号楼地下车库","noticInfo":"","serviceTime":"00:00-23:59",
						//"serviceFee":null,"parkingFeeInfo":"内部办卡","hasGun":1,"isBuilded":1,"chargeMode":1,"startTime":"2016.11.29 09:14:29","placeType":1224,
						//"cspId":"0","totalFeeInfo":null,"enableRoaming":0,"roamingId":8769,"serviceType":null,"parkingIdleCnt":null,"info":null},"type":"stubGroup"}
						
						jdbcTemplateMonitor.update("insert into t_stub_group (ID,CITY,IMG,NAME,INFO,ADDRESS,TEL,GIS_BD09_LAT,GIS_BD09_LNG,GIS_GCJ02_LAT,GIS_GCJ02_LNG," + 
								 "IS_ENABLE,STATUS,ORDER_ID,CREATE_ACCOUNT,CREATE_TIME,MODIFY_ACCOUNT,MODIFY_TIME,AVG_SCORE,TYPE,POWER_CABINET_ID," + 
								 "STUB_GROUP_TYPE,KW_LIMIT,PARKING_CNT,PARKING_TYPE,PARKING_INFO,PARKING_FREE,STUB_GROUP_INFO,NOTIC_INFO,SERVICE_TIME," + 
								 "SERVICE_FEE,PARKING_FEE_INFO,HAS_GUN,IS_BUILDED,CHARGE_MODE,START_TIME,PLACE_TYPE,CSP_ID,TOTAL_FEE_INFO,ENABLE_ROAMING," + 
								 "ROAMING_ID,SERVICE_TYPE,PARKING_IDLE_CNT) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
								  new Object[] {
								id, data.get("city"), data.get("img"), data.get("name")==null?"":data.get("name"), data.get("info"), data.get("address"), 
								data.get("tel"), data.get("gisBd09Lat")==null?0:data.get("gisBd09Lat"), data.get("gisBd09Lng")==null?0:data.get("gisBd09Lng"),
								data.get("gisGcj02Lat")==null?0:data.get("gisGcj02Lat"), data.get("gisGcj02Lng")==null?0:data.get("gisGcj02Lng"), 
								data.get("isEnable"),data.get("status"), data.get("orderId"), data.get("createAccount"), data.get("createTime"), 
								data.get("modifyAccount"), data.get("modifyTime"), data.get("avgScore"), data.get("type"), data.get("powerCabinetId"), 
								data.get("stubGroupType"), data.get("kwLimit"), data.get("parkingCnt"), data.get("parkingType"), data.get("parkingInfo"), 
								data.get("parkingFree"), data.get("stubGroupInfo"), data.get("noticInfo"), data.get("serviceTime"), data.get("serviceFee"),
								data.get("parkingFeeInfo"), data.get("hasGun"), data.get("isBuilded"), data.get("chargeMode")==null?1:data.get("chargeMode"), data.get("startTime"), 
								data.get("placeType"), data.get("cspId"), data.get("totalFeeInfo"), data.get("enableRoaming"), data.get("roamingId"),
								data.get("serviceType"), data.get("parkingIdleCnt") });
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.info(e.getMessage());
			}
			
		}
	}

}
