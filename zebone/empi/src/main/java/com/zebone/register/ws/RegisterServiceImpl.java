package com.zebone.register.ws;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.stereotype.Service;

import com.zebone.register.analyze.DocumentAnalyze;
import com.zebone.register.validation.DocumentValidation;

@WebService(endpointInterface = "com.zebone.register.ws.RegisterService")
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

	@Resource
	private DocumentValidation documentValidation;
	@Resource
	private DocumentAnalyze documentAnalyze;
	@Resource
	private WebServiceContext context;
	
	@Override
	public String requestRegister(String docXml) {
		 MessageContext ctx = context.getMessageContext();
		 HttpServletRequest request = (HttpServletRequest) ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
		 String ip = request.getRemoteAddr();
		 String result = null;
		 result = documentValidation.execute(docXml,ip);
		 if(result==null){
			 result = documentAnalyze.analyze(docXml,ip);
		 }
		return result;
	}

}
