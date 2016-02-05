package com.isell.demo.gztg.service;

import com.isell.demo.gztg.bean.Manifest;
import com.isell.demo.gztg.bean.ResponseMessage;

public interface GztgService {

	ResponseMessage getResponse(String messageId);

	void getGztg(Manifest manifest);

}
