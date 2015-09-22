package com.zebone.check.component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.zebone.check.CheckResult;
import com.zebone.log.ErrorType;
import com.zebone.log.FileStructureError;
import com.zebone.log.RangeError;
import com.zebone.log.SchemaError;

/**
 * xsd校验
 * 
 * @author 陈阵
 * @date 2014-4-8 下午3:36:43
 */
public class SchemaCheck {

	/**
	 * 
	 * @param xml
	 *            需要校验的xml
	 * @param xsdIs
	 *            xml对应的xsd文件流
	 * @return
	 * @throws DocumentException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws UnsupportedEncodingException
	 * @author 陈阵
	 * @date 2014-4-8 下午3:38:21
	 */
	public CheckResult check(String xml, InputStream xsdIs)
			throws DocumentException, SAXException,
			ParserConfigurationException, UnsupportedEncodingException {
		CheckResult checkResult = new CheckResult(true);
		final StringBuilder rs = new StringBuilder();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance("http://www.w3.org/2001/XMLSchema");
		factory.setSchema(schemaFactory.newSchema(new StreamSource(xsdIs)));

		SAXParser parser = factory.newSAXParser();

		SAXReader reader = new SAXReader(parser.getXMLReader());
		reader.setValidation(false);
		reader.setErrorHandler(new ErrorHandler() {

			@Override
			public void warning(SAXParseException exception)
					throws SAXException {
				// TODO Auto-generated method stub
			}

			@Override
			public void fatalError(SAXParseException exception)
					throws SAXException {
				// TODO Auto-generated method stub
				rs.append("\r\n").append(exception.getMessage());
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				// TODO Auto-generated method stub
				rs.append("\r\n").append(exception.getMessage());
			}
		});
		reader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));
		String result = rs.toString();
		if (StringUtils.isNotEmpty(result)) {
			checkResult.setSuccess(false);
			String code = "";
			String message = "";
			String[] strs = result.split("\r\n");
			for(String str : strs){
				String codeTemp = "";
				String messageTemp = "";
				if(str.contains(SchemaError.NODE_NOT_EXISTS.getErrorCode())){
					codeTemp = ErrorType.FILE_STRUCTURE.getErrorCode()+"-"+FileStructureError.NODE_NOT_EXISTS.getErrorCode();
					messageTemp = ErrorType.FILE_STRUCTURE.getErrorDesc()+"--"
							+FileStructureError.NODE_NOT_EXISTS.getErrorDesc()+"--"+
							str;
				}
				
				if(str.contains(SchemaError.NODE_VALUE_FORMAT.getErrorCode())){
					codeTemp = ErrorType.VALUE_CHECK.getErrorCode()+"-"+RangeError.NODE_VALUE_FORMAT.getErrorCode();
					messageTemp = ErrorType.VALUE_CHECK.getErrorDesc()+"--"
							+RangeError.NODE_VALUE_FORMAT.getErrorDesc()+"--"+
							str;
				}
				if(str.contains(SchemaError.NODE_VALUE_LENGTH.getErrorCode())
					||str.contains(SchemaError.NODE_VALUE_MIN_LENGTH.getErrorCode())
					||str.contains(SchemaError.NODE_VALUE_MAX_LENGTH.getErrorCode())
					||str.contains(SchemaError.NODE_VALUE_MIN_INT.getErrorCode())
					||str.contains(SchemaError.NODE_VALUE_MAX_INT.getErrorCode())
				){
					codeTemp = ErrorType.VALUE_CHECK.getErrorCode()+"-"+RangeError.NODE_VALUE_LENGTH.getErrorCode();
					messageTemp = ErrorType.VALUE_CHECK.getErrorDesc()+"--"
							+RangeError.NODE_VALUE_LENGTH.getErrorDesc()+"--"+
							str;
				}
				
				if(codeTemp!=""){
					code += codeTemp+",";
					message += messageTemp+"\r\n";
				}else{
					message += str+"\r\n";
				}
			}
			checkResult.setErrorCode(code);
			checkResult.setErrorMessage(message);
		}
		return checkResult;

	}

}
