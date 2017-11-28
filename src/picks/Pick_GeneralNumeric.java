/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picks;

import core.File_Details;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author john
 */
public class Pick_GeneralNumeric {

    public static ArrayList<Double> pick(String file, int loop, int position) throws Exception {
        int j, x;
        // array list to store the forks
        ArrayList<Double> lists = new ArrayList<>();
        //calling the file name.....
        XSSFWorkbook workbook = File_Details.readFileName(file);
        x = loop;// setting the sheet number...
        XSSFSheet spreadsheet = workbook.getSheetAt(x);
        String sname = workbook.getSheetName(x);

        Row row;
        Cell cell = null;
        for (j = 0; j < spreadsheet.getLastRowNum() + 1; ++j) {//To loop thru the rows in a sheet
            row = spreadsheet.getRow(j);
            cell = row.getCell(position); //forks are in the eighth column...
            switch (cell.getCellType()) {
                //Checking for strings values inthe cells..
                case Cell.CELL_TYPE_STRING:

                    break;
                //Checking for numeric values inthe cells..
                case Cell.CELL_TYPE_NUMERIC:
                    lists.add(cell.getNumericCellValue());
                    break;
                //Checking for bank in the cells..
                case Cell.CELL_TYPE_BLANK:
                    break;
            }//end of switch statement

        }// end of  for loop for the rows..

        //returns the arraylist to the main class....
        return lists;
    }
}
