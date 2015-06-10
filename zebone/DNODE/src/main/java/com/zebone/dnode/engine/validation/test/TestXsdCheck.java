package com.zebone.dnode.engine.validation.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.ws.commons.schema.XmlSchema;
import org.apache.ws.commons.schema.XmlSchemaCollection;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.zebone.dnode.engine.validation.component.SchemaValidation;
import com.zebone.dnode.engine.validation.dto.XsdValidationPara;

public class TestXsdCheck {

	/**
	 * @param args
	 * @author 陈阵 
	 * @date 2013-12-26 上午11:15:06 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		try {
			SchemaValidation schemaValidation = new SchemaValidation();
			XsdValidationPara xsdValidationPara = new XsdValidationPara();
			xsdValidationPara.setCheckRepeat("1");
			xsdValidationPara.setCheckSelect("1");
			SAXReader saxReader = new SAXReader();		
			XmlSchemaCollection schemaCol = new XmlSchemaCollection();
			XmlSchema xmlSchema = schemaCol.read(new InputSource(new FileInputStream(new File("C:\\Users\\cz\\Desktop\\abc.xml"))));
			Document doc = saxReader.read(new File("C:\\Users\\cz\\Desktop\\门诊处方.xml"));
			xsdValidationPara.setDataDocument(doc);
			xsdValidationPara.setXmlSchema(xmlSchema);
			
			schemaValidation.validation(xsdValidationPara);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
