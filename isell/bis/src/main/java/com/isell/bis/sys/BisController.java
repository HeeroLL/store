package com.isell.bis.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公共Controller
 * 
 * @author lilin
 * @version [版本号, 2015年10月19日]
 */
@Controller
public class BisController {
	
    /**
     * 404异常时提示的信息
     *
     * @return 提示信息
     */
	@RequestMapping("404")
    @ResponseBody
	public String to404Page() {
	    throw new RuntimeException("exception.access.service.null");
    }

}
