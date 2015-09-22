package com.zebone.empi.dao;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.zebone.btp.core.mybatis.DcMapper;


/**
 * 
 * @author 陈阵 
 * @date 2014-8-13 下午2:03:36
 */
@DcMapper
public interface DcOpMapper {
    
	
	int updateDcDataEmpi(@Param("table")String table,@Param("oempi") String oempi,@Param("nempi") String nempi,@Param("docno") String docNo);
	
	Map<String,String> getDcData(@Param("table")String table, @Param("empi") String empi,@Param("docno") String docNo);
	
	int delDcData(@Param("table")String table, @Param("empi") String empi, @Param("docno") String docNo);

}