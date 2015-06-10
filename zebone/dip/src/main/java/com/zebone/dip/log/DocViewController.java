package com.zebone.dip.log;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.log.service.DocViewLogService;
import com.zebone.dip.log.service.LogService;
import com.zebone.dip.log.vo.DocViewLog;
import com.zebone.dip.log.vo.ParmInfo;
import com.zebone.util.Encodes;
import com.zebone.util.EncodingTool;

/**
* 文档调阅日志控制层
* @author 杨英
* @version 2013-11-20 下午1:19
*/
@Controller
public class DocViewController {
    @Resource
    private DocViewLogService docViewService;

    @Resource
    private LogService logService;

    /**
     * 档案调阅日志页面
     * @param map
     * @return
     */
    @RequestMapping("log/docViewIndex")
    public String docViewIndex(ModelMap map){
        List<ParmInfo> docTypeList = logService.getDictInfo();
        List<ParmInfo> cardTypeList = docViewService.getCardInfo();
        map.put("docTypeList", docTypeList);
        map.put("cardTypeList", cardTypeList);
        return "dip/log/docView_index";
    }

    @RequestMapping("log/viewOrgInfo")
    @ResponseBody
    public JsonGrid viewOrgInfo(@RequestParam("query")String name,Pagination<ParmInfo> page){
        Pagination<ParmInfo> p = logService.getOrganInfo(name,page);
        JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
        return jGrid;
    }

    /**
     * 档案调阅日志 分页列表
     * @param page
     * @param docViewLog
     * @return
     */
    @RequestMapping("log/docViewLogList")
    @ResponseBody
    public JsonGrid docViewLogList(DocViewLog docViewLog,Pagination<DocViewLog> page){
        if(!StringUtils.isEmpty(docViewLog.getStartDt())){
            docViewLog.setStartDt(formatDate(docViewLog.getStartDt(),true));
        }
        if(!StringUtils.isEmpty(docViewLog.getEndDt())){
            docViewLog.setEndDt(formatDate(docViewLog.getEndDt(),false));
        }
        if(!StringUtils.isEmpty(docViewLog.getName())){
            String name = Encodes.urlDecode(docViewLog.getName());
            docViewLog.setName(EncodingTool.escapeStr(name));
        }
        Pagination<DocViewLog> p = docViewService.getDocViewLogPage(docViewLog,page);
        JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
        return jGrid;
    }

    /**
     * 格式化查询日期
     * @param date
     * @return
     */
    public String formatDate(String date, boolean isStartDt) {
        String rtnStr = "";
        String[] strLic = date.split("-");
        if (strLic != null && strLic.length > 0) {
            for (int i = 0; i < strLic.length; i++) {
                rtnStr = rtnStr + strLic[i];
            }
        }
        if (isStartDt) {
            rtnStr = rtnStr + "000000";  //查询起始日期
        } else {
            rtnStr = rtnStr + "235959";  //查询截止日期
        }
        return rtnStr;
    }
}
