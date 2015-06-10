package com.zebone.empi.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.zebone.empi.vo.EmpiLog;
import com.zebone.empi.vo.ResidentCard;
import com.zebone.empi.vo.ResidentInfo;

/**
 * 验证卡
 * @author YinCm
 * @version 2013-7-31 下午10:10:20
 */
public class CheckCard {
	static Log logger = LogFactory.getLog(CheckCard.class);
	
	/**
	 * 验证身份证
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public static boolean CheckIDCard(ResidentInfo residentInfo, EmpiLog empiLog){
		String strIdValide = "";
		for(ResidentCard rc : residentInfo.getCardList()){
			if(rc.getCardType()!=null && rc.getCardType().trim().equals("1")){
				try{
					strIdValide = IDCardValidate.IDCardValidate(rc.getCardNo());
				}catch(Exception e){
					return false;
				}
				
				if(!strIdValide.equals("") && empiLog!=null){
					
					String str = empiLog.getErrorPosition()==null?"IDCard":(empiLog.getErrorPosition().isEmpty()?"IDCard":(empiLog.getErrorPosition()+",IDCard"));
					empiLog.setErrorPosition(str);
					empiLog.setCardNo(rc.getCardNo());
					empiLog.setCardType("1");
				}
			}
		}
		if(strIdValide.equals("")){
			return true;
		}else{
			return false;
		}
	}
}
