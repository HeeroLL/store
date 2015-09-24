package com.isell.ps;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.isell.bis.auth.BisValidator;
import com.isell.bis.auth.bean.RequestParameter;


/**
 * 对外公共服务统一网关入口
 * 
 * @author lilin
 * @version [版本号, 2015年9月24日]
 */
@Controller
public class GatewayController {
    /**
     * 验证器
     */
    @Qualifier(value = "basicValidator")
    @Resource
    private BisValidator validator;
    
    /**
     * 统一网关入口负责校验请求合法性，和转发合法请求到指定控制器
     *
     * @param service 接口服务名
     * @param param 参数
     * @return 处理结果
     */
    @RequestMapping("gateway")
    public ModelAndView gateway(String service, RequestParameter param) {
        if (service == null) {
            throw new RuntimeException("exception.access.service.null");
        }
        // 先验证参数合法性
        validator.validate(param);
        
        return new ModelAndView(service.replace("_", "/"));//默认为forward模式   
    }
}
