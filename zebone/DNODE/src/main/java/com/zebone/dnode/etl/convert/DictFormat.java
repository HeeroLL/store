package com.zebone.dnode.etl.convert;

import com.zebone.dnode.etl.convert.dao.DataCompareMapper;
import java.util.HashMap;
import java.util.Map;

/**
 * 字典清洗转换类
 *
 * @author 杨英
 * @version 2014-02-19 上午08:44
 */
public class DictFormat implements DataConverting{


    /**
     * 获取字典对照编码
     * @param convertPar  机构字典类型
     * @param value  字典编码(机构)
     * @param orgCode 机构编码
     * @return
     */
    @Override
    public String getConvertData(String convertPar, Object value, String orgCode,DataCompareMapper dataCompareMapper) throws Exception{
        String dictCode = "";
        String orgDictCode = value.toString();
        Map<String,String> parMap = new HashMap<String,String>();
        parMap.put("dictTypeId", convertPar);
        parMap.put("dictCode", orgDictCode);
        parMap.put("orgCode",orgCode);
        dictCode = dataCompareMapper.getDictCompareCode(parMap);
        if(dictCode == null || "".equals(dictCode)){
            throw new Exception ("该字典编码有误");
        }
        return dictCode;
    }
}
