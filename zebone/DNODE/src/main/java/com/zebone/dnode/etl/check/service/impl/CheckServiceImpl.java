package com.zebone.dnode.etl.check.service.impl;

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

import com.zebone.dnode.etl.check.DataParse;
import com.zebone.dnode.etl.check.dao.CheckLogMapper;
import com.zebone.dnode.etl.check.dao.SourceDataMapper;
import com.zebone.dnode.etl.check.pojo.CheckConfig;
import com.zebone.dnode.etl.check.pojo.CheckLogObj;
import com.zebone.dnode.etl.check.pojo.CheckParameter;
import com.zebone.dnode.etl.check.pojo.ColumnParameter;
import com.zebone.dnode.etl.check.pojo.SourceDataObj;
import com.zebone.dnode.etl.check.pojo.TableParameter;
import com.zebone.dnode.etl.check.service.CheckService;
import com.zebone.util.UUIDUtil;

/**
 * 数据验证服务实现类
 *
 * @author 杨英
 * @version 2014-02-13 下午02:36
 */
@Service("checkService")
public class CheckServiceImpl implements CheckService{

    private static final Log log = LogFactory.getLog(CheckServiceImpl.class);

    @Resource
    private SourceDataMapper sourceDataMapper;

    @Resource
    private CheckLogMapper checkLogMapper;

    @Override
    public void checkSourceData(CheckConfig checkConfig,Map<String,String> oMap) {
        //获取配置文件中需要进行数据检查的数据源表集合
        List<TableParameter> tableParameterLic = checkConfig.getVerifyParameter().getTableParameter();
        String orgCode = oMap.get("orgCode");
        Map<String,String> parMap = new HashMap<String, String>();
        parMap.put("startTime",oMap.get("startTime"));
        parMap.put("endTime",oMap.get("endTime"));

        for(TableParameter tablePar : tableParameterLic ){
            String tableName = tablePar.getTableName()+"_"+orgCode;
            //字段名称列表
            List<String> columnNameLic = new ArrayList<String>();
            //数据检查规则对象列表
            List<List<CheckParameter>> checkPar = new ArrayList<List<CheckParameter>>();

            List<ColumnParameter> columnParameterLic = tablePar.getColumnParameter();
            for(ColumnParameter columnPar : columnParameterLic){
                columnNameLic.add(columnPar.getColumnName());
                checkPar.add(columnPar.getCheckParameter());
            }

            parMap.put("tableName",tableName);
            parMap.put("columnName",formatColumnInfo(columnNameLic));
            //需要进行检查的数据对象列表
            List<SourceDataObj> dataLic = sourceDataMapper.getSourceDataLic(parMap);
            if(dataLic!=null && dataLic.size()>0){
                for(SourceDataObj sourceData : dataLic){
                    String mainId = sourceData.getId();
                    boolean isDataValid = false;
                    DataParse dataParse = new DataParse();
                    //源数据的字段值列表信息
                    String[] perDataLic = sourceData.getColumnData().split(",");
                    for(int j=0; j<perDataLic.length; j++){
                        String strPerData = perDataLic[j];
                        //一个字段值可能需要进行多项数据检查
                        List<CheckParameter> perCheckParLic = checkPar.get(j);
                        for(CheckParameter perCheckPar : perCheckParLic){
                            isDataValid = dataParse.isDataValid(perCheckPar.getType(),perCheckPar.getParam(),strPerData);
                            if(!isDataValid){
                                //数据检查错误，插入日志信息
                                CheckLogObj checkLogObj = new CheckLogObj();
                                checkLogObj.setId(UUIDUtil.getUuid());
                                checkLogObj.setTableName(tableName);
                                checkLogObj.setColumnName(columnNameLic.get(j));
                                checkLogObj.setColumnValue(strPerData);
//                                checkLogObj.setCheckType(perCheckPar.getType());
                                checkLogObj.setCheckFlag("0"); //“0”代表检查不成功
                                checkLogObj.setMainId(mainId);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
                                checkLogObj.setCreateTime(sdf.format(new Date()));
                                try {
                                    checkLogMapper.saveCheckLog(checkLogObj);
                                }catch (Exception e){
                                    log.error("保存检查日志时出错",e);
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                        if(!isDataValid){
                            break;
                        }
                    }
                    parMap.put("id", mainId);
                    if (isDataValid) {
                        parMap.put("checkFlag","1"); //"1"表示检查成功
                    }else{
                        parMap.put("checkFlag","0"); //"0"表示检查失败
                    }
                    try {
                        //更新数据检查标志
                        sourceDataMapper.updateCheckFlag(parMap);
                    } catch (Exception e) {
                        log.error("更新数据检查标志时出错", e);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 格式化某表中需要进行检查的字段信息
     *
     * @param columnNameLic
     */
    private String formatColumnInfo(List<String> columnNameLic) {
        String columnNameInfo = "";
        if(columnNameLic!=null && columnNameLic.size()>0){
            for(String columnName: columnNameLic){
                if(!"".equals(columnNameInfo)){
                    columnNameInfo = columnNameInfo + "||','||";
                }
                columnNameInfo = columnNameInfo + columnName;
            }
        }
        return columnNameInfo;
    }
}
