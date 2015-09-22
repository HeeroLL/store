package com.zebone.dip.md.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.app.dict.service.DictionaryTypeService;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.dip.dictionary.service.DipDictionaryService;
import com.zebone.dip.md.service.MasterDataService;
import com.zebone.dip.md.vo.MDContentObject;
import com.zebone.dip.md.vo.MasterData;
import com.zebone.dip.md.vo.MasterDataField;
import com.zebone.dip.md.vo.MasterDataQuery;
import com.zebone.dip.md.vo.NameCode;
import com.zebone.util.DateUtil;
import com.zebone.util.Encodes;
import com.zebone.util.EncodingTool;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;

/**
 * 主数据管理
 * @author 杨英
 * @version 2013-7-17 上午10:52:03
 */
@Controller
public class MasterDataController extends BaseController {

    @Resource
    private DictionaryTypeService dictionaryTypeService;

    @Resource
    private MasterDataService masterDataService;

    @Resource
    private DipDictionaryService dipDictionaryService;

    /**
     * 获取主数据内容管理页面左侧的树状列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("maindata/masterData")
    public String masterData(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if(id!=null && !"".equals(id)){
            request.setAttribute("id", id);
        }
        //获取主数据内容管理页面左侧的树状列表
        DictionaryType dictionaryType = new DictionaryType();
        dictionaryType.setParentId("");
//        dictionaryType.setTypeId("MD000001");
        dictionaryType.setTypeName("主数据类型");
        dictionaryType.setTypeCode("MD");
        List<DictionaryType> typeList = new ArrayList<DictionaryType>();
       // typeList.add(dictionaryType);
        List<MasterData> treeList = masterDataService.getMasterDataType();
        request.setAttribute("treeList", JsonUtil.writeValueAsString(treeList));
        return "dip/masterData/masterdata_content";
    }

    /**
     * 根据一级节点的ID获取该类型下所有主数据作为树的二级节点
     * @param dictTypeLic
     * @return 主数据列表
     */
    private List<MasterData> getMasterTreeList(List<DictionaryType> dictTypeLic){
        List<MasterData> treeList = new ArrayList<MasterData>();
        if(dictTypeLic!=null){
            for(int i=0; i<dictTypeLic.size(); i++){
                MasterData masterData = new MasterData();
                DictionaryType dictionaryType = dictTypeLic.get(i);
                masterData.setId(dictionaryType.getTypeId());
                masterData.setCode(dictionaryType.getTypeCode());
                masterData.setName(dictionaryType.getTypeName());
                masterData.setTypeId(dictionaryType.getParentId());
                treeList.add(masterData);
                List<MasterData> masterDataList = new ArrayList<MasterData>();
                masterDataList = masterDataService.getMasterDataType();
                treeList.addAll(masterDataList);
            }
        }
        return treeList;
    }

    /**
     * 主数据内容管理页面
     * @param masterDataId
     * @param tableName
     * @param map
     * @return
     */
    @RequestMapping("maindata/masterDataContent")
    public String masterDataContent(@RequestParam("id") String masterDataId,
                                    @RequestParam("tableName") String tableName, ModelMap  map) {
        String resultURI = "";
        if (StringUtils.isEmpty(masterDataId)) {
            resultURI = "";
        } else {
            List<MasterDataField> masterDataFieldLic = masterDataService.getFieldByMasterDataId(masterDataId);
            setDataFieldInfo(masterDataFieldLic, map);

            //获取主数据内容管理页面左侧的树状列表
            DictionaryType dictionaryType = new DictionaryType();
            dictionaryType.setParentId("");
//            dictionaryType.setTypeId("MD000001");
            dictionaryType.setTypeName("主数据类型");
            dictionaryType.setTypeCode("MD");
//            List<DictionaryType> typeList = new ArrayList<DictionaryType>();
//            typeList.add(dictionaryType);
            List<MasterData> treeList = masterDataService.getMasterDataType();
            map.put("treeList", JsonUtil.writeValueAsString(treeList));
            map.put("id", masterDataId);
            map.put("tableName", tableName);
            resultURI = "dip/masterData/masterdata_content";
        }
        return resultURI;
    }

