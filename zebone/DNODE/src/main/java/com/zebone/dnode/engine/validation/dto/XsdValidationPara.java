package com.zebone.dnode.engine.validation.dto;

import org.apache.ws.commons.schema.XmlSchema;
import org.dom4j.Document;


/**
 * xsd校验参数
 * @author 陈阵 
 * @date 2013-8-8 上午9:45:32
 */
public class XsdValidationPara {
    
	private Document dataDocument;
    
	private XmlSchema xmlSchema;
	
	private String checkRepeat;
	
	private String checkSelect;
	

	public String getCheckRepeat() {
		return checkRepeat;
	}

	public void setCheckRepeat(String checkRepeat) {
		this.checkRepeat = checkRepeat;
	}

	public String getCheckSelect() {
		return checkSelect;
	}

	public void setCheckSelect(String checkSelect) {
		this.checkSelect = checkSelect;
	}

	public XmlSchema getXmlSchema() {
		return xmlSchema;
	}

	public void setXmlSchema(XmlSchema xmlSchema) {
		this.xmlSchema = xmlSchema;
	}

	public Document getDataDocument() {
		return dataDocument;
	}

	public void setDataDocument(Document dataDocument) {
		this.dataDocument = dataDocument;
	}
	
}
