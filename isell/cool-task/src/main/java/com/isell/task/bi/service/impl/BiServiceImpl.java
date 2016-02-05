package com.isell.task.bi.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.isell.core.util.DateUtil;
import com.isell.service.account.dao.CoonRunAccountMapper;
import com.isell.service.bi.dao.BiCoonShopMapper;
import com.isell.service.bi.dao.BiDateMapper;
import com.isell.service.bi.vo.BiCoonShop;
import com.isell.service.bi.vo.BiDate;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.task.bi.service.BiService;

@Service("biService")
public class BiServiceImpl implements BiService {
	
	/**
	 * log
	 */
	// private static final Logger log = Logger.getLogger(BiServiceImpl.class);
	
	/**
	 * 日期表mapper
	 */
	@Resource
	private BiDateMapper biDateMapper;
	
	/**
	 * 酷店统计表mapper
	 */
	@Resource
	private BiCoonShopMapper biCoonShopMapper;
	
	/**
	 * 账单流水表mapper
	 */
	@Resource
	private CoonRunAccountMapper coonRunAccountMapper;
	
	/**
	 * 订单表mapper
	 */
	@Resource
	private CoolOrderMapper coolOrderMapper;

	/**
	 * 每天定时插入一条时间数据
	 */
	@Override
	public void setBiDate() {
		String date = "";
		String year = "";
		String quarter = "";
		String month = "";
		String day = "";
		String week = "";
		String longTime = "";		
		String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};	
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		year = cal.get(Calendar.YEAR) + "";
		int m = cal.get(Calendar.MONTH) + 1;
		if(m < 10){
			month = "0" + m;
		}else{
			month = cal.get(Calendar.MONTH) + 1 + "";
		}
		int d = cal.get(Calendar.DAY_OF_MONTH);
		if(d < 10){
			day = "0" + d;
		}else{
			day = d + "";
		}			
		if(m <= 3){
			quarter = "第一季度";
		}else if(m>=4 && m <= 6){
			quarter = "第二季度";
		}else if(m>=7 && m <= 9){
			quarter = "第三季度";
		}else if(m>=10 && m <= 12){
			quarter = "第四季度";
		}				
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
        	w = 0;
        }		            
	    week = weekDays[w];		    
	    date = year + "-" + month + "-" + day;		    
	    DateUtil.strToDate(DateUtil.yyyy_MM_dd_HH_mm_ss, date + " 00:00:00");
	    longTime = String.valueOf(DateUtil.strToDate(DateUtil.yyyy_MM_dd_HH_mm_ss, date + " 00:00:00").getTime());
	    
	    BiDate bi = biDateMapper.getBiDateByDate(date);
	    if(bi == null){
	    	BiDate bd = new BiDate(); 
			bd.setDate(date);
			bd.setYear(year);
			bd.setQuarter(quarter);
			bd.setMonth(month);
			bd.setDay(day);
			bd.setWeek(week);
			bd.setLongtime(longTime);
			biDateMapper.saveBiDate(bd);
	    }
	}

	/**
	 * 酷店统计项抽取
	 */
	@Override
	public void etlCoonShop() {
		//判断有没有数据，没有的时候需要初始化
		List<BiCoonShop> biCoonshopList = biCoonShopMapper.findAllBiCoonShop();
		if(CollectionUtils.isEmpty(biCoonshopList)){
			
		}
		
	}

}
