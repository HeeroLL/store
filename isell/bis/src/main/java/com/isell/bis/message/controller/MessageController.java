package com.isell.bis.message.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.message.service.MessageService;
import com.isell.service.message.vo.CoolMessage;

/**
 * 消息controller
 * 
 * @author wangpeng
 * @version [版本号, 2015-11-12]
 */
@Controller
@RequestMapping("message")
public class MessageController {
	
	/**
	 * 消息接口
	 */
	@Resource
	private MessageService messageService;
	
	/**
     * 获取消息列表接口
     *
     * @param jsonObj 酷店参数
     * @return 消息列表
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("getMessage")
    @ResponseBody
    public JsonData getMessage(String jsonObj, String accessCode) {
    	CoolMessage message = JsonUtil.readValue(jsonObj, CoolMessage.class);
    	JsonData jsonData = new JsonData();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = messageService.getMessageList(message).getColumns();
        jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
        resultMap.remove("success");
        if (resultMap.get("msg") != null) {
            jsonData.setMsg((String)resultMap.get("msg"));
            resultMap.remove("msg");
        }        
        if (resultMap.get("messageList") != null) {
        	jsonData.setRows((List<CoolMessage>)resultMap.get("messageList"));
        	resultMap.remove("messageList");
        }
        jsonData.setData(resultMap);
        return jsonData;
    }

}
