package com.isell.ei.shop.kalemao.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.ei.shop.kalemao.service.KalemaoService;


/**
 * 卡乐猫推送接口
 * 
 * @author maowiejie
 * @version [版本号, 2016年2月25日]
 */
@Controller
@RequestMapping("kalemao")
public class KalemaoController {
	
	private static final Logger log = Logger.getLogger(KalemaoController.class);
	
	@Resource
	private KalemaoService kalemaoService;
	
	@ResponseBody
	@RequestMapping("upWayBillNo")
	public JsonData upWayBillNo(@RequestBody List<Map<String, String>> list){
		log.info("get upWayBillNo ok!");
		 return kalemaoService.upWayBillNo(list);
	}
}
