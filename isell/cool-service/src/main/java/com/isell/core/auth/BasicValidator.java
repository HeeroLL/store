package com.isell.core.auth;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.isell.core.auth.bean.RequestParameter;
import com.isell.core.config.BisConfig;
import com.isell.core.config.vo.AccessSystem;
import com.isell.core.util.Coder;

/**
 * 基础验证类
 * 
 * @author lilin
 * @version [版本号, 2015年7月22日]
 */
@Service("basicValidator")
public class BasicValidator implements BisValidator {
    /**
     * 系统配置信息
     */
    @Resource
    private BisConfig config;
    
    @Override
    public boolean validate(RequestParameter param) {
        String accessCode = param.getAccessCode();
        if (accessCode == null || param.getJsonObj() == null || param.getAuthCode() == null) {
            throw new RuntimeException("exception.access.param-invalidate");
        }
        AccessSystem sysInfo = config.getAccessSysMap().get(accessCode);
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
