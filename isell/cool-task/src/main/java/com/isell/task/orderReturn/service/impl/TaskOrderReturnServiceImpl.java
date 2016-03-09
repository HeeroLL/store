package com.isell.task.orderReturn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.service.order.vo.OrderReturn;
import com.isell.task.orderReturn.dao.TaskOrderReturnMapper;
import com.isell.task.orderReturn.service.TaskOrderReturnService;

/**
 * 接口回调方法
 * 
 * @author yang
 * 
 */
@Service("taskOrderReturnService")
public class TaskOrderReturnServiceImpl implements TaskOrderReturnService {
    /**
     * log
     */
    // private static final Logger log = Logger.getLogger(TaskOrderReturnServiceImpl.class);
    @Resource
    private TaskOrderReturnMapper taskOrderReturnMapper;
    
    /**
     * 查询所有需要回调的记录
     */
    @Override
    public List<OrderReturn> queryAllOrderReturnList() {
        return this.taskOrderReturnMapper.queryAllOrderReturnList();
    }
    
    /**
     * 更新记录成功标识
     */
    @Override
    public int changeRecSuccessFlag(OrderReturn orderReturn) {
        return this.taskOrderReturnMapper.changeRecSuccessFlag(orderReturn);
    }
}
