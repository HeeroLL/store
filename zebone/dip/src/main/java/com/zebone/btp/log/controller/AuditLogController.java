/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-11-27     审计日志管理控制层
 */
package com.zebone.btp.log.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.log.pojo.AuditLogInfo;
import com.zebone.btp.log.service.AuditLogService;

/**
 * 审计日志管理控制层
 *
 * @author lilin
 * @version [版本号, 2012-11-27]
 */
@Controller
@RequestMapping("btp/log")
public class AuditLogController
{
    /**
     * 审计日志业务接口
     */
    @Autowired
    @Qualifier(value = "btpLog")
    private AuditLogService auditLogService;

    /**
     * 审计日志列表页面
     *
     * @return url
     */
    @RequestMapping("/index")
    public String logIndex()
    {
        return "btp/log/log_index";
    }

    /**
     * 查询一条日志的完整信息
     *
     * @param logId logId
     * @param modelMap modelMap
     * @return url
     */
    @RequestMapping("/findLogById")
    public String findLogById(String logId, ModelMap modelMap)
    {
        AuditLogInfo auditLogInfo = auditLogService.findLogById(logId);
        modelMap.addAttribute("auditLogInfo", auditLogInfo);
        return "btp/log/log_detail";
    }

    /**
     * 分页查询日志信息
     *
     * @param auditLogInfo 查询条件集合
     * @param page 分页对象
     * @return list
     */
    @RequestMapping("/searchLog")
    @ResponseBody
    public JsonGrid searchLog(AuditLogInfo auditLogInfo, Pagination<AuditLogInfo> page)
    {
        page = auditLogService.searchLog(page.getRowBounds(), auditLogInfo);

        // 调用 JSONGrid的构造方法，对分页数据进行在处理
        JsonGrid jsonGrid = new JsonGrid("success", page.getTotalCount(), page.getData());

        return jsonGrid;
    }

    /**
     * 删除审计日志
     *
     * @param id 日志id
     * @return 结果
     */
    @RequestMapping("/removeLog")
    @ResponseBody
    public Map<String, Object> removeLog(String id)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        auditLogService.deleteLog(id.split(","));
        map.put("success", true);
        return map;
    }
}
