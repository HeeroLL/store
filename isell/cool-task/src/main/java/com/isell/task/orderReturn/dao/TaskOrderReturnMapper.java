package com.isell.task.orderReturn.dao;

import java.util.List;

import com.isell.core.mybatis.Mapper;
import com.isell.service.order.vo.OrderReturn;
/**
 * 接口回调mapper
 * @author yang
 *
 */
@Mapper
public interface TaskOrderReturnMapper {
	/**
	 * 查询所有需要回调记录
	 * @return
	 */
	public List<OrderReturn>queryAllOrderReturnList();
	/**
	 * 更新记录成功标识
	 * @param orderReturn
	 * @return
	 */
	public int changeRecSuccessFlag(OrderReturn orderReturn);
}
