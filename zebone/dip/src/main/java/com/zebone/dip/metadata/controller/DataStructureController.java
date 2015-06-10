package com.zebone.dip.metadata.controller;

import java.util.HashMap;
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
import com.zebone.dip.metadata.service.DataStructureService;
import com.zebone.dip.metadata.vo.FieldColumnConf;
import com.zebone.dip.metadata.vo.FieldTableConf;
import com.zebone.util.Encodes;
import com.zebone.util.EncodingTool;
import com.zebone.util.UUIDUtil;

/**
 * Created with IntelliJ IDEA.
 * User: yang ying
 * Date: 13-6-25
 * Time: 上午8:32
 * To change this template use File | Settings | File Templates.
 */

@Controller
public  class DataStructureController extends BaseController {

    @Resource
    private DataStructureService dataStructureService;

    @RequestMapping("metadata/tablestruIndex")
    public String tableStructure (HttpServletRequest request, HttpServletResponse response){
        return "dip/metadata/tableStru_index";
    }

//    @RequestMapping("metadata/tablefieldIndex")
//    public String tableField (@RequestParam("id") String id,@RequestParam("tableName") String tableName, ModelMap map){
//        map.put("tableId", id);
//        map.put("tableName",tableName);
//        return "dip/metadata/tableField_index";
//    }

    @RequestMapping("metadata/tableStruEdit")
    public String tableStruEdit(@RequestParam("id") String id, ModelMap map) {
        FieldTableConf fieldTableConf = dataStructureService.getTableStruInfoById(id);
        map.put("fieldTableConf", fieldTableConf);
        return "dip/metadata/tableStru_edit";
    }

    @RequestMapping("metadata/tableFieldEdit")
    public String tableFieldEdit(@RequestParam("tableId") String tableId, @RequestParam("id") String id,
                                 ModelMap map) {
        FieldColumnConf  fieldColumnConf = dataStructureService.getTableFieldInfoById(id);
        if(fieldColumnConf == null){
            fieldColumnConf = new FieldColumnConf();
            fieldColumnConf.setTableId(tableId);
        }
        map.put("fieldColumnConf",fieldColumnConf);
        return "dip/metadata/tableField_edit";
    }

    @RequestMapping("metadata/tableStruList")
    @ResponseBody
    public JsonGrid tableStruList(FieldTableConf fieldTableConf, Pagination<FieldTableConf> page) {
        if (fieldTableConf.getTableName() != null && !"".equals(fieldTableConf.getTableName())) {
            String tableName = Encodes.urlDecode(fieldTableConf.getTableName());
            fieldTableConf.setTableName(EncodingTool.escapeStr(tableName));
        }
        if (fieldTableConf.getTableDesc() != null && !"".equals(fieldTableConf.getTableDesc())) {
            String tableDesc = Encodes.urlDecode(fieldTableConf.getTableDesc());
            fieldTableConf.setTableDesc(EncodingTool.escapeStr(tableDesc));
        }
        Pagination<FieldTableConf> p = dataStructureService.tableStruPage(page, fieldTableConf);
        JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
        return jGrid;
    }

    @RequestMapping("metadata/tableFieldList")
    public @ResponseBody JsonGrid tableFieldList(FieldColumnConf fieldColumnConf,
                                   Pagination<FieldColumnConf> page) {
        Pagination<FieldColumnConf> p = dataStructureService.tableFieldPage(page, fieldColumnConf);
        JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
        return jGrid;
    }

    @RequestMapping("metadata/saveTableStruInfo")
    public @ResponseBody Map<String, Object> saveTableStruInfo(FieldTableConf fieldTableConf){
        int result = 0;
        String msg = "";
        boolean bool = false;
        if(StringUtils.isEmpty(fieldTableConf.getId())){
            fieldTableConf.setId(UUIDUtil.getUuid());
            fieldTableConf.setIsDeleted("0");
            result = dataStructureService.saveTableStruInfo(fieldTableConf);
            if(result == -1 ){
                msg = "该表名已经存在，保存失败";
            } else if (result > 0) {
                msg = "保存成功";
                bool = true;
            }else{
                msg = "保存失败";
            }
        }else{
            result = dataStructureService.updateTableStruInfo(fieldTableConf);
            if (result == -1) {
                msg = "该表名已经存在，更新失败";
            } else if (result > 0) {
                msg = "更新成功";
                bool = true;
            }else{
                msg = "更新失败";
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", bool);
        map.put("msg", msg);
        map.put("id", fieldTableConf.getId());
        return map;
    }

    @RequestMapping("metadata/removeTableStruByIds")
    public @ResponseBody Map<String, Object> removeTableStruByIds(@RequestParam("id") String id) {
        int result = 0;
        String msg = "";
        result = dataStructureService.removeTableStruByIds(id.split(","));
        if(result == 2){
            msg = "表结构与表字段绑定，无法删除";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", result == 1 ? true : false);
        map.put("msg", msg);
        return map;
    }

    @RequestMapping("metadata/saveTableFieldInfo")
    public @ResponseBody Map<String, Object> saveTableFieldInfo(FieldColumnConf fieldColumnConf) {
        int result = 0;
        String msg = "";
        boolean bool = false;
        if (StringUtils.isEmpty(fieldColumnConf.getId())) {
            fieldColumnConf.setId(UUIDUtil.getUuid());
            fieldColumnConf.setIsDeleted("0");
            result = dataStructureService.saveTableFieldInfo(fieldColumnConf);
            if (result == -1) {
                msg = "该字段名已经存在，保存失败";
            } else if (result > 0) {
                msg = "保存成功";
                bool = true;
            } else {
                msg = "保存失败";
            }
        } else {
            result = dataStructureService.updateTableFieldInfo(fieldColumnConf);
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
        map.put("id", fieldColumnConf.getId());
        return map;
    }

    @RequestMapping("metadata/removeTableFieldByIds")
    public @ResponseBody Map<String, Object> removeTableFieldByIds(@RequestParam("id") String id) {
        int result = 0;
        String msg = "";
        result = dataStructureService.removeTableFieldByIds(id.split(","));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", result == 1 ? true : false);
        map.put("msg", msg);
        return map;
    }
}
