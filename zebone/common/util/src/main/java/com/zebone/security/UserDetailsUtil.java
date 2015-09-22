package com.zebone.security;

import org.jasig.cas.client.util.AssertionHolder;

import com.zebone.util.JsonUtil;

public class UserDetailsUtil {
	/**
	 * 获取用户信息
	 * @return
	 */
	public static UserDetails getUserDetails() {
		try {
			String json = (String) AssertionHolder.getAssertion().getPrincipal().getAttributes().get("userDetails");
			return JsonUtil.readValue(json, UserDetails.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
