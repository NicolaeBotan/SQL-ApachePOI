package poi;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestData {
    //working on TestData Excel File

    XSSFSheet page;

    @Before
    public void setUp() throws IOException, InvalidFormatException {
        File excelFile = new File("src/test/resources/TestData.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        //FileInputStream creates a copy of the File in memory
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        page = workbook.getSheetAt(0);
    }

    @Test
    public void getTestData() {
        //Retrieve info about property with policy number 100678 and validate:
        // 1- state is WI
        // 2- Region is Midwest
        // 3- Business type is Apartment

        String policy = "100678";
        String expectedState = "WI";
        String expectedRegion = "Midwest";
        String expectedBusiness = "Apartment";
        for (int i = page.getFirstRowNum(); i <= page.getLastRowNum(); i++) {
            XSSFRow currentRow = page.getRow(i);
            for (int j = currentRow.getFirstCellNum(); j < currentRow.getLastCellNum(); j++) {
                XSSFCell currentCell = currentRow.getCell(j);
                if (!currentCell.toString().equals(policy)) {
                    continue;
                }
                Assert.assertEquals(expectedState, currentRow.getCell(3).toString());
                Assert.assertEquals(expectedRegion, currentRow.getCell(4).toString());
                Assert.assertEquals(expectedBusiness, currentRow.getCell(7).toString());
            }
        }
    }

    @Test
    public void test1() {
        String column = "Region";
        int columnIndex = -1;
        //WE USED MINUS 1 INDEX AT THE BEGINNING BECAUSE IF WE MISSPELL THE COLUMN NAME, IT WILL GIVE
        //US THE ERROR BUT IT WON'T GIVE US COLUMN NUMBER 0 INDEX.
        XSSFRow firstRow = page.getRow(0);

        //THIS FIRST LOOP IS IMPLEMENTED TO FIND OUT THE INDEX NUMBER OF THE 'REGIONS' COLUMN
        for (int j = firstRow.getFirstCellNum(); j < firstRow.getLastCellNum(); j++) {
            if (firstRow.getCell(j).toString().equals(column)) {
                columnIndex = j;
            }
        }
        //THIS LOOP WAS IMPLEMENTED TO ITERATE THROUGH ROWS AND PRINT OUT THE CELL IN THE 'REGIONS' INDEX
        // TO PRINT EVERY VALUE FROM 'REGIONS' COLUMN
        for (int i = page.getFirstRowNum(); i <= page.getLastRowNum(); i++) {
            XSSFRow currentRow = page.getRow(i);
            System.out.println(currentRow.getCell(columnIndex));
        }
    }
}