    /**
     * 获取主数据内容管理页面的字段信息，显示信息等
     * @param masterDataFieldLic
     * @param map
     */
    private void setDataFieldInfo(List<MasterDataField> masterDataFieldLic, ModelMap map){
        String displayName = "";
        String fieldName = "";
        String proName = "";
        String displayFlag = "";
        String fieldType = "";

        if(masterDataFieldLic!=null){
            for(int i=0; i<masterDataFieldLic.size(); i++){
                MasterDataField masterDataField = masterDataFieldLic.get(i);
                if(!"".equals(displayName)){
                    displayName = displayName +",";
                }
                if(!"".equals(fieldName)){
                    fieldName = fieldName + ",";
                }
                if(!"".equals(displayFlag)){
                    displayFlag = displayFlag + ",";
                }
                if(!"".equals(proName)){
                    proName = proName + ",";
                }
                if(!"".equals(fieldType)){
                    fieldType = fieldType +",";
                }

                displayName = displayName + masterDataField.getDisplayName();
                fieldName = fieldName + masterDataField.getFieldName();
                displayFlag = displayFlag + masterDataField.getDisplayable();
                fieldType = fieldType + masterDataField.getFieldType();
                proName = proName + ("pro"+(i+1));
                masterDataField.setProName("pro"+(i+1));

                //"1" 表示该字段可以为空，"0" 表示字段不能为空
                if("0".equals(masterDataField.getNullable())){
                    masterDataField.setStartLen(1);
                }

                if("number".equalsIgnoreCase(masterDataField.getFieldType())){
                    masterDataField.setValidateType("number");
                }
                if("email".equalsIgnoreCase(masterDataField.getFieldName())){
                    masterDataField.setValidateType("email");
                }

                //如果FIELD_TYPE 为字典型或主数据型，根据各自方法获取页面下拉列表的值
                if("dt".equalsIgnoreCase(masterDataField.getFieldType())){     //dt 代表字典类型
                    if(masterDataField.getTypeCode()!=null && !"".equals(masterDataField.getTypeCode())){
                        List<NameCode> dictNameLic = dipDictionaryService.getDictNameListByCode(masterDataField.getTypeCode());
                        if (dictNameLic != null && dictNameLic.size() > 0) {
                            masterDataField.setValueLic(dictNameLic);
                        }
                    }
                }else if("md".equalsIgnoreCase(masterDataField.getFieldType())){ //md 代表主数据类型
                    if(masterDataField.getTypeCode()!=null && !"".equals(masterDataField.getTypeCode())){
                        //typeCode 存的是主数据的编码信息，可以根据其获取具体的主数据表
                        MasterData masterData = masterDataService.getMasterDataByCode(masterDataField.getTypeCode());
                        MasterDataQuery masterDataQuery = new MasterDataQuery();
                        masterDataQuery.setDbName(masterData.getTableName());
                        List<NameCode> mdNameLic = masterDataService.getMDNameLic(masterDataQuery);
                        if(mdNameLic!=null && mdNameLic.size()>0){
                            masterDataField.setValueLic(mdNameLic);
                        }
                    }
                }
            }
        }
        map.put("displayName", displayName);
        map.put("fieldName", fieldName);
        map.put("displayFlag", displayFlag);
        map.put("proName", proName);
        map.put("fieldType", fieldType);
        map.put("masterDataFieldLic", masterDataFieldLic);
    }

