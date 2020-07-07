package org.viper75.uploadexcelfile.helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.viper75.uploadexcelfile.model.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    private static final String FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!file.getContentType().equals(FILE_TYPE))
            return false;

        return true;
    }

    public static List<Contact> excelToContacts(InputStream inputStream) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet("contacts");

            Iterator<Row> rows = sheet.rowIterator();

            List<Contact> contacts = new ArrayList<>();

            int rowIndex = 0;
            while (rows.hasNext()) {
                Row row = rows.next();

                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();

                Contact contact = new Contact();

                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    switch (cellIndex) {
                        case 0:
                            contact.setFirstName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            contact.setLastName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            contact.setPhone(String.valueOf(currentCell.getNumericCellValue()));
                            break;
                        case 3:
                            contact.setRegistered(currentCell.getBooleanCellValue());
                        default:
                            break;
                    }
                    cellIndex++;

                    contacts.add(contact);
                }
            }
            workbook.close();

            return contacts;

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file.");
        }
    }
}
