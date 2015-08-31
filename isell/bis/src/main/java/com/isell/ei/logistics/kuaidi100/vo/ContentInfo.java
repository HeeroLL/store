package com.isell.ei.logistics.kuaidi100.vo;

import com.isell.core.base.BaseInfo;

/**
 * 单条快递信息
 * 
 * @author lilin
 * @version [版本号, 2015年8月9日]
 */
public class ContentInfo extends BaseInfo  {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 926171742042419855L;
    
    /**
     * 处理时间
     */
    private String time;
    
    /**
     * 描述文字
     */
    private String context;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
