package com.isell.ei.shop.kalemao.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.ei.shop.kalemao.bean.KalemaoResult;
import com.isell.ei.shop.kalemao.service.KalemaoService;
/**
 * 卡乐猫业务层接口
 * 
 * @author maoweijie 
 * @version [版本号, 2016年2月25日]
 */
@Service("kalemaoService")
public class KalemaoServiceImpl implements KalemaoService{

	@Override
	public JsonData upWayBillNo(List<Map<String, String>> list) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("auth_code", AUTHCODE);
		paramMap.put("access_code", ACCESSCODE);
		paramMap.put("order_list", JsonUtil.writeValueAsString(list));
		String result = HttpUtils.httpPost(ZS_SEND_URL, paramMap);
		System.out.println(result);
		KalemaoResult kalemao = JsonUtil.readValue(result,KalemaoResult.class);
		JsonData jsonData = new JsonData();
		jsonData.setData(kalemao.getSuccess());
		jsonData.setMsg(kalemao.getMsg());
		return jsonData;
	}

}
