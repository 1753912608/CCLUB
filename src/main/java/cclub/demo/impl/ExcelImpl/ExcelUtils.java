package cclub.demo.impl.ExcelImpl;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bytedeco.javacv.FrameFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 生成Excel的文件工具类
 */
public class ExcelUtils {

    public static Workbook createWorkBook(List<Map<String,Object>>list,String[] keys,String[] columnNames){
        //创建Excel工作簿
        SXSSFWorkbook wb=new SXSSFWorkbook(100);
        Sheet sheet=wb.createSheet("sheet");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth(i, (int) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow(0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short)10);
        f.setColor(IndexedColors.BLACK.getIndex());

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short)10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        for(int i=0;i<list.size();i++){
            Row row1=sheet.createRow(i);
            for(int j=0;j<keys.length;j++){
                Cell cell=row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }



    /**
     *
     * @param fileSrc
     * @return
     * 解析批量提交候选人的Excel格式是否正确
     */
    public static boolean analysisCandidateExcel(String fileSrc)
    throws Exception {
        Sheet sheet=getExcelFileStream(fileSrc);
        Row row=sheet.getRow(0);
        List<String>list=new ArrayList<>();
        int firstCellIndex=row.getFirstCellNum(),lastCellIndex=row.getLastCellNum();
        for(int index=firstCellIndex;index<=lastCellIndex;index++){
            Cell cell=row.getCell(index);
            if(cell!=null){
                list.add(cell.getStringCellValue());
            }
        }
        if(list.size()!=3)return false;
        if(list.get(0).equals("姓名")&&list.get(1).equals("电话")&&list.get(2).equals("邮箱")){
            return true;
        }
        return false;
    }



    /**
     *
     * @param fileSrc
     * @return
     * 对批量上传的添加候选人Excel进行处理
     */
    public static List<List<String>> handleCandidateExcel(String fileSrc)
    throws Exception {
        List<List<String>>list=new ArrayList<>();
        Sheet sheet=getExcelFileStream(fileSrc);
        int firstRowIndex=sheet.getFirstRowNum()+1,lastRowIndex=sheet.getLastRowNum();
        for(int index=firstRowIndex;index<=lastRowIndex;index++){
            Row row=sheet.getRow(index);
            for(int j=0;j<3;j++){
                list.get(j).add(row.getCell(j).getStringCellValue());
            }
        }
        return list;
    }



    /**
     *
     * @param fileSrc
     * @return
     * 将Excel文件路径转化成工作表对象
     */
    public static Sheet getExcelFileStream(String fileSrc)
    throws Exception{
        String suffex=fileSrc.substring(fileSrc.lastIndexOf('.'));
        File excel=new File("src/main/resources/static/"+fileSrc);
        Workbook wb=null;
        if(suffex.equals(".xls")){
            FileInputStream fis = new FileInputStream(excel);   //文件流对象
            wb = new HSSFWorkbook(fis);
        }else{
            wb = new XSSFWorkbook(excel);
        }
        Sheet sheet=wb.getSheetAt(0);
        return sheet;
    }
}
