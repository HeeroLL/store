package com.isell.bis.sys;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

/**
 * 为了记录日志，扩展HttpRequest，使得可以从流中多次获取请求体
 * 
 * @author lilin
 * @version [版本号, 2015年7月22日]
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    /** 请求体 */
    private final byte[] body;
    
    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)
        throws IOException {
        super(request);
        body = StreamUtils.copyToByteArray(request.getInputStream());
    }
    
    @Override
    public BufferedReader getReader()
        throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    
    @Override
    public ServletInputStream getInputStream()
        throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            
            @Override
            public int read()
                throws IOException {
                return bais.read();
            }
        };
    }
}
