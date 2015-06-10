package com.zebone.dip.auditlog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.auditlog.vo.AuditLog;
import com.zebone.btp.core.mybatis.page.PageConfig;
import com.zebone.btp.core.mybatis.page.PageInfo;
import com.zebone.dip.auditlog.service.AuditlogService;
import com.zebone.util.PropertyOptions;

/**
 * 
 * 审计日志控制层
 * 
 * @author lilin
 * @version [版本号, 2015年6月1日]
 */
@Controller
@RequestMapping("auditlog")
public class AuditlogController {
    
    /**
     * 审计日志服务接口
     */
    @Resource
    private AuditlogService auditlogService;
    
    /**
     * 事件类型map
     */
    @Resource
    private Map<String, String> eventTypeMap;
    
    /**
     * 操作类型map
     */
    @Resource
    private Map<String, String> optTypeMap;
    
    /**
     * 审计日志主页
     * 
     * @return URL
     */
    @RequestMapping("/auditlogIndex")
    public String auditlogIndex() {
        return "dip/auditlog/auditlogIndex";
    }
    
    /**
     * 获取操作类型
     *
     * @return 操作类型集合
     */
    @RequestMapping("/getOptType")
    @ResponseBody
    public List<PropertyOptions> getOptType(){
        List<PropertyOptions> optionsList = new ArrayList<PropertyOptions>();
        for (Entry<String, String> entry : optTypeMap.entrySet()) {
            PropertyOptions option = new PropertyOptions(entry.getKey(), entry.getValue());
            optionsList.add(option);
        }
        return optionsList;
    }
    
    /**
     * 获取事件类型
     *
     * @return 事件类型集合
     */
    @RequestMapping("/getEventType")
    @ResponseBody
    public List<PropertyOptions> getEventType(){
        List<PropertyOptions> optionsList = new ArrayList<PropertyOptions>();
        for (Entry<String, String> entry : eventTypeMap.entrySet()) {
            PropertyOptions option = new PropertyOptions(entry.getKey(), entry.getValue());
            optionsList.add(option);
        }
        return optionsList;
    }
        
    /**
     * 获取日志信息表格数据
     * 
     * @param auditLog 查询条件
     * @param page 分页参数
     * @return pageInfo
     */
    @RequestMapping("/getAuditlogDataGrid")
    @ResponseBody
    public PageInfo<AuditLog> getAuditlogDataGrid(AuditLog auditLog, PageConfig page) {
        return auditlogService.findAuditlogList(page, auditLog);
    }
}
