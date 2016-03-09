package com.isell.app.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.app.dao.BmsManageMapper;
import com.isell.app.dao.entity.BillReqParam;
import com.isell.app.dao.entity.GoodsReqParam;
import com.isell.app.dao.entity.OrderDetail;
import com.isell.app.dao.entity.OrderGoods;
import com.isell.app.dao.entity.OrderParam;
import com.isell.app.dao.entity.OrderSumInfo;
import com.isell.app.dao.entity.PulldownEntity;
import com.isell.app.service.BmsManageService;
@Service("bmsManageService")
public class BmsManageServiceImpl implements BmsManageService {

	@Resource
	private BmsManageMapper bmsMapper;

	@Override
	public List<OrderDetail> queryBmsMyOrder(BillReqParam billParam) {
		
		return this.bmsMapper.queryBmsMyOrder(billParam);
	}

	@Override
	public Integer queryBillCount(BillReqParam billParam) {

		return this.bmsMapper.queryBillCount(billParam);
	}

	@Override
	public List<OrderGoods> sumGoodsInfoList(GoodsReqParam params) {
		
		return this.bmsMapper.sumGoodsInfoList(params);
	}

	@Override
	public List<OrderGoods> sumAllGoodsInfo(GoodsReqParam params) {
		return this.bmsMapper.sumAllGoodsInfo(params);
	}

	@Override
	public List<OrderSumInfo> sumAllOrderInfo(OrderParam params) {

		return this.bmsMapper.sumAllOrderInfo(params);
	}

	@Override
	public Integer getCountOfShopNewGoods(OrderParam params) {

		return this.bmsMapper.getCountOfShopNewGoods(params);
	}

	@Override
	public BigDecimal getUnsettleAmount(OrderParam params) {

		return this.bmsMapper.getUnsettleAmount(params);
	}

	@Override
	public List<PulldownEntity> getBrandAsPullDown() {

		return this.bmsMapper.getBrandAsPullDown();
	}
}
