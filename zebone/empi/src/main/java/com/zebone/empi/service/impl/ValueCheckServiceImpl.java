package com.zebone.empi.service.impl;

import com.zebone.empi.check.ErrorInfo;
import com.zebone.empi.check.ErrorType;
import com.zebone.empi.check.ValidateResult;
import com.zebone.empi.dao.DictionaryMapper;
import com.zebone.empi.service.ValueCheckService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 值域校验实现类
 *
 * @author 杨英
 * @version 2014-4-17 上午10:53
 */
@Service
public class ValueCheckServiceImpl implements ValueCheckService {
    private static final Log log = LogFactory.getLog(ValueCheckServiceImpl.class);

    private static final String dicMessage = "字典编码不存在";

    private static final String regMessage = "值不符合校验规则";

    @Resource
    private DictionaryMapper dictionaryMapper;


    public ValidateResult checkDictionary(String typeCode, String checkValue) {
        int resultCount = 0;
        ValidateResult validateResult = new ValidateResult(true);
        Map<String, String> parMap = new HashMap<String, String>();
        parMap.put("typeCode", typeCode);
        parMap.put("checkValue", checkValue);
        try {
            resultCount = dictionaryMapper.countDictInfo(parMap);
        } catch (Exception e) {
            validateResult.setSuccess(false);
            validateResult.setErrorCode(ErrorType.SYSTEM.getErrorCode());
            validateResult.setErrorMessage(ErrorType.SYSTEM.getErrorDesc());
            log.error(e.getMessage(), e);
            return validateResult;
        } finally {
            if (resultCount == 0) {
                validateResult.setSuccess(false);
                validateResult.setErrorCode(ErrorType.VALUE_CHECK.getErrorCode() + "-" + ErrorInfo.VALUE_NO.getErrorCode());
                validateResult.setErrorMessage(ErrorType.VALUE_CHECK.getErrorDesc() + "--" + ErrorInfo.VALUE_NO.getErrorDesc() + "--" + dicMessage);
            }
        }
        return validateResult;

    }

    @Override
    public ValidateResult checkRegular(String typeCode, String checkValue) {
        ValidateResult validateResult = new ValidateResult(true);
        if (!checkValue.matches(typeCode)) {
            validateResult.setSuccess(false);
            validateResult.setErrorCode(ErrorType.VALUE_CHECK.getErrorCode());
            validateResult.setErrorMessage(ErrorType.VALUE_CHECK.getErrorDesc() + "--" + regMessage);
        }
        return validateResult;
    }
}
