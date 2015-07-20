package com.sell.ei.logistics.ecm.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sell.core.base.BaseInfo;

/**
 * 商品信息接口外层参数<br/>
 * SCM接口:获取商品信息接口（sendCommodity）
 * 
 * @author lilin
 * @version [版本号, 2015年7月20日]
 */
public class Commodities extends BaseInfo {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5371093334685699999L;
    
    /** 商品集合 */
    @JsonProperty("Commoditys")
    private List<Commodity> commoditys;

    public List<Commodity> getCommoditys() {
        return commoditys;
    }

    public void setCommoditys(List<Commodity> commoditys) {
        this.commoditys = commoditys;
    }
}
