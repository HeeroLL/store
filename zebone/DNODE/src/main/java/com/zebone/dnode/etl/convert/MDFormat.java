package com.zebone.dnode.etl.convert;

import com.zebone.dnode.etl.convert.dao.DataCompareMapper;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 主数据清洗转换类
 *
 * @author 杨英
 * @version 2014-02-19 上午08:43
 */
@Service
public class MDFormat implements DataConverting{

    /**
     * 获取主数据对照编码
     * @param convertPar  主数据类型
     * @param value  主数据编码（机构）
     * @param orgCode 机构编码
     * @return
     */
    @Override
    public String getConvertData(String convertPar, Object value, String orgCode, DataCompareMapper dataCompareMapper) throws Exception {
        //标准主数据编码
        String mdCode = "";
        String orgMDCode = value.toString().trim();
        Map<String,String> parMap = new HashMap<String,String>();
        String tableName = dataCompareMapper.getMDTable(convertPar);
        if(tableName == null || "".equals(tableName)){
            throw new Exception ("该主数据类型不存在");
        }else{
            String orgTableName = tableName +"_M";
            parMap.put("mdTableName", tableName);
            parMap.put("orgTableName", orgTableName);
            parMap.put("orgMDCode", orgMDCode);
            parMap.put("orgCode",orgCode);
            mdCode = dataCompareMapper.getMDCompareCode(parMap);
            if(mdCode == null || "".equals(mdCode)){
                throw new Exception ("该主数据编码有误");
            }
        }
        return mdCode;
    }
}
