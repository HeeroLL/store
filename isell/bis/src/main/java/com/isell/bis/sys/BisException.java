package com.isell.bis.sys;

/**
 * Bis异常类
 * 
 * @author lilin
 * @version [版本号, 2015年11月10日]
 */
public class BisException extends RuntimeException {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2740501899177641069L;
    
    /**
     * code
     */
    private String code;
    
    public BisException() {
        super();
    }
    
    public BisException(String code) {
        super(code);
        this.code = code;
    }
    
    public BisException(String code, String message) {
        super(message);
        this.code = code;
    }
    
    public BisException(Throwable t) {
        super(t);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
