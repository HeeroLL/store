package com.isell.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.app.dao.AppSellMapper;
import com.isell.app.dao.AppUserMapper;
import com.isell.app.dao.entity.CenterOrder;
import com.isell.app.dao.entity.CenterOrderParam;
import com.isell.app.dao.entity.Product;
import com.isell.app.dao.entity.SearchParam;
import com.isell.app.service.AppSellService;
import com.isell.core.util.CommonUtils;

@Service("appSellService")
public class AppSellServiceImpl implements AppSellService{
	
	@Resource
	private AppSellMapper appSellMapper;
	@Resource
	private AppUserMapper appUserMapper;
	/**
	 * 查询卖家订单
	 */
	public List<CenterOrder>querySellMyOrderPage(CenterOrderParam centerOrderParam)
	{
		List<CenterOrder>list= this.appSellMapper.querySellMyOrderPage(centerOrderParam);
		for(CenterOrder info:list)
		{
			info.setImgdomain(centerOrderParam.getImgdomain());
			info.setOrderItemlist(this.appUserMapper.queryOrderItem(info));
		}
		return list;
	}
	/**
	 * 商品批量配置新品（上/下）
	 */
	public int batchUpdateGoodsIsNew(SearchParam param)
	{
		int result=0;
		String [] goods=param.getKeywords().split(",");
		result=this.appSellMapper.batchUpdateGoodsIsNew(param,goods);
		return result;
	}
	/**
	 * 商品批量上架/下架功能
	 */
	public int batchUpdateGoodsIsAdd(SearchParam param)
	{
		int result=0;
		String [] goods=param.getKeywords().split(",");
		if(param.getType()==1)//上架
		{
			for(int i=0;i<goods.length;i++)
			{
				param.setcId(Integer.parseInt(goods[i]));
				param.setUncode(CommonUtils.uuid());
				result=this.appSellMapper.batchUpdateGoodsIsAdd(param);
			}
		}else
		{
			//下架
			for(int i=0;i<goods.length;i++)
			{
				param.setcId(Integer.parseInt(goods[i]));
				result=this.appSellMapper.deleteShopGoodsForSell(param);
			}
		}
		return result;
	}
	/**
	 * 获取已上架/未上架/新品上线商品（搜索）
	 */
	public List<Product>querySellGoodsListSearch(SearchParam searchParam)
	{
		List<Product>list=new ArrayList<Product>();
		if(searchParam. getType()==1)
		{
			//查询已上架的商品
			
			
		}else if(searchParam.getType()==2)
		{
			//查询未上架的商品
		}else if(searchParam.getType()==3)
		{
			//查询新品上线的商品
			
			
		}
		if(list.size()>0)
		{
			//查询商品下面的规格
			
			
		}
		
		return list;
	}
}
