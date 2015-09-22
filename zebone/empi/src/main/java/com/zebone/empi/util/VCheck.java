package com.zebone.empi.util;

import com.zebone.empi.check.CheckConfig;
import com.zebone.empi.check.CheckConfig.Check;
import com.zebone.empi.check.CheckConfig.Rule;
import com.zebone.empi.check.ValidateResult;
import com.zebone.empi.service.ValueCheckService;
import com.zebone.empi.vo.EmpiRegisterInfo;
import org.apache.commons.beanutils.PropertyUtils;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 值域校验
 *
 * @author 杨英
 * @version 2014-4-16 下午02:25
 */
public class VCheck {

    public String check(CheckConfig checkConfig, Object checkObj, ValueCheckService valueCheckService) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, SQLException {
        StringBuilder result = new StringBuilder();
        List<Check> checkList = checkConfig.getCheckList();
        String path = checkConfig.getPath();
        if (path != null && !"".equals(path)) {
            path += ".";
        } else {
            path = "";
        }
        if (checkList != null && checkList.size() > 0) {
            for (Check check : checkList) {
                String field = path + check.getField();
                String fieldName = check.getFieldName();
                Object[] values = getValue(checkObj, field);
                if (values != null && values.length > 0) {
                    for (Object objVal : values) {
                        boolean bool = false;
                        if (!(objVal instanceof Short)) {
                            String temp = (String) objVal;
                            //字符串值域校验，如果为null值，不进行校验
                            if (temp != null && !"".equals(temp)) {
                                bool = true;
                            }
                        } else {
                            Short ss = (Short) objVal;
                            objVal = Short.toString(ss);
                            bool = true;
                        }
                        if (bool) {
                            for (Rule rule : check.getRuleList()) {
                                ValidateResult validateResult = null;
                                String type = rule.getType();
                                String[] types = type.split(":");
                                if ("dic".equals(types[0])) {
                                    validateResult = valueCheckService.checkDictionary(types[1], ((String)objVal).toUpperCase());
                                }else if("regular".equals(types[0])){
                                    validateResult = valueCheckService.checkRegular(types[1],(String)objVal);
                                }else if ("masterData".equals(types[0])) {
                                }
                                if (!validateResult.isSuccess()) {
                                    result.append(fieldName).append(" ").append(validateResult.getErrorMessage()).append("\r\n");
                                }
                            }
                        }
                    }
                }
            }
        }
        return result.toString();
    }

    public Object[] getValue(Object obj, String value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String[] propertys = value.split("\\.", 2);
        if (propertys.length == 1) {
            return new Object[]{PropertyUtils.getProperty(obj, propertys[0])};
        } else {
            PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(obj, propertys[0]);
            Class<? extends Object> clz = pd.getPropertyType();
            if (clz.isAssignableFrom(List.class)) {
                List<Object> resultObj = new ArrayList<Object>();
                List<Object> objList = (List<Object>) PropertyUtils.getProperty(obj, propertys[0]);
                for (Object lObj : objList) {
                    Object[] returnObj = getValue(lObj, propertys[1]);
                    for (Object tempObj : returnObj) {
                        resultObj.add(tempObj);
                    }
                }
                return resultObj.toArray();
            } else {
                Object nObj = PropertyUtils.getProperty(obj, propertys[0]);
                return getValue(nObj, propertys[1]);
            }
        }
    }
}
