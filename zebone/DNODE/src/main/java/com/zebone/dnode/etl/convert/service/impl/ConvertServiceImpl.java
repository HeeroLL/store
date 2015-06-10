package com.zebone.dnode.etl.convert.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zebone.dnode.etl.convert.ConvertParse;
import com.zebone.dnode.etl.convert.dao.ConvertDataMapper;
import com.zebone.dnode.etl.convert.dao.ConvertLogMapper;
import com.zebone.dnode.etl.convert.dao.DataCompareMapper;
import com.zebone.dnode.etl.convert.pojo.ColumnParameter;
import com.zebone.dnode.etl.convert.pojo.ConvertConfig;
import com.zebone.dnode.etl.convert.pojo.ConvertDataObj;
import com.zebone.dnode.etl.convert.pojo.ConvertLogObj;
import com.zebone.dnode.etl.convert.pojo.ConvertParameter;
import com.zebone.dnode.etl.convert.pojo.TableParameter;
import com.zebone.dnode.etl.convert.service.ConvertService;
import com.zebone.util.UUIDUtil;

/**
 * 数据清洗转换服务实现类
 *
 * @author 杨英
 * @version 2014-02-18 上午08:51
 */
@Service("convertService")
public class ConvertServiceImpl implements ConvertService {

    private static final Log log = LogFactory.getLog(ConvertServiceImpl.class);

    @Resource
    private ConvertDataMapper convertDataMapper;

    @Resource
    private ConvertLogMapper convertLogMapper;

    @Resource
    private DataCompareMapper dataCompareMapper;

