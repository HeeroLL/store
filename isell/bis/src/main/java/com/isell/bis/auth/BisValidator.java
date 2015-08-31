package com.isell.bis.auth;

import com.isell.bis.auth.bean.RequestParameter;

/**
 * BIS认证接口
 * 
 * @author lilin
 * @version [版本号, 2015年7月22日]
 */
public interface BisValidator {
    /**
     * 验证参数
     *
     * @param param 参数
     * @return 验证结果
     */
    boolean validate(RequestParameter param);
    
    /**
     * 生成校验码
     *
     * @param jsonObj json参数
     * @param privateKey 客户系统密钥
     * @return 校验码
     */
    String generateAuthCode(String jsonObj, String privateKey);
}
