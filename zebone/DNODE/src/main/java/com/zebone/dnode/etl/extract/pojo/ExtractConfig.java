package com.zebone.dnode.etl.extract.pojo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("nodeConfig")
public class ExtractConfig {
	

	@XStreamAlias("name")
   	@XStreamAsAttribute
	private String nodeName;

	@XStreamAlias("policy")
   	@XStreamAsAttribute
	private String nodePolicy;
	
	@XStreamImplicit(itemFieldName="dataSource")
	private List<JdbcParameter> JdbcParameter;
	
	@XStreamAlias("extract")
	private ExtractParameter  extractParameter;

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodePolicy() {
		return nodePolicy;
	}

	public void setNodePolicy(String nodePolicy) {
		this.nodePolicy = nodePolicy;
	}

	public ExtractParameter getExtractParameter() {
		return extractParameter;
	}

	public void setExtractParameter(ExtractParameter extractParameter) {
		this.extractParameter = extractParameter;
	}

	public List<JdbcParameter> getJdbcParameter() {
		return JdbcParameter;
	}

	public void setJdbcParameter(List<JdbcParameter> jdbcParameter) {
		JdbcParameter = jdbcParameter;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExtractConfig [nodeName=");
		builder.append(nodeName);
		builder.append(", nodePolicy=");
		builder.append(nodePolicy);
		builder.append(", JdbcParameter=");
		builder.append(JdbcParameter);
		builder.append(", extractParameter=");
		builder.append(extractParameter);
		builder.append("]");
		return builder.toString();
	}
	
	

}
