package utill;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private String excelFilePath;
    private XSSFSheet sheet;
    private XSSFWorkbook workbook;

    public ExcelReader(String excelFilePath) throws IOException {
        this.excelFilePath = excelFilePath;
        File file = new File(excelFilePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheetAt(0);
        } catch (IOException e){
            throw new IOException("Не поддерживаемый формат!");
        }
    }

    public ExcelReader(String excelFilePath, String sheet) throws IOException {
        this.excelFilePath = excelFilePath;
        File file = new File(excelFilePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
            this.sheet = workbook.getSheet(sheet);
        } catch (IOException e){
            throw new IOException("Не поддерживаемый формат!");
        }
    }

    private String cellToString(XSSFCell cell) throws Exception {
        Object result = null;
        if (cell==null){
           return "";
        }
        CellType type = cell.getCellType();
        switch (type){
            case STRING :
                result=cell.getStringCellValue();
                break;
            case NUMERIC :
                result=cell.getNumericCellValue();
                break;
            default: throw new Exception("Нечитаемый формат ячейки!");
        }
        return result.toString();
    }

    public String[][] getData() throws Exception {
        int countColumn = this.sheet.getRow(0).getLastCellNum();
        int countRow = this.sheet.getLastRowNum();
        String [][] data = new String[countRow][countColumn];
        for(int i=1; i<=countRow; i++){
            for (int j = 0; j <countColumn; j++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell=row.getCell(j);
                String value=cellToString(cell);
                data[i-1][j]=value;
                if (value == null){
                    data[i-1][j]="";
                }
            }
        }
        return data;
    }

}
