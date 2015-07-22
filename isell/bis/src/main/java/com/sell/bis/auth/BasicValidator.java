package com.sell.bis.auth;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.sell.bis.auth.bean.AccessSystemInfo;
import com.sell.bis.auth.bean.RequestParameter;
import com.sell.core.util.Coder;

/**
 * 基础验证类
 * 
 * @author lilin
 * @version [版本号, 2015年7月22日]
 */
@Service("basicValidator")
public class BasicValidator implements BisValidator {
    
    /**
     * 接入系统Map集合
     */
    private Map<String, AccessSystemInfo> accessSysMap;
    
    @PostConstruct
    @Override
    public void init() {
        // 系统加载时初始化认证信息 TODO 以后从数据库中读取
        accessSysMap = new HashMap<String, AccessSystemInfo>();
        accessSysMap.put("lootooker", new AccessSystemInfo("lootooker", "lootookerPrivateKEY", false));
    }
    
    @Override
    public boolean validate(RequestParameter param) {
        String accessCode = param.getAccessCode();
        if (accessCode == null || param.getJsonObj() == null || param.getAuthCode() == null) {
            throw new RuntimeException("exception.access.param-invalidate");
        }
        AccessSystemInfo sysInfo = accessSysMap.get(accessCode);
        if (sysInfo == null) {
            throw new RuntimeException("exception.access.system-null");
        }
        if (sysInfo.isFrozen()) {
            throw new RuntimeException("exception.access.system-frozen");
        }
        // 对json参数+私钥的字符串进行MD5加密后再进行Base64加密生成校验码
        String authCode = generateAuthCode(param.getJsonObj(), sysInfo.getPrivateKey());
        if (!authCode.equals(param.getAuthCode())) {
            throw new RuntimeException("exception.access.authcode-wrong");
        }
        return true;
    }
    
    @Override
    public String generateAuthCode(String jsonObj, String privateKey) {
        return Coder.encryptBASE64(Coder.encryptMD5(jsonObj + privateKey));
    }
}
