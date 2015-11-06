package com.isell.ps.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.core.util.JsonData;
import com.isell.core.util.JsonUtil;
import com.isell.service.member.vo.CoolUser;

/**
 * 用户接口controller
 * 
 * @author lilin
 * @version [版本号, 2015年11月5日]
 */
@Controller
@RequestMapping("user")
public class UserController {
    /**
     * 注册用户接口
     *
     * @param jsonObj 参数
     * @return 注册是否成功
     */
    @RequestMapping("register")
    @ResponseBody
    public JsonData register(String jsonObj) {
        CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
        // 组装封装结果
        JsonData jsonData = new JsonData();
        jsonData.setData(user);
        
        return jsonData;
    }
    
    /**
     * 校验手机接口
     *
     * @param jsonObj 参数
     * @return 手机号是否存在
     */
    @RequestMapping("checkMobile")
    @ResponseBody
    public JsonData checkMobile(String jsonObj) {
        CoolUser user = JsonUtil.readValue(jsonObj, CoolUser.class);
        user.setUsername(user.getMobile());
        user.setMobile(null);
        // 组装封装结果
        JsonData jsonData = new JsonData();
        jsonData.setSuccess(false);
        jsonData.setData(user);
        
        return jsonData;
    }
}
