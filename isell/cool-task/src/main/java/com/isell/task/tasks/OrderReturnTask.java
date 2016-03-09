package com.isell.task.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.order.vo.OrderReturn;
import com.isell.task.orderReturn.service.TaskOrderReturnService;

/**
 * 接口回调任务task 五分钟一次
 * 
 * @author yang
 * 
 */
@Component("returnTask")
public class OrderReturnTask {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(OrderReturnTask.class);
    
    @Resource
    private TaskOrderReturnService taskOrderReturnService;
    
    /**
     * 定时任务到达执行的方法
     */
    public void execute() {
        
        log.info("in OrderReturnTask");
        try {
            List<OrderReturn> list = taskOrderReturnService.queryAllOrderReturnList();
            for (OrderReturn info : list) {
                Map<String, String> map = new HashMap<String, String>();
                
                map.put("orderNo", info.getOrderno());
                map.put("state", info.getState());
                map.put("reason", info.getReason());
                map.put("waybillNo", info.getWaybillNo());
                map.put("sendStyle", info.getSendStyle());
                map.put("pid", String.valueOf(info.getPid()));
                map.put("gid", String.valueOf(info.getGid()));
                map.put("price", info.getPrice());
                
                String result = "";
                
                result = HttpUtils.httpPost(info.getReturnurl(), map);
                
                if (!"".equals(result)) {
                    log.info(info.getOrderno() + ":Success");
                    JsonData returnData = JsonUtil.readValue(result, JsonData.class);
                    if (returnData.getSuccess()) {
                        // 更新记录成功
                        
                        this.taskOrderReturnService.changeRecSuccessFlag(info);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        
        log.info("out OrderReturnTask");
        
    }
    
}
