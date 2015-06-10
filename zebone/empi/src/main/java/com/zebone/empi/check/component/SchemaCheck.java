package com.zebone.empi.check.component;

import com.zebone.empi.check.ErrorType;
import com.zebone.empi.check.ValidateResult;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * XSD校验
 *
 * @author 杨英
 * @version 2014-4-15 下午03:05
 */
public class SchemaCheck {
    /**
     * @param xml   需要校验的xml
     * @param xsdIs xml对应的xsd文件流
     * @return
     * @throws org.dom4j.DocumentException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws java.io.UnsupportedEncodingException
     *
     */
    public ValidateResult check(String xml, InputStream xsdIs)
            throws DocumentException, SAXException,
            ParserConfigurationException, UnsupportedEncodingException {
        ValidateResult checkResult = new ValidateResult(true);
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
        reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        String result = rs.toString();
        if (StringUtils.isNotEmpty(result)) {
            checkResult.setSuccess(false);
            checkResult.setErrorCode(ErrorType.XML_SCHEMA.getErrorCode());
            checkResult.setErrorMessage(ErrorType.XML_SCHEMA.getErrorDesc() + "--" + result);
        }
        return checkResult;

    }
}
