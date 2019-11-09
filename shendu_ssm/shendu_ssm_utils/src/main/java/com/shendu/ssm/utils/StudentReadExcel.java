package com.shendu.ssm.utils;

import com.shendu.ssm.domain.StudentDetail;
import com.shendu.ssm.domain.StudentDetail;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentReadExcel {
    //总行数  
    private static int totalRows = 0;
    //总条数  
    private static int totalCells = 0;
    //错误信息接收器  
    private static String errorMsg;
    //构造方法  
    public StudentReadExcel(){}
    //获取总行数  
    public static int getTotalRows()  { return totalRows;}
    //获取总列数  
    public static int getTotalCells() {  return totalCells;}
    //获取错误信息  
    public static String getErrorInfo() { return errorMsg; }
    public static List<StudentDetail> studentDetails;
  /** 
   * 读EXCEL文件，获取信息集合 
   * @param fielName 
   * @return 
   */  
    public static List<StudentDetail> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名

        try {  
            if (!validateExcel(fileName)) {// 验证文件名是否合格  
                return null;  
            }  
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本  
            if (isExcel2007(fileName)) {  
                isExcel2003 = false;  
            }
            studentDetails = (List<StudentDetail>) createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return studentDetails;
    }  
    
  /** 
   * 根据excel里面的内容读取客户信息 
   * @param is 输入流 
   * @param isExcel2003 excel是2003还是2007版本 
   * @return 
   * @throws IOException 
   */  
    private static List<StudentDetail> createExcel(InputStream is, boolean isExcel2003) {

        try{  
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003  
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007  
                wb = new XSSFWorkbook(is);
            }
            studentDetails = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();  
        }  
        return studentDetails;
    }  
    
  /** 
   * 读取Excel里面客户的信息 
   * @param wb 
   * @return 
   */  
    private static List<StudentDetail> readExcelValue(Workbook wb) {
        // 得到第一个shell  
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数  
        totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)  
        if (totalRows > 1 && sheet.getRow(0) != null) {  
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }  
        List<StudentDetail> studentDetail = new ArrayList<StudentDetail>();
        // 循环Excel行数  
        for (int r = 1; r < totalRows; r++) {  
            Row row = sheet.getRow(r);
            if (row == null){  
                continue;  
            }
            StudentDetail StudentDetail = new StudentDetail();
            // 循环Excel的列  
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {  
                    if (c == 0) {

                        StudentDetail.setName(getValue(cell));//姓名

                    } else if (c == 1) {
                        if (getValue(cell) !=null){

                                StudentDetail.setPhone(getValue(cell));//学生电话
                        }

                    } else if (c == 2){
                        if (getValue(cell) !=null){
                            StudentDetail.setParentPhone(getValue(cell));//家长电话
                        }
                    }
                }  
            }  
            // 添加到list  
            studentDetail.add(StudentDetail);
        }  
        return studentDetail;
    }  
      
    /** 
     * 验证EXCEL文件 
     *  
     * @param filePath 
     * @return 
     */  
    private static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {  
            errorMsg = "文件名不是excel格式";  
            return false;  
        }  
        return true;  
    }  
      
    // @描述：是否是2003的excel，返回true是2003   
    private static boolean isExcel2003(String filePath)  {
         return filePath.matches("^.+\\.(?i)(xls)$");    
     }    
     
    //@描述：是否是2007的excel，返回true是2007   
    private static boolean isExcel2007(String filePath)  {
         return filePath.matches("^.+\\.(?i)(xlsx)$");    
     }
    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    //解决excel类型问题，获得数值
    private static   String getValue(Cell cell) {
        String value = "";
        if(null==cell){
            return value;
        }
        switch (cell.getCellType()) {
            //数值型
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    value = format.format(date);;
                }else {// 纯数字
                    BigDecimal big=new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    //解决1234.0  去掉后面的.0
                    if(null!=value&&!"".equals(value.trim())){
                        String[] item = value.split("[.]");
                        if(1<item.length&&"0".equals(item[1])){
                            value=item[0];
                        }
                    }
                }
                break;
            //字符串类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case Cell.CELL_TYPE_FORMULA:
                //读公式计算值
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                value = " "+ cell.getBooleanCellValue();
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if("null".endsWith(value.trim())){
            value="";
        }
        return value;
    }

} 
