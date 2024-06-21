package poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class WriteExcel {

    //HERE WE WILL LEARN HOW TO WRITE IN EXCEL FILE

    @Test
    public void writeTest() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File
                ("src/test/resources/SmokeTestSetUp.xlsx")));
        XSSFSheet page1 = workbook.getSheetAt(0);
        XSSFRow row = page1.getRow(0);

        //here we will create a new Cell in the first row
        // and giving the name of the column/cell "Owner"
        XSSFCell newCell = row.createCell(6);
        newCell.setCellValue("Owner");

        //now we will create a FileOutputStream
        FileOutputStream fos = new FileOutputStream(new File
                ("src/test/resources/SmokeTestSetUp.xlsx"));
        workbook.write(fos);

    }
}
