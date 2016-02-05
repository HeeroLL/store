package com.isell.task.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.isell.core.util.DateUtil;
import com.isell.core.util.JaxbUtil;
import com.isell.service.custorms.dao.CoolProductCustomsNbybMapper;
import com.isell.service.custorms.po.CoolProductCustomsNbyb;
import com.isell.task.product.dao.WhProductCustomsStockConMapper;
import com.isell.task.product.dao.WhProductCustomsStockMapper;
import com.isell.task.product.service.TaskProductService;
import com.isell.task.product.vo.WhProductCustomsStock;
import com.isell.task.product.vo.WhProductCustomsStockCon;
import com.isell.ws.ningbo.bean.StockSyncBody;
import com.isell.ws.ningbo.bean.StockSyncGood;
import com.isell.ws.ningbo.bean.StockSyncMsg;
import com.isell.ws.ningbo.bean.StockSyncResult;
import com.isell.ws.ningbo.service.YoubeiService;
import com.isell.ws.ningbo.ws.stock.StockSyncWebService;
import com.isell.ws.ningbo.ws.stock.StockSyncWebServiceSoap;

/**
 * 商品接口实现
 * 
 * @author Administrator
 *
 */
@Service("taskProductService")
public class TaskProductServiceImpl implements TaskProductService {
	
	/**
	 * 保税仓库存配置类mapper
	 */
	@Resource
	private WhProductCustomsStockConMapper whProductCustomsStockConMapper;
	
	/**
	 * 保税仓库存类mapper
	 */
	@Resource
	private WhProductCustomsStockMapper whProductCustomsStockMapper;
	
	/**
	 * 宁波优贝海关备案Mapper
	 */
	@Resource
	private CoolProductCustomsNbybMapper coolProductCustomsNbybMapper;

	/**
	 * 获取宁波优贝库存
	 */
	@Override
	public void getNbybStock() {
		WhProductCustomsStockCon con = new WhProductCustomsStockCon();
		con.setCustomsType("1");
		WhProductCustomsStockCon sCon = whProductCustomsStockConMapper.getWhProductCustomsStock(con);
		boolean flag = false;
		String updateTime = "";
		if(sCon != null){
			//获得最新的更新时间
			updateTime = DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, sCon.getUpdatetime());		
			flag = true; 
		}else{
			updateTime = "2015-10-01 00:00:00";
		}
		//库存同步接口调用
		StockSyncWebServiceSoap serviceSoap = new StockSyncWebService().getStockSyncWebServiceSoap12();
		StockSyncMsg msg = new StockSyncMsg();
		msg.setShopId(YoubeiService.SHOPID.toString());
		msg.setTime(updateTime);
		String xml = JaxbUtil.convertToXml(msg);
        xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        System.out.println(xml);
		String result = serviceSoap.getAllStock(YoubeiService.ERPKEY, YoubeiService.ERPSECRET, xml);
		System.out.println(result);
		
		StockSyncResult syncResult = JaxbUtil.converyToJavaBean(result, StockSyncResult.class);
		StockSyncBody body = syncResult.getBody();
		if(body != null){
			List<StockSyncGood> goods = body.getGoods();
			if(CollectionUtils.isNotEmpty(goods)){
				for(StockSyncGood good : goods){
					String code = good.getProductCode();					
					WhProductCustomsStock stock = whProductCustomsStockMapper.getWhProductCustomsStockByCode(code);
					if(stock != null){
						stock.setStock(good.getNum());
						whProductCustomsStockMapper.updateWhProductCustomsStock(stock);
					}else{
						CoolProductCustomsNbyb nbyb = coolProductCustomsNbybMapper.getCoolProductCustomsNbybByCode(code);						
						stock = new WhProductCustomsStock();
						stock.setCustomsType(WhProductCustomsStock.CUSTOMS_TYPE_1);
						stock.setgId(nbyb.getgId());
						stock.setpId(nbyb.getpId());
						stock.setProductCode(code);
						stock.setProductName(nbyb.getProductName());
						stock.setStock(good.getNum());
						whProductCustomsStockMapper.saveWhProductCustomsStock(stock);
					}					
				}
			}
		}
		
		//处理配置表
		String sqlTime = syncResult.getSqlTime();
		if(flag){ //更新最新的同步时间
			sCon.setUpdatetime(DateUtil.parseDate(sqlTime, DateUtil.yyyy_MM_dd_HH_mm_ss));
			whProductCustomsStockConMapper.updateWhProductCustomsStockCon(sCon);
		}else{
			sCon = new WhProductCustomsStockCon();
			sCon.setCustomsType(WhProductCustomsStock.CUSTOMS_TYPE_1);
			sCon.setUpdatetime(DateUtil.parseDate(sqlTime, DateUtil.yyyy_MM_dd_HH_mm_ss));
			whProductCustomsStockConMapper.saveWhProductCustomsStockCon(sCon);
		}		
	}

}
