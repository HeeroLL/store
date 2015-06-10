package com.zebone.dip.compare.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.zebone.dip.compare.vo.DictInfo;
import com.zebone.dip.compare.vo.FileUpload;
import com.zebone.dip.compare.vo.MainInfo;
import com.zebone.dip.compare.vo.UploadDataVO;

/**
 * excel文件相关操作
 * @author YinCM
 *
 */
@Service
public class ExcelManageService {
	
	@Resource 
	private CompareCommonService compareCommonService;
	
	/**
	 * 
	 * @param typeId 1,2,3,4
	 * @param dataString  
	 * @return 创建的模板文件名称
	 * 医疗机构信息   : 1
	 * 医疗机构人员信息   : 2
	 * 行政区划信息  :3
	 * 诊疗项目信息  : 4
	 * 药品信息 : 5  
	 * 字典 : 6
	 */
	public String createModuleExcel(String typeId){
		List<String> dataList = compareCommonService.generateModuleTemplate(typeId);
		String filePath = null;// this.getClass().getResource("/com/zebone/dip/compare/").getPath()+"excelTemplates/";
		if(typeId.trim().equals("1")){
			filePath = createMaindataModuleExcel(typeId, dataList, "医疗机构信息");
		}else if(typeId.trim().equals("2")){
			filePath = createMaindataModuleExcel(typeId, dataList, "医疗机构人员信息");
		}else if(typeId.trim().equals("3")){
			filePath = createMaindataModuleExcel(typeId, dataList, "行政区划信息");
		}else if(typeId.trim().equals("4")){
			filePath = createMaindataModuleExcel(typeId, dataList, "诊疗项目信息");
		}else if(typeId.trim().equals("5")){
			filePath = createMaindataModuleExcel(typeId, dataList, "药品信息");
		}else if(typeId.trim().equals("6")){
			filePath = createMaindataModuleExcel(typeId, dataList, "字典");
		}		
		return filePath;
	}

	
	@Test
	public void testtest(){
		String path = this.getClass().getClassLoader().getResource("").getPath();
		path = path.substring(0, path.lastIndexOf("/WEB-INF/classes/"));
		System.out.println(path);
	}

	/**
	 * 字典模板
	 * @param dataList
	 * @return 文件全地址
	 */
	private String createDictionaryModuleExcel(List<String> dataList) {
		
		return null;
	}

