package com.zebone.register.validation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.zebone.register.validation.dao.DataCenterMapper;
import com.zebone.register.validation.domain.DocMapping;
import com.zebone.register.validation.domain.FieldTableColumn;
import com.zebone.register.validation.enu.ErrorType;
import com.zebone.register.validation.enu.MetaDataError;
import com.zebone.register.validation.enu.OnlyError;
import com.zebone.register.validation.repository.MetaDataRepository;
import com.zebone.register.validation.service.OnlyValidationService;
import com.zebone.register.validation.vo.ErrorMsg;
import com.zebone.register.validation.vo.ErrorMsgDetail;
import com.zebone.register.validation.vo.ValidationResult;
import com.zebone.util.DateUtil;


/**
 * 唯一性校验service实现类
 * @author 陈阵 
 * @date 2013-8-20 上午8:18:39
 */
@Service("onlyValidationService")
public class OnlyValidationServiceImpl implements OnlyValidationService {

	private static final Log logger =  LogFactory.getLog(OnlyValidationServiceImpl.class);
    
	@Resource
	private MetaDataRepository metaDataRepository ;
	
	@Resource
	private DataCenterMapper dataCenterMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public ValidationResult validation(String docId, Document xmlDataDocument, String docOrgCode) {
		// TODO Auto-generated method stub
		ValidationResult validationResult = new ValidationResult();
		String startTime = DateUtil.getCurrentDate();

		List<Element> valueElementList = xmlDataDocument.selectNodes("//value");
		if(valueElementList != null && valueElementList.size() > 0){
			for (Element valueElement : valueElementList) {
				/** value元素的值 **/
				String valueCodeAttrValue = valueElement.attributeValue("code");
				/** 你元素对应元数据的标识符号 **/
				Element parentElement = valueElement.getParent();
				/** 结点的code属性 **/
				String parentElementCodeAttrValue = parentElement.attributeValue("code");
				/** 父亲结点的name值 用于标识xpath **/
				String parentElementNameValue = parentElement.attributeValue("name");
				
				String xpath = parentElement.getPath() + "[@code='"+parentElementCodeAttrValue+"'][@name='"+parentElementNameValue+"']";
				DocMapping docMapping = metaDataRepository.getDocMappingByXpathAndDocId(xpath, docId);
	            
				if(docMapping != null){
					  if("1".equals(docMapping.getIsOnly())){
							  String cloumnId = docMapping.getCloumnId();
							  FieldTableColumn fieldTableColumn =  metaDataRepository.getFieldTableColumn(cloumnId);
							  if(fieldTableColumn != null){
								  int checkResult = dataCenterMapper.getOnlyCheck(fieldTableColumn.getTableName(), fieldTableColumn.getColumnName(), valueCodeAttrValue, docOrgCode);
								  if(checkResult > 0){
								    	String errorMsg = "(唯一性校验[表名："+fieldTableColumn.getTableName()+",列名："+ fieldTableColumn.getColumnName()+",校验值："+valueCodeAttrValue+"])";
										validationResult.setSucess(false);			
										ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
										errorMsgDetail.setStartTime(DateUtil.getCurrentDate());
										errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
										errorMsgDetail.setErrorType(ErrorType.ONLY.getType());
										errorMsgDetail.setErrorSubType(OnlyError.DATA_HAS_EXIT.getErrorCode());
										errorMsgDetail.setErrorDesc(OnlyError.DATA_HAS_EXIT.getErrorMsg() + errorMsg);
										errorMsgDetail.setDocXpath(xpath);		
										validationResult.getErrorMsgDetial().add(errorMsgDetail);
								  }
							  }else{
							    	String errorMsg = "(唯一性校验[P_FIELD_TABLE_CONF ID_："+ cloumnId +"])";
								  	validationResult.setSucess(false);			
									ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
									errorMsgDetail.setStartTime(DateUtil.getCurrentDate());
									errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
									errorMsgDetail.setErrorType(ErrorType.ONLY.getType());
									errorMsgDetail.setErrorSubType(MetaDataError.FIELD_TABLE_COLUMN_NOT_FIND.getErrorCode());
									errorMsgDetail.setErrorDesc(MetaDataError.FIELD_TABLE_COLUMN_NOT_FIND.getErrorMsg() + errorMsg);
									errorMsgDetail.setDocXpath(xpath);		
									validationResult.getErrorMsgDetial().add(errorMsgDetail);
							  }							
					  }	  
				}else{
						String errorMsg = "(唯一性校验[xpath:"+ xpath +"])";
						validationResult.setSucess(false);			
						ErrorMsgDetail errorMsgDetail = new ErrorMsgDetail();
						errorMsgDetail.setStartTime(startTime);
						errorMsgDetail.setEndTime(DateUtil.getCurrentDate());
						errorMsgDetail.setErrorType(ErrorType.METADATA.getType());
						errorMsgDetail.setErrorSubType(MetaDataError.DOC_MAPPING_NOT_FIND.getErrorCode());
						errorMsgDetail.setErrorDesc(MetaDataError.DOC_MAPPING_NOT_FIND.getErrorMsg() + errorMsg);
						errorMsgDetail.setDocXpath(xpath);
						validationResult.getErrorMsgDetial().add(errorMsgDetail);
				}	
			}
		}
		
		if(!validationResult.isSucess()){
			ErrorMsg errorMsg = validationResult.getErrorMsg();
			errorMsg.setStartTime(startTime);
			errorMsg.setEndTime(DateUtil.getCurrentDate());
		}
		
		return validationResult;
	}

}
