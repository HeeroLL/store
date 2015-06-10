package com.zebone.dnode.etl.check;

/**
 * 正则表达式验证类
 *
 * @author 杨英
 * @version 2014-02-17 上午10:01
 */
public class RegexCheck implements DataChecking{
    @Override
    public boolean check(String checkReg, Object value) {
        String val = value.toString();
        if(val.matches(checkReg)){
            return true;
        }
        return false;
    }
}
