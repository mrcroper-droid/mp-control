package com.wcsoft.service.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcsoft.entity.BaseModel;

/**
 * @author: theTian
 * @date: 2021/1/29 18:18
 */
public class ExcelUtils {

    static ObjectMapper imapper = new ObjectMapper();
    
    /**
     * 生成单元格样式
     *
     * @param workbook Workbook
     * @param verticalAlignmentCenter 是否垂直居中
     * @param horizontalAlignmentCenter 是否水平居中
     * @param bold 是否加粗
     * @param fontName 字体名
     * @param fontSize 字号
     * @return
     */
    public static CellStyle getCellStyle(Workbook workbook, boolean verticalAlignmentCenter, boolean horizontalAlignmentCenter,
                                   boolean bold, String fontName, int fontSize) {
        CellStyle cellStyle = workbook.createCellStyle();
        if (verticalAlignmentCenter) {
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }
        if (horizontalAlignmentCenter) {
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
        }
        Font font = workbook.createFont();
        if (bold) {
            font.setBold(true);
        }
        font.setFontHeight((short) (20 * fontSize));
        if (fontName == null) {
            font.setFontName("宋体");
        } else {
            font.setFontName(fontName);
        }
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static CellStyle getCellStyle(Workbook workbook, boolean verticalAlignmentCenter, boolean horizontalAlignmentCenter,
                                         boolean bold, String fontName, int fontSize, short fontColor) {
        CellStyle cellStyle = workbook.createCellStyle();
        if (verticalAlignmentCenter) {
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }
        if (horizontalAlignmentCenter) {
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
        }
        Font font = workbook.createFont();
        if (bold) {
            font.setBold(true);
        }
        font.setFontHeight((short) (20 * fontSize));
        if (fontName == null) {
            font.setFontName("宋体");
        } else {
            font.setFontName(fontName);
        }
        if (fontColor != -1){
            font.setColor(fontColor);
        }
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static String getColName(int indexCol) {
        return CellReference.convertNumToColString(indexCol);
    }

    public static void setBorder(CellStyle cellStyle){
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
    }
  
    public static void writeExcel(Class claszz, List params, String fileUri) {
    	writeExcel(claszz, params, fileUri, 1, "模板", null).finish();;
    }
    public static ExcelWriter writeExcel(Class claszz, List params, String fileUri, Integer sheetNo, String sheetName, ExcelWriter excelWriter) {
    	ArrayList<Object> workOrderExcelModelList = new ArrayList<>();
    	if(params == null || params.size()<1) {
    		throw new RuntimeException("无数据,导出excel失败");
    	}
    	if(params.get(0) instanceof BaseModel) {
    		workOrderExcelModelList.addAll(params);
    	}else {
    		for(Object  param : params) {
    			try {
    				workOrderExcelModelList.add(imapper.readValue(imapper.writeValueAsString(param), claszz));
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
//	    EasyExcel.write(fileUri, claszz).sheet(sheetNo ,sheetName).doWrite(workOrderExcelModelList);
	   if(excelWriter ==null) {
			excelWriter = EasyExcel.write(fileUri, claszz).build();
	   }
	   WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, sheetName).head(claszz).build();
       return excelWriter.write(workOrderExcelModelList, writeSheet);
    }
    public static <T extends BaseRowModel> List<T>  read(String filename, Class<T> rowModel) throws Exception{
    	return read(filename, rowModel, 0 );
    }
    public static <T extends BaseRowModel> List<T>  read(String filename, Class<T> rowModel,int SheetNo) throws Exception{

        ExcelListener excelListener = new ExcelListener();
        ExcelReader excelReader = getExcelReader(new File(filename),excelListener,true);
        if(excelReader == null){
            return new ArrayList();
        }
        List<Sheet> sheetArr = excelReader.getSheets();
        Sheet sheet = sheetArr.get(SheetNo);
        sheet.setClazz(rowModel);
        excelReader.read(sheet);
        List<T> list = new ArrayList<>();
        for(Object obj:excelListener.getDataList()){
                list.add((T)obj);
        }
        return list;
    }


    /**
     *
     * @param in 文件输入流
     * @param customContent 自定义模型可以在AnalysisContext中获取用于监听者回调使用
     * @param eventListener 用户监听
     * @param trim 是否对解析的String做trim()默认true,用于防止excel中空格引起的装换报错
     * @return
     */
    public static ExcelReader getExcelReader(File file,
                                             AnalysisEventListener eventListener, boolean trim) throws Exception{
        String fileName  = file.getName();
        if (fileName == null ) {
            throw new Exception("文件格式错误！");
        }
        if (!fileName.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue()) && !fileName.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue())) {
            throw new Exception("文件格式错误！");
        }
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            if (fileName.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue())) {
                return new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, eventListener, false);
            } else {
                return new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, eventListener, false);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
