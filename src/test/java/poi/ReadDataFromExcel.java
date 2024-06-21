package poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadDataFromExcel {

    @Test
    public void readFileTest() throws IOException {
        File excelFile = new File("src/test/resources/SmokeTestSetUp.xlsx");
        //copy path reference, content root of the Excel file

        FileInputStream fileInputStream = new FileInputStream(excelFile);
        //as a parameter is our Excel File

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        // an Excel File its like a book and all the sheets are like pages
        //so, we save it as a workbook data type

        XSSFSheet page1 = workbook.getSheetAt(0);
        //we save it as a page1 Sheet data type
        //get me the first page from workbook

        XSSFRow row1 = page1.getRow(0); //row datatype
        XSSFCell cell1 = row1.getCell(0);// cell datatype

        System.out.println(cell1);//Test Case Name -- name of the column/cell
    }

    @Test
    public void getRowValuesTest() throws IOException {
        File excelFile = new File("src/test/resources/SmokeTestSetUp.xlsx");
        FileInputStream fileInputStream = new FileInputStream(excelFile); //takes as parameter a file
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);// parameter is fileInputStream

        XSSFSheet sheet1 = workbook.getSheet("Sheet1");//name of our Sheet from Excel
        //this getSheet() method can take name of the sheet, also you can use getSheetAt() and pass the
        // index number of sheet as a parameter.

        XSSFRow row1 = sheet1.getRow(0);//to print out the first row
        for (int i = row1.getFirstCellNum(); i < row1.getLastCellNum(); i++) {
            //HERE WE TELL JAVA THAT I IS EQUAL TO FIRST CELL FROM THE GIVEN ROW, AND TELL THAT I IS
            //SMALLER THAT LAST CELL OF THE ROW, AND WE INCREASE I BY 1 EVERY TIME IT ITERATES
            XSSFCell currentCell = row1.getCell(i); // i, will get us every cell one by one

            System.out.print(currentCell + " | ");//to print each cell in one line one by one
            //this loop will help us to print the whole first row
        }
    }

    @Test
    public void getAllRowValuesTest() throws IOException {
        File excelFile = new File("src/test/resources/SmokeTestSetUp.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        //fis means FileInputStream
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet page1 = workbook.getSheetAt(0);

        //HERE WE ARE IMPLEMENTING A LOOP THAT WILL ITERATE THROUGH EVERY ROW AN GET ALL THE INFO
        for (int i = page1.getFirstRowNum(); i <= page1.getLastRowNum(); i++) {
            XSSFRow currentRow = page1.getRow(i);
            for (int j = currentRow.getFirstCellNum(); j < currentRow.getLastCellNum(); j++) {
                XSSFCell currentCell = currentRow.getCell(j);
                System.out.print(currentCell + " |");
            }
            System.out.println();
        }
    }
}
