package com.zebone.register.vo;
/**
 * 解析主索引信息和注册信息
 */
public class ParamInfo {
	private EmpiInfo empiInfo;
	private RegisterMain registerMain;
    private RegisterTemp registerTemp;
	public EmpiInfo getEmpiInfo() {
		return empiInfo;
	}
	public void setEmpiInfo(EmpiInfo empiInfo) {
		this.empiInfo = empiInfo;
	}
	public RegisterMain getRegisterMain() {
		return registerMain;
	}
	public void setRegisterMain(RegisterMain registerMain) {
		this.registerMain = registerMain;
	}

    public RegisterTemp getRegisterTemp() {
        return registerTemp;
    }

    public void setRegisterTemp(RegisterTemp registerTemp) {
        this.registerTemp = registerTemp;
    }
}
