package com.zebone.dnode.etl.check;

/**
 * 数据校验接口
 *
 * @author 杨英
 * @version 2014-02-17 上午09:46
 */
public interface DataChecking {

    boolean check(String checkReg, Object value);
}
