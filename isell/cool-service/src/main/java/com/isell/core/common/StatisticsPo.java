package com.isell.core.common;

import java.math.BigDecimal;

/**
 * 统计指标
 * 
 * @author wangpeng
 *
 */
public class StatisticsPo {

	private String statistics_k;
	
	private String statistics_v_s;
	
	private BigDecimal statistics_v_b;
	
	private Integer statistics_v_i;

	public String getStatistics_k() {
		return statistics_k;
	}

	public void setStatistics_k(String statistics_k) {
		this.statistics_k = statistics_k;
	}

	public String getStatistics_v_s() {
		return statistics_v_s;
	}

	public void setStatistics_v_s(String statistics_v_s) {
		this.statistics_v_s = statistics_v_s;
	}

	public BigDecimal getStatistics_v_b() {
		return statistics_v_b;
	}

	public void setStatistics_v_b(BigDecimal statistics_v_b) {
		this.statistics_v_b = statistics_v_b;
	}

	public Integer getStatistics_v_i() {
		return statistics_v_i;
	}

	public void setStatistics_v_i(Integer statistics_v_i) {
		this.statistics_v_i = statistics_v_i;
	}
	
}
