package com.isell.demo.gztg.controlloer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.demo.gztg.bean.Manifest;
import com.isell.demo.gztg.bean.ResponseMessage;
import com.isell.demo.gztg.bean.Result;
import com.isell.demo.gztg.service.GztgService;

@Controller
@RequestMapping("demo/gtzg")
public class GztgController {
	
	@Resource
	private GztgService gztgService;
	
	@RequestMapping("getGtzg")
	@ResponseBody
	public Result getGztg(@RequestBody Manifest manifest){
			gztgService.getGztg(manifest);
		  return null;
	}
	

	@RequestMapping("getResponse")
	@ResponseBody
	public ResponseMessage getResponse(String messageId){
		ResponseMessage rm =gztgService.getResponse(messageId);
		return rm;
	}
	
	 
}
