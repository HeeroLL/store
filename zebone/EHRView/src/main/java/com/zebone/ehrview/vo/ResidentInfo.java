package com.zebone.ehrview.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户EMPI基本信息
 * @author YinCM
 *
 */
public class ResidentInfo {
	private List<ResidentCard> cardList;
	private Map<String, String> infoMap;

    //用于文档调阅日志的记录
    private String searchInfo = "";
    private String patientInfo = "";

	public ResidentInfo(){
		cardList = new ArrayList<ResidentCard>();
		infoMap = new HashMap<String,String>();
	}

	public List<ResidentCard> getCardList() {
		return cardList;
	}
	public void setCardList(List<ResidentCard> cardList) {
		this.cardList = cardList;
	}
	public Map<String, String> getInfoMap() {
		return infoMap;
	}
	public void setInfoMap(Map<String, String> infoMap) {
		this.infoMap = infoMap;
	}

    public String getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(String searchInfo) {
        this.searchInfo = searchInfo;
    }

    public String getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(String patientInfo) {
        this.patientInfo = patientInfo;
    }
}
