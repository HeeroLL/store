package com.isell.task.orderReturn.service;

import java.util.List;

import com.isell.service.order.vo.OrderReturn;

/**
 * 接口回调方法
 * @author yang
 *
 */
public interface TaskOrderReturnService {
	/**
	 * 查询所有需要再次发起回调的记录
	 * @return
	 */
	List<OrderReturn>queryAllOrderReturnList();
	/**
	 * 更新记录成功标识
	 * @param orderReturn
	 * @return
	 */
	int changeRecSuccessFlag(OrderReturn orderReturn);
}
