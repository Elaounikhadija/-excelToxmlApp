package awb.conversion.excelToxmlApp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import awb.conversion.excelToxmlApp.Model.Colib;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
public class ExcelUtility {
    public static String TYPE="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
      static String[] HEADERs={"DATE_OPERATION", " MONTANT_BRUT", " MONTANT_TAXE", "NUMERO_DECLARATION"};
      static String SHEET="colib";
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Colib> excelToStuList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Colib> stuList = new ArrayList<Colib>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Colib stu = new Colib();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            stu.setNumero_declaration((int) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            stu.setMontant_taxe(currentCell.getStringCellValue());
                            break;
                        case 2:
                            stu.setMontant_brut(currentCell.getStringCellValue());
                            break;
                        case 3:
                            stu.setDate_operation((Date) currentCell.getDateCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                stuList.add(stu);
            }
            workbook.close();
            return stuList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }





}