    /**
     * 主数据内容 分页列表
     * @param page
     * @param masterDataQuery
     * @return
     */
    @RequestMapping("maindata/masterDataList")
    public @ResponseBody JsonGrid masterDataList(Pagination<MDContentObject> page, MasterDataQuery masterDataQuery) {
        if (masterDataQuery.getMdCodeValue() != null && !"".equals(masterDataQuery.getMdCodeValue())) {
            String mdCodeValue = Encodes.urlDecode(masterDataQuery.getMdCodeValue());
            masterDataQuery.setMdCodeValue(EncodingTool.escapeStr(mdCodeValue));
        }
        if(masterDataQuery.getMdNameValue() != null && !"".equals(masterDataQuery.getMdNameValue())){
            String mdNameValue = Encodes.urlDecode(masterDataQuery.getMdNameValue());
            masterDataQuery.setMdNameValue(EncodingTool.escapeStr(mdNameValue));
        }
        Pagination<MDContentObject> p = masterDataService.mtDetailPage(page, masterDataQuery);
        JsonGrid jGrid = new JsonGrid("success", p.getTotalCount(), p.getData());
        return jGrid;
    }

    /**
     * 根据ID加载数据
     *
     * @param masterDataQuery
     * @return mdContentObject
     */
    @RequestMapping("maindata/masterDataEdit")
    public @ResponseBody MDContentObject loadMD(@ModelAttribute MasterDataQuery masterDataQuery) {
        masterDataQuery.setPrimaryKeyField("ID_");
        MDContentObject mdContentObject = masterDataService.getMtDetailsById(masterDataQuery);
        return mdContentObject;
    }

    /**
     * MD 新增 删除
     *
     * @param request
     * @param masterDataQuery
     * @return map
     */
    @RequestMapping("maindata/saveMDContentInfo")
    public @ResponseBody Map<String, Object> saveMDContentInfo(HttpServletRequest request,
                                          @ModelAttribute MasterDataQuery masterDataQuery) {
        boolean bool = false;
        String message = "";
        List valueList = new ArrayList();
        // 处理传值
        String[] arr = new String[0];
        String[] fieldV = new String[0];
        String[] fieldType = new String[0];
        if (masterDataQuery.getCorres() != null && masterDataQuery.getCorres().length() > 0) {
            arr = masterDataQuery.getCorres().split(",");
            fieldV = masterDataQuery.getDbFields().split(",");
            fieldType = masterDataQuery.getFieldType().split(",");
        }
        if(masterDataQuery.getPrimaryKeyValue()!=null && !"".equals(masterDataQuery.getPrimaryKeyValue())){
            valueList.add(masterDataQuery.getPrimaryKeyValue());
        }else{
            valueList.add(UUIDUtil.getUuid());
        }
        for(int i=0; i<arr.length; i++){
            String value = request.getParameter(arr[i]);
            //如果是MD_NAME,MD_CODE的值，去除所有空格，其他去除首尾空格
            if(fieldV[i].equalsIgnoreCase("MD_NAME") || fieldV[i].equalsIgnoreCase("MD_CODE")){
                valueList.add(value.replace(" ",""));
            }else if("datatime".equals(fieldType[i])){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                try {
                    if (value == null || "".equals("")) {
                        valueList.add(sdf.parse(DateUtil.getCurrentDate("yyyyMMdd")));
                    } else {
                        valueList.add(sdf.parse(value));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {
                valueList.add(value.trim());
            }
        }
        masterDataQuery.setPrimaryKeyField("ID_");
        masterDataQuery.setValueList(valueList);
        int count = masterDataService.saveMDContentInfo(masterDataQuery);
        Map<String, Object> map = new HashMap<String, Object>();
        if(count == -1){
            message = "该编码已经存在，保存失败！";
        }else if (count >= 1){
            message = "保存成功！";
            bool = true;
        }else{
            message = "保存失败！";
        }
        map.put("bool", bool);
        map.put("msg", message);
        return map;
    }

    /**
     *
     * MD删除 支持批量删除
     *
     * @param ids
     * @return map
     */
    @RequestMapping("maindata/removeMDContentInfo")
    @ResponseBody
    public Map<String, Object> removeMDContentInfo(@ModelAttribute
                                             MasterDataQuery masterDataQuery, @RequestParam("id")
                                             String ids) {
        masterDataQuery.setId(ids);
        masterDataQuery.setPrimaryKeyField("ID_");
        String msg = "";
        int result = masterDataService.removeMDContentInfo(masterDataQuery);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", result == 1 ? true : false);
        map.put("msg", msg);
        return map;
    }
}
