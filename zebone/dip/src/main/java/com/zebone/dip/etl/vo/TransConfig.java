package com.zebone.dip.etl.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("transformation_configuration")
public class TransConfig  implements Serializable{


	@XStreamAlias("trans_reference")
	private Reference transRef;

	@XStreamAlias("transformation_execution_configuration")
	private TransExecConfig transExecConfig;

	public Reference getTransRef() {
		return this.transRef;
	}

	public void setTransRef(Reference transRef) {
		this.transRef = transRef;
	}

	public TransExecConfig getTransExecConfig() {
		return this.transExecConfig;
	}

	public void setTransExecConfig(TransExecConfig transExecConfig) {
		this.transExecConfig = transExecConfig;
	}
}