package com.zebone.dnode.etl.check.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 数据检查配置对象
 *
 * @author 杨英
 * @version 2014-02-14 上午08:45
 */

@XStreamAlias("verifyConf")
public class CheckConfig {

    @XStreamAlias("verify")
    private VerifyParameter  verifyParameter;

    public VerifyParameter getVerifyParameter() {
        return verifyParameter;
    }

    public void setVerifyParameter(VerifyParameter verifyParameter) {
        this.verifyParameter = verifyParameter;
    }
}
