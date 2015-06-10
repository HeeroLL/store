package com.zebone.dnode.engine.empi.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.empi.service.EmpiCardAggregateService;
import com.zebone.dnode.engine.empi.vo.ResidentCard;
import com.zebone.dnode.engine.empi.vo.ResidentInfoLog;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;


/**
 * 
 * @author 陈阵 
 * @date 2014-6-13 下午3:27:46
 */

@Service
public class EmpiCardAggregateServiceImpl implements EmpiCardAggregateService {
	
	@Resource(name="jdbcTemplateEMPI")
	private JdbcTemplate jdbcTemplateEMPI;

	@Override
	public void doMedicalCardAggregate() {
		// TODO Auto-generated method stub
		Map<String,Integer> cardRegisterMap = new HashMap<String, Integer>();
		Map<String,Integer> cardCancelMap = new HashMap<String, Integer>();
		
		
		String yesterday = DateUtil.getAdvanceDay(-1,"yyyy-MM-dd");
		String curDate = DateUtil.getCurrentDate("yyyy-MM-dd");
		String startDate = yesterday + " 00:00:00";
		String endDate = curDate + " 00:00:00";
	    
		/**
		startDate = "2014-06-14 00:00:00";
		endDate = "2014-06-15 00:00:00";
		curDate = "2014-06-15";
		**/
		
		/** 统计卡注册  **/
		String sql1 = "SELECT EMPI FROM E_RESIDENT_CARD WHERE CARD_TYPE = '10' AND CREATE_DATE >= TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS') AND CREATE_DATE<=TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS')";
		List<ResidentCard> rcList1 = jdbcTemplateEMPI.query(sql1, new ResidentCardMapper(),new Object[]{startDate,endDate});
	    if(rcList1 != null && rcList1.size() > 0 ){
	    	for(ResidentCard rc : rcList1){
	    		String empi = rc.getEmpi();
	    		String isql = "SELECT DEPT_CODE FROM E_RESIDENT_INFO_LOG WHERE EMPI = ? AND OPER_STATE = 1";
	    		ResidentInfoLog rif = jdbcTemplateEMPI.queryForObject(isql,new ResidentInfoLogMapper(),new Object[]{empi});
	    		String deptCode = rif.getDeptCode();
	    		Integer count = cardRegisterMap.get(deptCode);
	    		if(count == null){
		    		cardRegisterMap.put(deptCode,Integer.valueOf(1));
	    		}else{
	    			cardRegisterMap.put(deptCode,++count);
	    		}
	    	}
	    }
	    
	    /** 统计卡注销**/
		String sql2 = "SELECT EMPI FROM E_RESIDENT_CARD WHERE CARD_TYPE = '10' AND CARD_STATUS = '2' AND CANCEL_DATE >= TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS') AND CANCEL_DATE<=TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS')";
		List<ResidentCard> rcList2 = jdbcTemplateEMPI.query(sql2, new ResidentCardMapper(),new Object[]{startDate,endDate});
	    if(rcList2 != null && rcList2.size() > 0 ){
	    	for(ResidentCard rc : rcList2){
	    		String empi = rc.getEmpi();
	    		String isql = "SELECT DEPT_CODE FROM E_RESIDENT_INFO_LOG WHERE EMPI = ? AND OPER_STATE = 1";
	    		ResidentInfoLog rif = jdbcTemplateEMPI.queryForObject(isql,new ResidentInfoLogMapper(),new Object[]{empi});
	    		String deptCode = rif.getDeptCode();
	    		Integer count = cardCancelMap.get(deptCode);
	    		if(count == null){
	    			cardCancelMap.put(deptCode,Integer.valueOf(1));
	    		}else{
	    			cardCancelMap.put(deptCode,++count);
	    		}
	    	}
	    }
	    
	    String sql3 = "SELECT DISTINCT(DEPT_CODE) FROM E_RESIDENT_INFO_LOG";
	    List<String> deptCodeList = jdbcTemplateEMPI.queryForList(sql3, String.class);
	    if(deptCodeList != null && deptCodeList.size() > 0){
	    	for(String deptCode : deptCodeList){
	    		String sql = "INSERT INTO APC_STATISTICS(ID_,ORG_CODE,REG_MEDICAL_CARD_NUM,CANCEL_MEDICAL_CARD_NUM,SPE_TIME) VALUES(?,?,?,?,TO_DATE(?,'yyyy-mm-dd'))";
	    		Integer cardRegisterTotal = cardRegisterMap.get(deptCode);
	    		Integer cardCancelTotal = cardCancelMap.get(deptCode);
	    		if(cardRegisterTotal == null){
	    			cardRegisterTotal = 0;
	    		}
	    		if(cardCancelTotal == null){
	    			cardCancelTotal = 0;
	    		}
	    		String uuid = UUIDUtil.getUuid();
	    		jdbcTemplateEMPI.update(sql, new Object[]{uuid,deptCode,cardRegisterTotal,cardCancelTotal,curDate});
	    	}
	    }
	}
	
	
	
	
	private static final class ResidentCardMapper implements RowMapper<ResidentCard> {
		@Override
		public ResidentCard mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			ResidentCard rc = new ResidentCard();
			rc.setEmpi(rs.getString("EMPI"));
			return rc;
		}

	}
	
	private static final class ResidentInfoLogMapper implements RowMapper<ResidentInfoLog> {
		@Override
		public ResidentInfoLog mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			ResidentInfoLog ril = new ResidentInfoLog();
			ril.setDeptCode(rs.getString("DEPT_CODE"));
			return ril;
		}

	}

}
