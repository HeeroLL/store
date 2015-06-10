package com.zebone.dip.tool.docoperation;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.zebone.dip.tool.jdbc.DatabaseSearch;
import com.zebone.dip.tool.vo.DictionaryField;
import com.zebone.dip.tool.vo.DictionaryTable;
import com.zebone.dip.tool.vo.DictionaryType;
import com.zebone.dip.tool.vo.DocFieldInfo;
import com.zebone.dip.tool.vo.DocInfo;



public class GenerateDocument {
	/**
	 * 替换word中的文字
	 * 
	 * @param srcPath
	 * @param destPath
	 * @param map
	 * @return
	 */
	public static boolean replaceAndGenerateWord(String srcPath,
			String destPath, Map<String, String> map) {
		try {
			XWPFDocument document = new XWPFDocument(
					POIXMLDocument.openPackage(srcPath));
			// 替换段落中的指定文字
			Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
			int ij = 0;
			while (itPara.hasNext()) {
				XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
				List<XWPFRun> runs = paragraph.getRuns();

				for (int i = 0; i < runs.size(); i++) {

					String oneparaString = runs.get(i).getText(
							runs.get(i).getTextPosition());
					if (oneparaString == null)
						continue;
					for (Map.Entry<String, String> entry : map.entrySet()) {
						oneparaString = oneparaString.replace(entry.getKey(),
								entry.getValue());
					}
					runs.get(i).setText(oneparaString, 0);
				}
			}

			// 替换表格中的指定文字
			Iterator<XWPFTable> itTable = document.getTablesIterator();
			int tableCount = 0;
			while (itTable.hasNext()) {
				tableCount++;
				XWPFTable table = (XWPFTable) itTable.next();
				int rcount = table.getNumberOfRows();
				for (int i = 0; i < rcount; i++) {
					XWPFTableRow row = table.getRow(i);
					List<XWPFTableCell> cells = row.getTableCells();
					for (XWPFTableCell cell : cells) {
						String cellTextString = cell.getText();
						for (Entry<String, String> e : map.entrySet()) {
							if (cellTextString.contains(e.getKey()))
								cellTextString = cellTextString.replace(
										e.getKey(), e.getValue());
						}
						cell.removeParagraph(0);
						cell.setText(cellTextString);
					}
				}
			}
			FileOutputStream outStream = null;
			outStream = new FileOutputStream(destPath);
			document.write(outStream);
			outStream.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 替换word中的文字
	 * 
	 * @param srcPath
	 * @param destPath
	 * @param map
	 * @return
	 */
	public static boolean replaceWord(XWPFDocument document, Map<String, String> map) {
		try {
			// 替换段落中的指定文字
			Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
			while (itPara.hasNext()) {
				XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
				List<XWPFRun> runs = paragraph.getRuns();

				for (int i = 0; i < runs.size(); i++) {

					String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
					if (oneparaString == null)
						continue;
					for (Map.Entry<String, String> entry : map.entrySet()) {
						oneparaString = oneparaString.replace(entry.getKey(),entry.getValue());
					}
					runs.get(i).setText(oneparaString, 0);
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	/**
	 * 找到正确文档信息表位置，并进行表的添补完善
	 * @param srcPath
	 * @param destPath
	 * @param map
	 * @param fieldValueList
	 *            值域列表
	 * @return
	 */
	public static boolean fillDocInfoTable(XWPFDocument document, List<String> fieldValueList, DocInfo di) {
		try {
			
			// 替换表格中的指定文字
			Iterator<XWPFTable> itTable = document.getTablesIterator();
			int tableCount = 0;
			while (itTable.hasNext()) {
				tableCount++;
				XWPFTable table = (XWPFTable) itTable.next();
				int rcount = table.getNumberOfRows();
				if (tableCount == 7) {
					appendDocInfoTable(table, fieldValueList, di);
				}

			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	
	private static void traverse(List<Element> elements,List<DocFieldInfo> docInfoList,List<DocFieldInfo> ndocInfoList){
		if(elements != null && elements.size() > 0){
			for(Element el:elements){
			   String acode = el.attributeValue("code");
			   String aname = el.attributeValue("name");
			   String xpath = null;
			   if(aname != null && acode != null){
				   xpath = el.getPath()+"[@code='"+acode+"'][@name='"+aname+"']";
			   }else{
				   xpath = el.getPath();
			   }
			   matchingElements(docInfoList,xpath,ndocInfoList);
			   List<Element> subElements = el.elements();
			   traverse(subElements,docInfoList,ndocInfoList);
			}
		}
	}
	
	private static void matchingElements(List<DocFieldInfo> docInfoList,String xpath,List<DocFieldInfo> ndocInfoList){
		for(DocFieldInfo dfi : docInfoList){
			String txpath = dfi.getXpath();
			if(xpath.equals(txpath)){
				ndocInfoList.add(dfi);
				break;
			}
		}
	}

	/**
	 * 完善文档信息表
	 * 
	 * @param table
	 * @param fieldValueList
	 */
	private static void appendDocInfoTable(XWPFTable table,
			List<String> fieldValueList, DocInfo di) {

		try {
			String xml = di.getDocXML();
			SAXReader reader = new SAXReader();
			reader.setEncoding("gbk");
			org.dom4j.Document doc = reader.read(new ByteArrayInputStream(xml.getBytes()));
		    Element element = (Element)doc.selectSingleNode("/ClinicalDocument/structuredBody");		
			List<Element> elements = element.elements();
		
		    List<DocFieldInfo> docInfoList = DatabaseSearch.getDocFieldInfo(di.getId());
		    List<DocFieldInfo> nDocFieldInfo = new ArrayList<DocFieldInfo>();
		    traverse(elements,docInfoList,nDocFieldInfo);

			for (DocFieldInfo docInfo : nDocFieldInfo) {
				if (docInfo.getFieldValue() != null
						&& docInfo.getFieldValue().trim() != "") {
					fieldValueList.add(docInfo.getFieldValue().trim());
				}
				Integer i = 0;
				XWPFTableRow row = table.getRow(1);
				List<XWPFTableCell> list = row.getTableCells();
				// xpath中包含@name的位置
				int lstIndex = 0;
				for (XWPFTableCell cell : list) {
					cell.setColor("FFFFFF");
					i++;
					String str = "";
					switch (i) {
					case 1:
						String xpath = docInfo.getXpath();
						if (xpath == null || xpath.trim().equals("")) {
							str = "";
							break;
						}
						// str = docInfo.getFieldName();
						lstIndex = xpath.lastIndexOf("@name=");
						if (lstIndex == -1) {
							str = getParentNodeXpathName(di.getDocXML(),
									docInfo.getXpath());
							break;
						}
						String b = xpath.substring(lstIndex);// @name='检验报告备注']
						String s = b.substring(7);
						str = (String) (s.subSequence(0, s.length() - 2));
						break;
					case 2:
						str = docInfo.getFieldCode();
						break;
					case 3:
						str = docInfo.getFieldFormat();
						break;
					case 4:
						str = docInfo.getIsSelect();
						if (str == "1") {
							str = "R";
						} else {
							str = "O";
						}
						break;
					case 5:
						str = docInfo.getRepeat();
						if (StringUtils.isBlank(str)) {
							str = "0";
						} else if (str.contains("1")) {
							str = "1";
						} else if (str.contains("2")) {
							str = "0..*";
						} else if (str.contains("3")) {
							str = "1..*";
						} else {
							str = "0";
						}
						break;
					case 6:
						str = docInfo.getIsOnly();
						if (str == "1") {
							str = "是";
						} else {
							str = "否";
						}
						break;
					case 7:
						if (docInfo.getFieldValue() != null
								&& docInfo.getFieldValue().trim() != "")
							if (docInfo.getTypeCode() == null) {
								str = docInfo.getFieldValueName();
							} else {
								str = docInfo.getFieldValueName() + "/"
										+ docInfo.getTypeCode();
							}
						break;
					case 8:
						str = docInfo.getXpath();
						break;
					default:
						break;
					}
					XWPFRun xrun =cell.getParagraphs().get(0).getRuns().get(0);
					xrun.setText(str, 0);
				}
				if (lstIndex == -1) {
					for (XWPFTableCell cell : list) {
						cell.setColor("E4E6E1");
						
					}
				}
				table.addRow(row);
			}
			table.removeRow(1);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 通过xpathstr路径获取父节点在xmldoc中的name值
	 * @param xmldoc
	 * @param xpathstr
	 * @return
	 */
	public static String getParentNodeXpathName(String xmldoc, String xpathstr){
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        String parentName=null;
        try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse(new InputSource( new StringReader(xmldoc)));
			 // Create XPathFactory object
	        XPathFactory xpathFactory = XPathFactory.newInstance();
	        // Create XPath object
	        XPath xpath = xpathFactory.newXPath();
			XPathExpression expr = xpath.compile(xpathstr+"/@name");
			parentName = expr.evaluate(doc, XPathConstants.STRING).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

        
		return parentName;
	}
   
	/**
	 * 按照文档类型编号和文档版本生成文档
	 * @param docTypeCode 文档类型编号 C00.01.03.00
	 * @param docVersion 文档版本 1.0 || 1.1
	 */
	public static void getDocument(String docTypeCode, String docVersion){
		String filepathString = GenerateDocument.class.getResource("").getPath()+"src1.docx";
		String destpathString = getProperty("outputPath");
		//获取模板文档
		try {
			//获取所有的docInfo列表
			List<DocInfo> docInfoList = DatabaseSearch.getDocInfoList(docTypeCode, docVersion);
			for(DocInfo di : docInfoList){
				System.out.println("正在处理文档："+di.getDocName()+"--版本号:"+di.getDocVersion());
				//新建文档
				XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(filepathString));
			 
				// 存储字典值域代码（id）
				List<String> fieldValueList = new ArrayList<String>();
				// 填补表，在fieldValueList中获得
				fillDocInfoTable(document, fieldValueList, di);

				// 添加各个字典表
				addDictionaryTables(document, fieldValueList);
				
				//添加最后文档
				addXMLAndTitleDocInfo(document, di);
				FileOutputStream outStream = null;
				outStream = new FileOutputStream(destpathString+di.getDocName()+".docx");
				document.write(outStream);
				outStream.close();
			}
			DatabaseSearch.closeConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取配置文件中值
	 * @param str 
	 * @return
	 */
	public static String getProperty(String str){
		Properties prop = new Properties();
		InputStream is=null;
		try {
			is = GenerateDocument.class.getResource("config.property").openStream();
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(is!=null)
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(prop!=null && str!=null &&str.trim()!=""){
			return prop.getProperty(str);
		}else{
			return null;
		}
		
	}
	
	public static void main(String[] args){
		//getDocument("C00.01.03.00", "1.0");
		String filepathString = GenerateDocument.class.getResource("").getPath()+"src1.docx";
        String destpathString = getProperty("outputPath");
        destpathString = "c:\\doc\\";
		//获取模板文档
		try {
			//获取所有的docInfo列表
			List<DocInfo> docInfoList = DatabaseSearch.getDocInfoList();
			int i=0;
			for(DocInfo di : docInfoList){
				if(!"个人健康档案".equals(di.getDocName())){
					continue;
				}
//				if(i++>3){
//					return;
//				}
				System.out.println("\n#############################################");
				System.out.println("正在处理文档："+di.getDocName()+"--版本号:"+di.getDocVersion());
				System.out.println("#############################################");
				//新建文档
				XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(filepathString));
				// 存储字典值域代码（id）
				List<String> fieldValueList = new ArrayList<String>();
				// 填补表，在fieldValueList中获得
				fillDocInfoTable(document, fieldValueList, di);

				// 添加各个字典表
				addDictionaryTables(document, fieldValueList);
				//添加最后文档
				addXMLAndTitleDocInfo(document, di);
				FileOutputStream outStream = null;
				outStream = new FileOutputStream(destpathString+di.getDocName()+".docx");
				document.write(outStream);
				outStream.close();
			}
			
			System.out.println("\n\n生成完成，共生成"+docInfoList.size()+"份文件！");
			DatabaseSearch.closeConnection();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
 
       
	}

	/**
	 * 添加xml文档，以及替换doc文档中文档信息相关字段（文档名，版本等）
	 * @param document
	 * @param di
	 */
	public static void addXMLAndTitleDocInfo(XWPFDocument document, DocInfo di) {
		//替换文档名，版本号
		Map<String, String> map = new HashMap<String, String>();
		map.put("${title}", di.getDocName());
		document.getDocument().getBody().getSdtList().get(0).getSdtContent().getPList().get(2).getRList().get(0).getTList().get(0).setStringValue(di.getDocName());
		if(di.getDocVersion()!=null && di.getDocVersion().trim()!="")
		document.getDocument().getBody().getSdtList().get(0).getSdtContent().getPList().get(3).getRList().get(0).getTList().get(0).setStringValue( "("+di.getDocVersion()+"版)");
		replaceWord(document, map);
		//填入xml原始文档
		
	}

	/**
	 * 添加字典表
	 * @param fieldValueList
	 */
	public static void addDictionaryTables(XWPFDocument document, List<String> fieldValueList) {
		List<DictionaryTable> dictionaryTableList = new ArrayList<DictionaryTable>();
		 //list集合去重
        for (int i = 0; i < fieldValueList.size() - 1; i++) {     //循环遍历集体中的元素
            for (int j = fieldValueList.size() - 1; j > i; j--) {  //这里非常巧妙，这里是倒序的是比较
                if (fieldValueList.get(j).equals(fieldValueList.get(i))) {
                    fieldValueList.remove(j);
                }
            }
        }
		//遍历所有字典类型
		for (String str : fieldValueList) {
			//获取字典类型
			DictionaryType dt = DatabaseSearch.getDictionaryType(str);
			if (dt == null || dt.getTypeName() == null || dt.getTypeName().trim() == "") {
				continue;
			}
			//获取字典字段列表
			List<DictionaryField> dictionaryFieldList = DatabaseSearch.getDictionaryField(str);
			if (dictionaryFieldList.size() == 0) {
				continue;
			}
			//获取DictionaryTable，添加到待处理列表dictionaryTableList中
			DictionaryTable dta = new DictionaryTable();
			dta.setDictionaryFieldList(dictionaryFieldList);
			dta.setDictionaryType(dt);
			dictionaryTableList.add(dta);
		//	System.out.println("待处理列表" + dt.getTypeName());
			//测试用
			//break;
		}
		//word操作，写入table
		addDictionaryTablesToDocx(document, dictionaryTableList);
	}

	/**
	 * 将字典表写入word中
	 * @param dictionaryTableList
	 */
	private static boolean addDictionaryTablesToDocx(XWPFDocument document, List<DictionaryTable> dictionaryTableList) {
		//获取字典表位置，写入字典表
		try {
			 
			// 替换表格中的指定文字
			Iterator<XWPFTable> itTable = document.getTablesIterator();
			//获取到表位置
			XWPFTable dictionaryTemplateTable=null;
			int tableCount = 0;
			while (itTable.hasNext()) {
				tableCount++;
				XWPFTable table = (XWPFTable) itTable.next();
				//System.out.println("table index : " + tableCount + "---Row count:" + rcount);
				if (tableCount == 8) {
					dictionaryTemplateTable = table;
				}
			}
			//标题写入
			//表头两行吸入
			//表体写入
			int countDicTable=0;
			for(DictionaryTable dt : dictionaryTableList){
				//System.out.println("共有"+dictionaryTableList.size()+"张表，正在处理表-----"+dt.getDictionaryType().getTypeName());
				if(countDicTable>0){
					//加入回车
					XWPFParagraph p3 = document.createParagraph();
			        XWPFRun r3= p3.createRun();
			        r3.addCarriageReturn();
				}
				//加入表标题
				XWPFParagraph p2 = document.createParagraph();
		        XWPFRun r2= p2.createRun();
		        r2.setText("4.2."+(++countDicTable)+"  "+dt.getDictionaryType().getStandardCode()+" "+dt.getDictionaryType().getTypeName());
		        r2.setBold(true);
		        r2.setFontFamily("Courier");
		        p2.setVerticalAlignment(TextAlignment.BOTTOM);
		        r2.addCarriageReturn();
		        
		       
		        XWPFTable table=document.createTable(3+dt.getDictionaryFieldList().size(),2);
				//table.setWidth(6000);
				
				//设置列宽
		        CTTblGrid grid = table.getCTTbl().addNewTblGrid();
				//录入表头信息
				//CTTcPr cttcpr1 = table.getCTTbl().addnew
				BigInteger bigIntWidth = new BigInteger("4000");
                table.getCTTbl().getTrList().get(1).getTcList().get(1).addNewTcPr().addNewGridSpan().setVal(new BigInteger("2"));
                table.getCTTbl().getTrList().get(0).getTcList().get(1).addNewTcPr().addNewGridSpan().setVal(new BigInteger("2"));
		        table.getRow(0).getCell(0).setText("编码系统名称");
		        table.getRow(0).getCell(1).setText(dt.getDictionaryType().getTypeName());
		        table.getRow(1).getCell(0).setText("编码系统");
		        table.getRow(1).getCell(1).setText(dt.getDictionaryType().getTypeCode());
		        table.getRow(2).createCell();
//		        table.getRow(1).getCell(1).getBodyElements().
		        table.getRow(0).getCell(0).setColor("E4E6E1");
		        table.getRow(0).getCell(1).setColor("E4E6E1");
		        table.getRow(1).getCell(0).setColor("E4E6E1");
		        table.getRow(1).getCell(1).setColor("E4E6E1");
		        
		        table.getRow(2).getCell(0).setColor("ECEDE8");
		        table.getRow(2).getCell(1).setColor("ECEDE8");
		        table.getRow(2).getCell(2).setColor("ECEDE8");
		        table.getRow(2).getCell(0).setText("值");
				table.getRow(2).getCell(1).setText("显示名称");
				table.getRow(2).getCell(2).setText("备注");
				int rowIndex=3;
				for(DictionaryField df : dt.getDictionaryFieldList()){
					table.getRow(rowIndex).createCell();
					table.getRow(rowIndex).getCell(0).setText(df.getValue()==null?"":df.getValue());
					table.getRow(rowIndex).getCell(1).setText(df.getName()==null?"":df.getName());
					table.getRow(rowIndex).getCell(2).setText(df.getMark()==null?"":df.getMark());
				//	System.out.println("------"+dt.getDictionaryType().getTypeName()+"---"+df.getName()+"---"+df.getValue()+"---"+df.getMark());
					rowIndex++;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 将字典表写入word中
	 * @param dictionaryTableList
	 */
	private static boolean addDictionaryTablesToDocx_Old(XWPFDocument document, List<DictionaryTable> dictionaryTableList) {
		//获取字典表位置，写入字典表
		try {
			 
			// 替换表格中的指定文字
			Iterator<XWPFTable> itTable = document.getTablesIterator();
			//获取到表位置
			XWPFTable dictionaryTemplateTable=null;
			int tableCount = 0;
			while (itTable.hasNext()) {
				tableCount++;
				XWPFTable table = (XWPFTable) itTable.next();
				int rcount = table.getNumberOfRows();
				//System.out.println("table index : " + tableCount + "---Row count:" + rcount);
				if (tableCount == 8) {
					dictionaryTemplateTable = table;
				}
			}
			//标题写入
			//表头两行吸入
			//表体写入
			int countDicTable=0;
			for(DictionaryTable dt : dictionaryTableList){
				//System.out.println("共有"+dictionaryTableList.size()+"张表，正在处理表-----"+dt.getDictionaryType().getTypeName());
				if(countDicTable>0){
					//加入回车
					XWPFParagraph p3 = document.createParagraph();
			        XWPFRun r3= p3.createRun();
			        r3.addCarriageReturn();
				}
				//加入表标题
				XWPFParagraph p2 = document.createParagraph();
		        XWPFRun r2= p2.createRun();
		        r2.setText("4.2."+(++countDicTable)+"  "+dt.getDictionaryType().getStandardCode()+" "+dt.getDictionaryType().getTypeName());
		        r2.setBold(true);
		        r2.setFontFamily("Courier");
		        p2.setVerticalAlignment(TextAlignment.BOTTOM);
		        r2.addCarriageReturn();
				//建表
				XWPFTable table=document.createTable(3+dt.getDictionaryFieldList().size(),2);
				
				//录入表头信息
		        table.getRow(0).getCell(0).setText("编码系统名称");
		        table.getRow(0).getCell(1).setText(dt.getDictionaryType().getTypeName());
		        table.getRow(1).getCell(0).setText("编码系统");
		        table.getRow(1).getCell(1).setText(dt.getDictionaryType().getTypeCode());
		        table.getRow(2).createCell();
		        //table.getRow(1).getCell(1).getBodyElements().
		       /* List<CTTbl> tblist = document.getDocument().getBody().getTblList();
		        BigInteger bigIntWidth = new BigInteger("5000");
		        tblist.get(tblist.size()-1).getTrList().get(1).getTcList()*/
		        table.getRow(2).getCell(0).setText("值");
				table.getRow(2).getCell(1).setText("显示名称");
				table.getRow(2).getCell(2).setText("备注");
				//System.out.println("----table处理中---"+dt.getDictionaryType().getTypeName());
				int rowIndex=3;
				for(DictionaryField df : dt.getDictionaryFieldList()){
					table.getRow(rowIndex).createCell();
					table.getRow(rowIndex).getCell(0).setText(df.getValue()==null?"":df.getValue());
					table.getRow(rowIndex).getCell(1).setText(df.getName()==null?"":df.getName());
					table.getRow(rowIndex).getCell(2).setText(df.getMark()==null?"":df.getMark());
				//	System.out.println("------"+dt.getDictionaryType().getTypeName()+"---"+df.getName()+"---"+df.getValue()+"---"+df.getMark());
					rowIndex++;
				}
				document.insertTable(++countDicTable, table);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	
	
	/**
	 * 读取文件到string中
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}
}