	/**
	 * 主数据模板
	 * @param typeId
	 * @param dataList
	 * @param 地址
	 * @return 文件全地址
	 */
	private String createMaindataModuleExcel(String typeId,	List<String> dataList, String modelFileName) {
		int cellOffset=2;
		if(typeId.trim().equals("6"))
			cellOffset=4;
		
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
		ServletContext servletContext = webApplicationContext.getServletContext();  
		String modelFilePath= servletContext.getRealPath("/")+"/WEB-INF/classes/com/zebone/dip/compare/excelTemplates/"+modelFileName+".xlsx";
		modelFilePath = modelFilePath.replaceAll("%20", " ");
		//模板生成好后最终保存地址
		String destPath=null;
		try {
		
			InputStream inp = new FileInputStream(modelFilePath);
			//InputStream inp = new FileInputStream("workbook.xlsx");
			//Workbook wb = WorkbookFactory.create(inp);
			XSSFWorkbook wb_origin = new XSSFWorkbook(inp);
			inp.close();
			SXSSFWorkbook wb = new SXSSFWorkbook(wb_origin);
			SXSSFSheet sheet = (SXSSFSheet)wb.getSheetAt(0);
			sheet.setRandomAccessWindowSize(1000);//keep 1000 rows in memory, exceeding rows will be flushed to disk
			int rowCount=0;
			for(String str : dataList){
				rowCount++;
				System.out.println(str);
				Row row = sheet.createRow(rowCount);
		
				//writeLeavePieceToRow(row,lp);
				String[] strArray = new String[0];
                if(str!=null && str.length()>0){
                    strArray = str.split("\\*\\*--\\*\\*");
                }
				int cellCount=0;
				for(String fieldStr : strArray){
					//Cell cell = row.getCell();
					Cell cell = row.createCell(cellCount+cellOffset);
					cell.setCellValue(strArray[cellCount].trim());
					//cell.setCellValue(fieldStr[cellCount]);
					cellCount++;
				}
				
			}
			
			Calendar cd = Calendar.getInstance();
			//保存文件
			destPath = modelFilePath;
			destPath = destPath.substring(0, destPath.lastIndexOf("/WEB-INF/classes/"))+"/dipCompare/exceldownload/template/"+modelFileName+"-"+(cd.get(Calendar.MONTH)+1)+cd.get(Calendar.DAY_OF_MONTH)+cd.get(Calendar.HOUR_OF_DAY)+cd.get(Calendar.MINUTE)+cd.get(Calendar.SECOND)+".xlsx";
			destPath = destPath.replaceAll("%20", " ");
			FileOutputStream fileOut = new FileOutputStream(destPath);
			wb.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return destPath;
	}
	
	
	@Deprecated
	private String loadEdxcelToDBAndGenerateResultFile(String typeId, String srcFilePath, List<String> dataList, String modelFileName) {
		boolean isCorrect=true;
		//验证数据，错误的需要标记
		String destPath=null;
		Set<String> uniqueColumnString = new HashSet<String>();
		
		try {
			InputStream inp = new FileInputStream(srcFilePath);
			//InputStream inp = new FileInputStream("workbook.xlsx");
			Workbook wb = WorkbookFactory.create(inp);
			inp.close();
			 
			SXSSFSheet sheet = (SXSSFSheet)wb.getSheetAt(0);
			Row firstRow = sheet.getRow(0);
			//遍历所有行，进行验证，如果遇到错的，在末尾添加一行
			
			for(int i=1; i<1000000; i++){
				Row row = sheet.getRow(i);
				//如果主数据名称与编码都为空，则为末尾行，退出遍历
				//if(row.getCell(2).getStringCellValue()==null )
			}
			
			sheet.setRandomAccessWindowSize(1000);//keep 1000 rows in memory, exceeding rows will be flushed to disk
			
			int rowCount=0;
			for(String str : dataList){
				rowCount++;
				System.out.println(str);
				Row row = sheet.createRow(rowCount);
		
				//writeLeavePieceToRow(row,lp);
				String strArray[] = str.split("\\*\\*--\\*\\*");
				int cellCount=0;
				for(String fieldStr : strArray){
					//Cell cell = row.getCell();
					Cell cell = row.createCell(cellCount);
					cell.setCellValue(strArray[cellCount]);
				//	cell.setCellValue(fieldStr[cellCount]);
					cellCount++;
				}
				
			}
			
			Calendar cd = Calendar.getInstance();
			//保存文件
			destPath = this.getClass().getClassLoader().getResource("").getPath();
			destPath = destPath.substring(0, destPath.lastIndexOf("/WEB-INF/classes/"))+"/dipCompare/exceldownload/template/"+modelFileName+"-"+(cd.get(Calendar.MONTH)+1)+"-"+cd.get(Calendar.DAY_OF_MONTH)+" "+cd.get(Calendar.HOUR_OF_DAY)+"-"+cd.get(Calendar.MINUTE)+"-"+cd.get(Calendar.SECOND)+".xlsx";
			FileOutputStream fileOut = new FileOutputStream(destPath);
			wb.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 将验证通过的数据入库，不通过的标识后另存为一份excel文档
	 * @param typeId
	 * @param dataList
	 * @param 地址
	 * @return 文件全地址
	 */
	public UploadDataVO loadExcelToDBAndGenerateResultFile(FileUpload fileUpload, String srcFilePath)  throws Exception{
		//验证数据，错误的需要标记
		boolean isCorrect=true;
		//返回的错误excel文档路径
		//错误计数
		//正确统计
		UploadDataVO uploadDataVO = new UploadDataVO();
	
		Set<String> uniqueColumnString = new HashSet<String>();
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
		ServletContext servletContext = webApplicationContext.getServletContext();  
		String modelFilePath= servletContext.getRealPath("/")+"/WEB-INF/classes/com/zebone/dip/compare/excelTemplates/temp.xlsx";
		//String modelFilePath = this.getClass().getResource("/com/zebone/dip/compare/excelTemplates/").getPath()+"temp.xlsx";
		modelFilePath = modelFilePath.replaceAll("%20", " ");
			InputStream inpWrite = new FileInputStream(modelFilePath);
			XSSFWorkbook wb_origin = new XSSFWorkbook(inpWrite);
			inpWrite.close();
			SXSSFWorkbook wbWrite = new SXSSFWorkbook(wb_origin);
			SXSSFSheet sheetWrite = (SXSSFSheet)wbWrite.getSheetAt(0);
			SXSSFSheet sheetWrite1 = (SXSSFSheet)sheetWrite;
			sheetWrite1.setRandomAccessWindowSize(1000);//keep 1000 rows in memory, exceeding rows will be flushed to disk
			
			InputStream ins = new FileInputStream(srcFilePath);
			//InputStream inp = new FileInputStream("workbook.xlsx");
			Workbook wb = WorkbookFactory.create(ins);
			ins.close();
			Sheet sheet = wb.getSheetAt(0);
			String sheetName = sheet.getSheetName();
			String fileType = fileUpload.getFileType();
			boolean isFileNameWrong=false;
			if(sheetName==null){
				isFileNameWrong=true;
				return uploadDataVO;
			}else if(fileType.equals("1")){
				 if(!sheetName.equals("yljg")){
					 isFileNameWrong=true;
				 }
			}else if(fileType.equals("2")){
				 if(!sheetName.equals("yljgry")){
					 isFileNameWrong=true;
				 }
			}else if(fileType.equals("3")){
				 if(!sheetName.equals("xzqh")){
					 isFileNameWrong=true;
				 }
			}else if(fileType.equals("4")){
				 if(!sheetName.equals("zlxm")){
					 isFileNameWrong=true;
				 }
			}else if(fileType.equals("5")){
				 if(!sheetName.equals("ypxx")){
					 isFileNameWrong=true;
				 }
			}
			uploadDataVO.setFileNameWrong(isFileNameWrong);
			if(isFileNameWrong){
				return uploadDataVO;
			}
			
			//System.out.println(wb.getActiveSheetIndex());
			//获取首行信息
			Row firstRow = sheet.getRow(0);
			Row firstRowWrite = copyRow(wb, sheet, wbWrite, sheetWrite, 0, 0);
			 
			int countCell=0;
			while(!getStringFromCell(firstRow.getCell(countCell)).trim().equals("标识(标准)")){
				countCell++;
			}
			countCell++;
			Cell cell = firstRowWrite.createCell(countCell);
			cell.setCellValue("错误信息");
			CellStyle style = wbWrite.createCellStyle();
		    Font font = wbWrite.createFont();
		    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		    style.setFont(font);
		    font.setColor(HSSFFont.COLOR_RED);
			cell.setCellStyle(style);
			
			
			//遍历所有行，进行验证，如果遇到错的，在末尾添加一行
			//System.out.println(firstRow.getCell(3));
			//成功录入数据库数量
			int successCount=0;
			for(int i=1; i<1000000; i++){
				boolean isTempCorrect = true;
				Row row = sheet.getRow(i);
				
				//Row rowWrite = sheetWrite1.getRow(i);
				if(row!=null){
					Row rowWrite = copyRow(wb, sheet, wbWrite, sheetWrite, i, i);
					String errorStr = "";
					Cell firstCell = row.getCell(0);
					String str0;
					if(firstCell!=null){
						str0 = getStringFromCell(firstCell);
					}else{
						str0="";
					}
					if(str0==null || str0.trim().equals("")){
						str0="";
					}
					Cell secondCell = row.getCell(1);
					String str1;
					if(secondCell!=null){
						str1 = getStringFromCell(secondCell);
					}else{
						str1="";
					}
					
					if(str1==null || str1.trim().equals("")){
						str1="";
					}
					if(str0.length()>64){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+="名称过长，应小于64!;";
					}
					if(str1.length()>64){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+="编码过长，应小于64!;";
					}
					
					String compositeStr = str0.trim()+str1.trim();
					
					/*****************/
					String str2,str3;
					if(row.getCell(2)!=null){
						str2 = getStringFromCell(row.getCell(2));
					}else{
						str2 = "";
					}
					if(row.getCell(3)!=null){
						str3 = getStringFromCell(row.getCell(3));
					}else{
						str3 = "";
					}
					if(str2.trim().equals("") || str3.trim().equals("")){
						continue;
					}else if(str0.trim().equals("") || str1.trim().equals("") ){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+="存在空白单元格，请填写完整!;";
					}else if(uniqueColumnString.contains(compositeStr)){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+="唯一性校验失败，有重复项目，请排除!;";
					}else{
						//如果验证通过，则加入已存在的set中
						uniqueColumnString.add(compositeStr);
					}
					
					if(isTempCorrect){
						MainInfo mainInfo = new MainInfo();
						uploadDataVO.setCorrectCount(uploadDataVO.getCorrectCount()+1);
						
						mainInfo.setTableName(fileUpload.getTableName());
						if(row.getCell(0).getCellType()==0){
							mainInfo.setName(String.valueOf((int)row.getCell(0).getNumericCellValue()));
						}else if(row.getCell(0).getCellType()==1){
							mainInfo.setName(row.getCell(0).getStringCellValue());
						}
						
						if(row.getCell(1).getCellType()==0){
							mainInfo.setCode(String.valueOf((int)row.getCell(1).getNumericCellValue()));
						}else if(row.getCell(1).getCellType()==1){
							mainInfo.setCode(row.getCell(1).getStringCellValue());
						}
						
						//mainInfo.setCode(row.getCell(1).getStringCellValue());
						if(fileUpload.getFileType().equals("1")){
							mainInfo.setMid(row.getCell(5).getStringCellValue());
						}else if(fileUpload.getFileType().equals("2")){
							mainInfo.setMid(row.getCell(38).getStringCellValue());
						}else if(fileUpload.getFileType().equals("3")){
							mainInfo.setMid(row.getCell(4).getStringCellValue());
						}else if(fileUpload.getFileType().equals("4")){
							mainInfo.setMid(row.getCell(10).getStringCellValue());
						}else if(fileUpload.getFileType().equals("5")){
							mainInfo.setMid(row.getCell(7).getStringCellValue());
						}
						boolean isSuccess = compareCommonService.saveMdCompareInfo(mainInfo);
						if(isSuccess){
							successCount++;
						}else{
							isCorrect=false;
							errorStr+="该机构主数据已经存在！;";
						}
						isTempCorrect=isSuccess;
					}
					
					if(!isTempCorrect){
						uploadDataVO.setFailCount(uploadDataVO.getFailCount()+1);
						Cell newCell = rowWrite.createCell(countCell);
						newCell.setCellValue(errorStr);
						CellStyle newStyle = wbWrite.createCellStyle();
					    Font newFont = wbWrite.createFont();
						newFont.setColor(HSSFFont.COLOR_RED);
						newStyle.setFont(newFont);
						newCell.setCellStyle(newStyle);
					}
					
					//System.out.println(row.getCell(2)+"xxx");
				}else{
					break;
				}
				//System.out.println(i);
				//如果主数据名称与编码都为空，则为末尾行，退出遍历
				//if(row.getCell(2).getStringCellValue()==null )
			}
			if(!isCorrect){
				String destPath = modelFilePath;
				Date date = new Date();
				String strDate = new Long(date.getTime()).toString();
				String errorFileName = fileUpload.getFileType()+strDate+"error";
				destPath = destPath.substring(0, destPath.lastIndexOf("/WEB-INF/classes/"))+"/dipCompare/errorExcelFile/"+errorFileName+".xlsx";
				destPath= destPath.replaceAll("%20", " ");
				uploadDataVO.setFileName(errorFileName);
				
				FileOutputStream fileOut = new FileOutputStream(destPath);
				//wbWrite.write(fileOut);
				wbWrite.write(fileOut);
				fileOut.close();
			}
			//如果全部通过则录入数据库
			/*MainInfo mainInfo = new MainInfo();
			if(isCorrect){
				for(int i=1; i<1000000; i++){
					Row row = sheet.getRow(i);
					if(row!=null){
						mainInfo.setTableName(fileUpload.getTableName());
						mainInfo.setName(row.getCell(0).getStringCellValue());
						mainInfo.setCode(row.getCell(1).getStringCellValue());
						if(fileUpload.getFileType().equals("1")){
							mainInfo.setMid(row.getCell(5).getStringCellValue());
						}else if(fileUpload.getFileType().equals("2")){
							mainInfo.setMid(row.getCell(38).getStringCellValue());
						}else if(fileUpload.getFileType().equals("3")){
							mainInfo.setMid(row.getCell(4).getStringCellValue());
						}else if(fileUpload.getFileType().equals("4")){
							mainInfo.setMid(row.getCell(10).getStringCellValue());
						}else if(fileUpload.getFileType().equals("5")){
							mainInfo.setMid(row.getCell(7).getStringCellValue());
						}
						boolean isSuccess = compareCommonService.saveMdCompareInfo(mainInfo);
						if(isSuccess){
							successCount++;
						}
					}else{
						break;
					}
				}
			}*/
			uploadDataVO.setSuccessCount(successCount);
			
		
		//否则存入excel文档，返回标记错误的excel路径
		return uploadDataVO;
	}
	
	/**
	 * 获取cell中的string值，如果是int，则转换为string
	 * 方法描述: 
	 * @author caixl
	 * @date Feb 13, 2014
	 * @param cell
	 * @return 
	 * String
	 */
	public String getStringFromCell(Cell cell){
		if(cell.getCellType()==0){
			return String.valueOf((int)cell.getNumericCellValue());
		}else if(cell.getCellType()==1){
			return cell.getStringCellValue();
		}
		return null;
	}
	
	/**
	 * 将字典验证通过的数据入库，不通过的标识后另存为一份excel文档
	 * @param typeId
	 * @param dataList
	 * @param 地址
	 * @return 文件全地址
	 * @throws Exception 
	 */
	public UploadDataVO loadDictExcelToDBAndGenerateResultFile(FileUpload fileUpload, String srcFilePath) throws Exception {
		//验证数据，错误的需要标记
		boolean isCorrect=true;
		//返回的错误excel文档路径
		//错误计数
		//正确统计
		UploadDataVO uploadDataVO = new UploadDataVO();
	
		Set<String> uniqueColumnString = new HashSet<String>();
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
		ServletContext servletContext = webApplicationContext.getServletContext();  
		String modelFilePath= servletContext.getRealPath("/")+"/WEB-INF/classes/com/zebone/dip/compare/excelTemplates/temp.xlsx";
		//String modelFilePath = this.getClass().getResource("/com/zebone/dip/compare/excelTemplates/").getPath()+"temp.xlsx";
		modelFilePath = modelFilePath.replaceAll("%20", " ");
			InputStream inpWrite = new FileInputStream(modelFilePath);
			XSSFWorkbook wb_origin = new XSSFWorkbook(inpWrite);
			inpWrite.close();
			SXSSFWorkbook wbWrite = new SXSSFWorkbook(wb_origin);
			SXSSFSheet sheetWrite = (SXSSFSheet)wbWrite.getSheetAt(0);
			SXSSFSheet sheetWrite1 = (SXSSFSheet)sheetWrite;
			sheetWrite1.setRandomAccessWindowSize(1000);//keep 1000 rows in memory, exceeding rows will be flushed to disk
			
			InputStream ins = new FileInputStream(srcFilePath);
			//InputStream inp = new FileInputStream("workbook.xlsx");
			Workbook wb = WorkbookFactory.create(ins);
			ins.close();
			Sheet sheet = wb.getSheetAt(0);
			String sheetName = sheet.getSheetName();
			if(sheetName==null || !sheetName.equals("zd")){
				uploadDataVO.setFileNameWrong(true);
				return uploadDataVO;
			}
			//System.out.println(wb.getActiveSheetIndex());
			//获取首行信息
			Row firstRow = sheet.getRow(0);
			Row firstRowWrite = copyRow(wb, sheet, wbWrite, sheetWrite, 0, 0);
			 
			int countCell=0;
			while(firstRow.getCell(countCell)!=null && getStringFromCell(firstRow.getCell(countCell))!=null &&getStringFromCell(firstRow.getCell(countCell)).trim()!=""){
				countCell++;
			}
			Cell cell = firstRowWrite.createCell(countCell);
			cell.setCellValue("错误信息");
			CellStyle style = wbWrite.createCellStyle();
		    Font font = wbWrite.createFont();
		    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		    style.setFont(font);
		    font.setColor(HSSFFont.COLOR_RED);
			cell.setCellStyle(style);
			
			
			String	col0Name="类型名称（机构）";
			String	col1Name="类型编码（机构）";
			String	col2Name="名称（机构）";
			String	col3Name="编码（机构）";
			 
			
			//遍历所有行，进行验证，如果遇到错的，在末尾添加一行
			//System.out.println(firstRow.getCell(3));
			//入库成功的数量
			int successCount=0;
			for(int i=1; i<1000000; i++){
				boolean isTempCorrect = true;
				Row row = sheet.getRow(i);
				
				//Row rowWrite = sheetWrite1.getRow(i);
				if(row!=null){
					Row rowWrite = copyRow(wb, sheet, wbWrite, sheetWrite, i, i);
					String errorStr = "";
					Cell firstCell = row.getCell(0);
					String str0;
					if(firstCell!=null){
						str0 = getStringFromCell(firstCell);
					}else{
						str0="";
					}
					if(str0==null || str0.trim().equals("")){
						str0="";
					}
					Cell secondCell = row.getCell(1);
					String str1;
					if(secondCell!=null){
						str1 = getStringFromCell(secondCell);
					}else{
						str1="";
					}
					
					if(str1==null || str1.trim().equals("")){
						str1="";
					}
					if(str0.length()>64){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+=col0Name+"过长，应小于64!;";
					}
					if(str1.length()>64){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+=col1Name+"过长，应小于64!;";
					}
					
					String str2="";
					 
					Cell thirdCell = row.getCell(2);
					
					if(thirdCell!=null){
						str2 = getStringFromCell(thirdCell);
					}else{
						str2="";
					}
					if(str2==null || str2.trim().equals("")){
						str2="";
					}
					if(str2.length()>64){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+=col2Name+"过长，应小于64!;";
					}
					String str3="";
					 
					Cell fourthCell = row.getCell(3);
					
					if(fourthCell!=null){
						str3 = getStringFromCell(fourthCell);//fourthCell.getStringCellValue();
					}else{
						str3="";
					}
					if(str3==null || str3.trim().equals("")){
						str3="";
					}
					if(str3.length()>64){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+=col3Name+"过长，应小于64!;";
					}
					
					String compositeStr = str0.trim()+str1.trim()+str2.trim()+str3.trim();
					
					/******/
					String str4,str5,str6,str7;
					if(row.getCell(4)!=null){
						str4 = getStringFromCell(row.getCell(4));
					}else{
						str4 = "";
					}
					if(row.getCell(5)!=null){
						str5 = getStringFromCell(row.getCell(5));
					}else{
						str5 = "";
					}
					if(row.getCell(6)!=null){
						str6 = getStringFromCell(row.getCell(6));
					}else{
						str6 = "";
					}
					if(row.getCell(7)!=null){
						str7 = getStringFromCell(row.getCell(7));
					}else{
						str7 = "";
					}
					
					if(str4.trim().equals("") || str5.trim().equals("")|| str6.trim().equals("")|| str7.trim().equals("")){
						continue;
					}else if(str0.trim().equals("") || str1.trim().equals("")|| str2.trim().equals("")|| str3.trim().equals("")){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+="存在空白单元格，请填写完整!;";
					}else if(uniqueColumnString.contains(compositeStr)){
						isCorrect = false;
						isTempCorrect = false;
						errorStr+="唯一性校验失败，有重复项目，请排除!;";
					}else{
						//如果验证通过，则加入已存在的set中
						uniqueColumnString.add(compositeStr);
					}
					
					/******************************/
					
					/******************************/
					
					if(isTempCorrect){
						DictInfo dictInfo= new DictInfo();
						uploadDataVO.setCorrectCount(uploadDataVO.getCorrectCount()+1);
						if(row.getCell(0).getCellType()==0){
							dictInfo.setOrgDictType(String.valueOf((int)row.getCell(0).getNumericCellValue()));
						}else if(row.getCell(0).getCellType()==1){
							dictInfo.setOrgDictType(row.getCell(0).getStringCellValue());
						}
						
						if(row.getCell(1).getCellType()==0){
							dictInfo.setOrgDictTypeCode(String.valueOf((int)row.getCell(1).getNumericCellValue()));
						}else if(row.getCell(1).getCellType()==1){
							dictInfo.setOrgDictTypeCode(row.getCell(1).getStringCellValue());
						}
						
						if(row.getCell(2).getCellType()==0){
							dictInfo.setOrgDict(String.valueOf((int)row.getCell(2).getNumericCellValue()));
						}else if(row.getCell(2).getCellType()==1){
							dictInfo.setOrgDict(row.getCell(2).getStringCellValue());
						}
						
						if(row.getCell(3).getCellType()==0){
							dictInfo.setOrgDictCode(String.valueOf((int)row.getCell(3).getNumericCellValue()));
						}else if(row.getCell(3).getCellType()==1){
							dictInfo.setOrgDictCode(row.getCell(3).getStringCellValue());
						}
						
//						dictInfo.setOrgDictType(row.getCell(0).getStringCellValue());
//						dictInfo.setOrgDictTypeCode(row.getCell(1).getStringCellValue());
//						dictInfo.setOrgDict(row.getCell(2).getStringCellValue());
//						dictInfo.setOrgDictCode(row.getCell(3).getStringCellValue());
						dictInfo.setDictId(row.getCell(9).getStringCellValue());
						dictInfo.setDictTypeId(row.getCell(8).getStringCellValue());
						dictInfo.setDictTypeCode(row.getCell(5).getStringCellValue());
						dictInfo.setDictCode(row.getCell(7).getStringCellValue());
						dictInfo.setDict(row.getCell(6).getStringCellValue());
						boolean isSuccess = compareCommonService.saveDictCompareInfo(dictInfo);
						if(isSuccess){
							successCount++;
						}else{
							isCorrect=false;
							errorStr+="该机构数据字典已经存在！;";
						}
						isTempCorrect=isSuccess;
						
					}
					if(!isTempCorrect)
					{
						isCorrect = false;
						uploadDataVO.setFailCount(uploadDataVO.getFailCount()+1);
						Cell newCell = rowWrite.createCell(countCell);
						newCell.setCellValue(errorStr);
						CellStyle newStyle = wbWrite.createCellStyle();
					    Font newFont = wbWrite.createFont();
						newFont.setColor(HSSFFont.COLOR_RED);
						newStyle.setFont(newFont);
						newCell.setCellStyle(newStyle);
					}
					//System.out.println(row.getCell(2)+"xxx");
				}else{
					break;
				}
				//System.out.println(i);
				//如果主数据名称与编码都为空，则为末尾行，退出遍历
				//if(row.getCell(2).getStringCellValue()==null )
			}
			uploadDataVO.setSuccessCount(successCount);
			if(!isCorrect){
				String destPath = modelFilePath;
				Date date = new Date();
				String strDate = new Long(date.getTime()).toString();
				String errorFileName = fileUpload.getFileType()+strDate+"error";
				destPath = destPath.substring(0, destPath.lastIndexOf("/WEB-INF/classes/"))+"/dipCompare/errorExcelFile/"+errorFileName+".xlsx";
				destPath = destPath.replaceAll("%20", " ");
				uploadDataVO.setFileName(errorFileName);
				FileOutputStream fileOut = new FileOutputStream(destPath);
				//wbWrite.write(fileOut);
				wbWrite.write(fileOut);
				fileOut.close();
			}
			
			//如果全部通过则录入数据库
/*			int successCount=0;
			DictInfo dictInfo= new DictInfo();
			if(isCorrect){
				for(int i=1; i<1000000; i++){
					Row row = sheet.getRow(i);
					if(row!=null){
						dictInfo.setOrgDictType(row.getCell(0).getStringCellValue());
						dictInfo.setOrgDictTypeCode(row.getCell(1).getStringCellValue());
						dictInfo.setOrgDict(row.getCell(2).getStringCellValue());
						dictInfo.setOrgDictCode(row.getCell(3).getStringCellValue());
						dictInfo.setDictId(row.getCell(9).getStringCellValue());
						dictInfo.setDictTypeId(row.getCell(8).getStringCellValue());
						dictInfo.setDictTypeCode(row.getCell(5).getStringCellValue());
						dictInfo.setDictCode(row.getCell(7).getStringCellValue());
						dictInfo.setDict(row.getCell(6).getStringCellValue());
						boolean isSuccess = compareCommonService.saveDictCompareInfo(dictInfo);
						if(isSuccess){
							successCount++;
						}
					}else{
						break;
					}
				}
			}*/
			
		
		
		//否则存入excel文档，返回标记错误的excel路径
		return uploadDataVO;
	}
	
	private Row copyRow(Workbook workbooksrc, Sheet worksheetsrc, SXSSFWorkbook workbookdest, SXSSFSheet worksheetdest, int sourceRowNum, int destinationRowNum) {
        // Get the source / new row
        Row sourceRow = worksheetsrc.getRow(sourceRowNum);

        // If the row exist in destination, push down all rows by 1 else create a new row
        Row newRow = worksheetdest.createRow(destinationRowNum);

        // Loop through source columns to add to new row
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            // Grab a copy of the old/new cell
            Cell oldCell = sourceRow.getCell(i);
            Cell newCell = newRow.createCell(i);

            // If the old cell is null jump to next cell
            if (oldCell == null) {
                newCell = null;
                continue;
            }

            // Copy style from old cell and apply to new cell
            CellStyle newCellStyle = workbookdest.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            newCell.setCellStyle(newCellStyle);

            // If there is a cell comment, copy
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // If there is a cell hyperlink, copy
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // Set the cell data type
            newCell.setCellType(oldCell.getCellType());

            // Set the cell data value
            switch (oldCell.getCellType()) {
                case Cell.CELL_TYPE_BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
            }
        }
        return newRow;
    }
	
}
