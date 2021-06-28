package converter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.FileParams;

public class Excel2Json {

    public static String generate(final FileParams fp) {

        try {
            //read Excel file into Java list objects
            final FileInputStream excelFile = new FileInputStream(fp.getFullPath());
            final Workbook workbook = new XSSFWorkbook(excelFile);
            //get target sheet
            final Sheet sheet = workbook.getSheet(fp.getExcelParams().getSheetName());
            final Iterator<Row> rows = sheet.iterator();
            final List<Object> data = new ArrayList<>();
            boolean firstRow = true;
            final List<String> headers = new ArrayList<>();
            while (rows.hasNext()) {
                final Row currentRow = rows.next();
                //get headers
                if (firstRow) {
                    for (int i = 0; i < currentRow.getLastCellNum(); i++) {
                        headers.add(currentRow.getCell(i).getStringCellValue());
                    }
                    firstRow = false;
                    continue;
                }
                Map<String, Object> dataRow = new LinkedHashMap<>();
                if (fp.getColumnTypes() == null) {
                    //record simple string data
                    for (int i = 0; i < currentRow.getLastCellNum(); i++) {
                        if (currentRow.getCell(i).getCellTypeEnum() == CellType.NUMERIC) {
                            dataRow.put(headers.get(i), String.valueOf(currentRow.getCell(i).getNumericCellValue()));
                        } else if (currentRow.getCell(i).getCellTypeEnum() == CellType.BOOLEAN) {
                            dataRow.put(headers.get(i), currentRow.getCell(i).getBooleanCellValue() ? "true" : "false");
                        } else {
                            dataRow.put(headers.get(i), currentRow.getCell(i).getStringCellValue());
                        }
                    }
                } else {
                    //record data with different column types
                    for (int i = 0; i < currentRow.getLastCellNum(); i++) {
                        switch (fp.getColumnTypes().get(i)) {
                            case 1:
                                dataRow.put(headers.get(i), (int) currentRow.getCell(i).getNumericCellValue());
                                break;
                            case 2:
                                dataRow.put(headers.get(i), currentRow.getCell(i).getNumericCellValue());
                                break;
                            case 3:
                                dataRow.put(headers.get(i), currentRow.getCell(i).getBooleanCellValue());
                                break;
                            default:
                                dataRow.put(headers.get(i), currentRow.getCell(i).getStringCellValue());
                        }
                    }
                }
                data.add(dataRow);
            }
            //close workBook
            workbook.close();
            //convert Java objects to JSON string
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(data);
        } catch (final JsonProcessingException e) {
            System.out.println("JsonProcessingException error. Message: " + e.getMessage());
            return null;
        } catch (final IOException e) {
            System.out.println("IOException error. Message: " + e.getMessage());
            return null;
        }
    }
}

