package com.zebone.pubsub.server.service;

public interface PubSubService<T> {
	  
	 void doPub(T para);
}
