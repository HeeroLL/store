package com.zebone.empi.service;

import com.zebone.empi.check.ValidateResult;

/**
 * 值域校验接口
 *
 * @author 杨英
 * @version 2014-4-17 上午10:52
 */
public interface ValueCheckService {

    /**
     * 字典值域校验
     * @param typeCode
     * @param checkValue
     * @return
     */
    public ValidateResult checkDictionary(String typeCode, String checkValue);

    /**
     * 正则校验
     * @param typeCode
     * @param checkValue
     * @return
     */
    public ValidateResult checkRegular(String typeCode, String checkValue);
}
