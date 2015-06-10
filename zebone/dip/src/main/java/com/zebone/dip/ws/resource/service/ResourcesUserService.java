package com.zebone.dip.ws.resource.service;

import com.zebone.dip.ws.resource.pojo.UserRequest;
import com.zebone.dip.ws.resource.pojo.UserResponse;

public interface ResourcesUserService {

	UserResponse saveUser(UserRequest userRequest);
	
	UserResponse delUser(UserRequest userRequest);
	
	UserResponse searchUser(UserRequest userRequest);
	
	UserResponse updateUser(UserRequest userRequest);
}
