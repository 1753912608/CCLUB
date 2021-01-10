package cclub.demo.impl.ExcelImpl;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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
}
