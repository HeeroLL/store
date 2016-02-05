package com.isell.ws;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;

import com.isell.core.util.HttpUtils;

public class WebServiceClient {
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(HttpUtils.class);
    
    /**
     * 发送webservice消息
     * 
     * @param endpoint 发送地址
     * @param methodName 调用服务平台暴露的方法名
     * @param message 发送消息
     * @param namespace 命名空间
     * @param parametersName 参数名
     * @return
     */
    public static String invokeRemoteFuc(String endpoint, String methodName, String message, String namespace,
        String parametersName) {
        // message为传入的三单报文xml格式的字符串
        // 远程调用路径
        String result = "call failed!";
        Service service = new Service();
        Call call;
        
        try {
            call = (Call)service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(methodName);// 调用的方法名
            
            call.addParameter(new QName(namespace, parametersName), XMLType.XSD_STRING, // 参数类型:String
                ParameterMode.IN);// 参数模式：'IN' or 'OUT'
            // 设置返回值类型
            call.setReturnType(XMLType.XSD_STRING); // 返回值类型：String
            
            StringBuilder builder = new StringBuilder();
            // 记录发送日志
            builder.append("parameter: ")
                .append(System.getProperty("line.separator", "\n"))
                .append(message)
                .append(System.getProperty("line.separator", "\n"));
            
            result = (String)call.invoke(new Object[]{message});// 远程调用
            
            // 记录响应日志
            builder.append("response:  ").append(result);
            log.info(builder.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    
}