    @Override
    public void convertData(ConvertConfig convertConfig, Map<String,String> oMap) {
        //获取配置文件中需要进行数据清洗的数据源表集合
        List<TableParameter> tableParameterLic = convertConfig.getCleanParameter().getTableParameter();
        //获取来源机构编码
        String orgCode = oMap.get("orgCode");
        Map<String, String> parMap = new HashMap<String, String>();
        parMap.put("startTime",oMap.get("startTime"));
        parMap.put("endTime",oMap.get("endTime"));

        for (TableParameter tablePar : tableParameterLic) {
            String tableName = tablePar.getTableName()+"_"+orgCode;
            //根据源数据表名获取清洗目标表名
            String cleanTableName = tableName.replaceFirst("O", "C");
            //需要进行清洗转换的字段名称列表
            List<String> cleanColNameLic = new ArrayList<String>();
            //数据清洗规则对象列表
            List<List<ConvertParameter>> convertPar = new ArrayList<List<ConvertParameter>>();

            List<ColumnParameter> columnParameterLic = tablePar.getColumnParameter();
            for (ColumnParameter columnPar : columnParameterLic) {
                cleanColNameLic.add(columnPar.getColumnName());
                convertPar.add(columnPar.getConvertParameter());
            }

            parMap.put("tableName", tableName);
            //源表中除“CLEAN_FLAG”之外所有的字段名称
            List<String> allColNameLic = convertDataMapper.getColumnNameLic(parMap);
            parMap.put("columnName", formatColumnName(allColNameLic));
            //需要进行清洗转换的数据对象列表
            List<ConvertDataObj> dataLic = convertDataMapper.getConvertDataLic(parMap);
            if (dataLic != null && dataLic.size() > 0) {
                for (ConvertDataObj convertData : dataLic) {
                    String mainId = convertData.getId();
                    String errorDesc = "";
                    ConvertParse convertParse = new ConvertParse();
                    //源数据的字段值列表信息
                    String[] perDataLic = convertData.getColumnData().split(",");

                    for (int i = 0; i < cleanColNameLic.size(); i++) {
                        //需要进行清洗转换的字段名称
                        String cleanColName = cleanColNameLic.get(i);
                        for (int j = 0; j < allColNameLic.size(); j++) {
                            if (cleanColName.equals(allColNameLic.get(j))) {
                                //一个字段值可能有多个清洗转换规则
                                List<ConvertParameter> perConvertParLic = convertPar.get(i);
                                for (ConvertParameter perConvertPar : perConvertParLic) {
                                    //进行清洗转换之前的数据值
                                    String strPerData = perDataLic[j];
                                    //进行清洗转换之后的数据值
                                    String dataAftConvert = "";
                                    String convertClass = perConvertPar.getType();  //数据清洗转换实现类名称
                                    try {
                                        if ("NoNeedConvert".equals(convertClass)) { //该字段不需要进行清洗转换
                                            dataAftConvert = strPerData;
                                        } else {
                                            dataAftConvert = convertParse.getConvertData(convertClass, perConvertPar.getParam(), strPerData, orgCode, dataCompareMapper);
                                        }
                                    } catch (Exception e) {
                                        errorDesc = e.getMessage();
                                    }
                                    if (!"".equals(errorDesc)) {
                                        //插入数据清洗转换日志信息
                                        ConvertLogObj convertLogObj = new ConvertLogObj();
                                        convertLogObj.setId(UUIDUtil.getUuid());
                                        convertLogObj.setTableName(tableName);
                                        convertLogObj.setColumnName(allColNameLic.get(j));
                                        convertLogObj.setCleanTableName(cleanTableName);
                                        convertLogObj.setCleanColumnName(allColNameLic.get(j));
                                        convertLogObj.setColumnValue(strPerData);
                                        convertLogObj.setCleanColumnValue(dataAftConvert);
//                                        convertLogObj.setCleanType(convertClass);
                                        convertLogObj.setCleanFlag("0"); //“0”代表清洗转换不成功
                                        convertLogObj.setCleanErrorDesc(errorDesc);
                                        convertLogObj.setMainId(mainId);
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
                                        convertLogObj.setCreateTime(sdf.format(new Date()));
                                        try {
                                            convertLogMapper.saveConvertLog(convertLogObj);
                                        } catch (Exception e) {
                                            log.error("保存清洗日志时出错", e);
                                            e.printStackTrace();
                                        }
                                        break;
                                    } else {
                                        //如果该字段清洗转换成功，将数组中该字段的值替换成转换后的值
                                        perDataLic[j] = dataAftConvert;
                                    }
                                }
                            }
                        }
                        if (!"".equals(errorDesc)) {
                            break;
                        }
                    }
                    if (!"".equals(errorDesc)) {
                        parMap.put("cleanFlag", "0"); //"0"表示转换失败
                    } else {
                        parMap.put("cleanFlag", "1"); //"1"表示转换成功
                    }
                    parMap.put("id", mainId);
                    parMap.put("cleanTableName", cleanTableName);
                    parMap.put("cleanFieldName", getStrFieldName(allColNameLic));
                    parMap.put("cleanFieldVal", getStrFieldVal(perDataLic));
                    try {
                        //更新数据清洗转换标志，同时将清洗成功的数据保存到目标清洗表中
                        updateCleanFlag(parMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                }
            }
        }
    }

    /**
     * 格式化源表中字段信息以便查询
     *
     * @param columnNameLic
     */
    private String formatColumnName(List<String> columnNameLic) {
        String columnNameInfo = "";
        if (columnNameLic != null && columnNameLic.size() > 0) {
            for (String columnName : columnNameLic) {
                if (!"".equals(columnNameInfo)) {
                    columnNameInfo = columnNameInfo + "||','||";
                }
                columnNameInfo = columnNameInfo + columnName;
            }
        }
        return columnNameInfo;
    }

    /**
     * 更新数据清洗转换标志
     *
     * @param parMap
     */
    @Transactional(value = "transactionManager_etl", rollbackFor = Throwable.class)
    private void updateCleanFlag(Map<String, String> parMap) throws Exception {
        String cleanFlag = parMap.get("cleanFlag");
        try {
            if ("1".equals(cleanFlag)) {
                //如果清洗转换标志为“1”，需要把清洗后的数据保存到目标清洗表中
                convertDataMapper.saveConvertData(parMap);
            }
            convertDataMapper.updateCleanFlag(parMap);
        } catch (Exception e) {
            log.error("更新数据清洗标志时出错", e);
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * 根据字段列表获取字段字符串
     *
     * @param columnNameLic
     */
    private String getStrFieldName(List<String> columnNameLic) {
        String strFieldName = "";
        if (columnNameLic != null && columnNameLic.size() > 0) {
            for (String fieldName : columnNameLic) {
                if (!"".equals(strFieldName)) {
                    strFieldName = strFieldName + ",";
                }
                strFieldName = strFieldName + fieldName;
            }
        }
        return strFieldName;
    }

    /**
     * 根据字段数组值获取字符串值
     *
     * @param perDataLic
     */
    private String getStrFieldVal(String[] perDataLic) {
        String strFieldVal = "";
        if (perDataLic != null && perDataLic.length > 0) {
            for (int i = 0; i < perDataLic.length; i++) {
                if (!"".equals(strFieldVal)) {
                    strFieldVal = strFieldVal + ",";
                }
                strFieldVal = strFieldVal + "'"+perDataLic[i]+"'";
            }
        }
        return strFieldVal;
    }

}