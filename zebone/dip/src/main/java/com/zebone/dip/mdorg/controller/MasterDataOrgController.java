package com.zebone.dip.mdorg.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.dip.dictionary.vo.DipDictionaryType;
import com.zebone.dip.md.vo.MasterData;
import com.zebone.dip.md.vo.MasterDataField;
import com.zebone.dip.mdorg.service.MasterDataOrgService;
import com.zebone.util.UUIDUtil;


/**
 * 主数据管理
 *
 * @author 杨英
 * @version 2013-7-17 上午10:52:03
 */
@Controller
public class MasterDataOrgController extends BaseController {


    @Resource
    private MasterDataOrgService masterDataStruService;

    @RequestMapping("masterDataStru/masterDataStruindex")
    public String tableStructure(HttpServletRequest request, HttpServletResponse response) {
        return "dip/masterDataStru/masterDataStru_index";
    }

    @RequestMapping("masterDataStru/index.zb")
    public String masterDataStruindex(HttpServletRequest request, HttpServletResponse response) {
        return "dip/masterDataStru/index";
    }

    @RequestMapping("masterDataStru/masterDataStruList")
    @ResponseBody
    public JsonGrid MasterDataStruList(MasterData masterData, Pagination<MasterData> page) {

        Pagination<MasterData> p = masterDataStruService.MasterDataStruList(page, masterData);
        JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
        return jGrid;
    }

    @RequestMapping("masterDataStru/masterDataFieldList")
    public @ResponseBody JsonGrid masterDataFieldList(MasterDataField masterDataField,
                                 Pagination<MasterDataField> page) {
        Pagination<MasterDataField> p = masterDataStruService.masterDataFieldPage(page, masterDataField);
        JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
        return jGrid;
    }


    @RequestMapping("masterDataStru/masterDataStruEdit")
    public String masterDataStruEdit(@RequestParam("id") String id, ModelMap map) {
        MasterData masterData = masterDataStruService.getmasterDataStruInfoById(id);
        map.put("masterData", masterData);
        if (masterData != null) {
            int result = masterDataStruService.isMDTableExist(masterData.getTableName());
            map.put("mdTableExist", result > 0 ? true : false);
        }
        return "dip/masterDataStru/masterDataStru_edit";
    }


    @RequestMapping("masterDataStru/saveMasterDataStruInfo")
    public @ResponseBody Map<String, Object> saveMasterDataStruInfo(MasterData masterData) {
        int result = 0;
        String msg = "";
        boolean bool = false;
        if (StringUtils.isEmpty(masterData.getId())) {
            masterData.setId(UUIDUtil.getUuid());
            result = masterDataStruService.saveMasterDataStruInfo(masterData);
            if (result == -1) {
                msg = "数据库表名称已经存在，保存失败";
            } else if (result == -2) {
                msg = "主数据名称已经存在，保存失败";
            } else if (result == -3) {
                msg = "主数据编码已经存在，保存失败";
            } else if (result > 0) {
                msg = "保存成功";
                bool = true;
            } else {
                msg = "保存失败";
            }
        } else {
            result = masterDataStruService.updateMasterDataStruInfo(masterData);
            if (result == -1) {
                msg = "数据库表名称已经存在，更新失败";
            } else if (result == -2) {
                msg = "主数据名称已经存在，保存失败";
            } else if (result == -3) {
                msg = "主数据编码已经存在，保存失败";
            } else if (result > 0) {
                msg = "更新成功";
                bool = true;
            } else {
                msg = "更新失败";
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", bool);
        map.put("msg", msg);
        map.put("id", masterData.getId());
        return map;
    }

    @RequestMapping("masterDataStru/removeMasterDataStruByIds")
    public @ResponseBody Map<String, Object> removeMasterDataStruByIds(@RequestParam("id") String id) {
        int result = 0;
        String msg = "";
        result = masterDataStruService.removeMasterDataStruByIds(id.split(","));
        if (result == 2) {
            msg = "主数据表已经创建，结构不能删除";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", result == 1 ? true : false);
        map.put("msg", msg);
        return map;
    }

    @RequestMapping("masterDataStru/masterDataStruFieldEdit")
    public String masterDataStruFieldEdit(@RequestParam("masterDataId") String masterDataId, @RequestParam("id") String id,
                                          ModelMap map) {
        MasterDataField masterDataField = masterDataStruService.getMasterDataStruFieldInfoById(id);
        if (masterDataField == null) {
            masterDataField = new MasterDataField();
            masterDataField.setMasterDataId(masterDataId);
        }
        map.put("masterDataField", masterDataField);
        return "dip/masterDataStru/masterDataStruField_edit";
    }


    @RequestMapping("masterDataStru/saveMasterDataStruFieldInfo")
    public @ResponseBody Map<String, Object> saveMasterDataStruFieldInfo(MasterDataField masterDataField) {
        int result = 0;
        String msg = "";
        boolean bool = false;
        if (StringUtils.isEmpty(masterDataField.getId())) {
            masterDataField.setId(UUIDUtil.getUuid());
            result = masterDataStruService.saveMasterDataStruFieldInfo(masterDataField);
            if (result == -1) {
                msg = "该字段名已经存在，保存失败";
            } else if (result > 0) {
                msg = "保存成功";
                bool = true;
            } else {
                msg = "保存失败";
            }
        } else {
            result = masterDataStruService.updateMasterDataStruFieldInfo(masterDataField);
            if (result == -1) {
                msg = "该字段名已经存在，更新失败";
            } else if (result > 0) {
                msg = "更新成功";
                bool = true;
            } else {
                msg = "更新失败";
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", bool);
        map.put("msg", msg);
        map.put("id", masterDataField.getId());
        return map;
    }


    @RequestMapping("masterDataStru/removeMasterDataStruFieldByIds")
    public @ResponseBody Map<String, Object> removeMasterDataStruFieldByIds(@RequestParam("id") String id) {
        int result = 0;
        String msg = "";
        result = masterDataStruService.removeMasterDataStruFieldByIds(id.split(","));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", result == 1 ? true : false);
        map.put("msg", msg);
        return map;
    }

    /**
     * 判断主数据表是否已经创建
     * @param mdTableName
     * @return
     */
    @RequestMapping("masterDataStru/isMDTableExist")
    @ResponseBody
    public Map<String, Object> isMDTableExist(@RequestParam("mdTableName")String mdTableName){
        int result = masterDataStruService.isMDTableExist(mdTableName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mdTableExist", result>0?true:false);
        return map;
    }

    @RequestMapping("masterDataStru/getMatchInfo")
    @ResponseBody
    public Map<String, Object> getMatchInfo(@RequestParam("fieldType")String fieldType,@RequestParam("query") String name){
        List<DipDictionaryType> list = masterDataStruService.getMatchInfo(fieldType, name);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", list);
        map.put("success", true);
        int total =0;
        if(list != null && list.size() > 0){
            total = list.size();
        }
        map.put("query", name);
        map.put("total", total);
        return map;
    }

    @RequestMapping("masterDataStru/generateSql")
    public String generateSql(@RequestParam("masterDataId") String masterDataId,
                              @RequestParam("tableName") String tableName, ModelMap map) {
        String sql = masterDataStruService.generateSql(masterDataId,tableName);
        map.put("sql", sql);
        return "dip/masterDataStru/masterDataStru_sql";
    }

}
