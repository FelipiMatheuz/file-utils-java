package converter;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ExcelParams;

public class Excel2Json {

    static Logger logger = Logger.getLogger(Json2Excel.class.getName());

    public static List<String> generate(final ExcelParams excelParams) {

        try {
            //read Excel file into Java list objects
            final FileInputStream excelFile = new FileInputStream(excelParams.getFullPath());
            final Workbook workbook = new XSSFWorkbook(excelFile);
            List<String> dataList = new ArrayList<>();
            //get target sheets
            for (int l = 0; l < workbook.getNumberOfSheets(); l++) {
                final Sheet sheet = workbook.getSheetAt(l);
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
                    if (excelParams.getColumnTypes() == null || excelParams.getColumnTypes().size() <= l) {
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
                            switch (excelParams.getColumnTypes().get(l).get(i)) {
                                case INTEGER:
                                    dataRow.put(headers.get(i), (int) currentRow.getCell(i).getNumericCellValue());
                                    break;
                                case DOUBLE:
                                    dataRow.put(headers.get(i), currentRow.getCell(i).getNumericCellValue());
                                    break;
                                case BOOLEAN:
                                    dataRow.put(headers.get(i), currentRow.getCell(i).getBooleanCellValue());
                                    break;
                                case DATE:
                                    try {
                                        dataRow.put(headers.get(i), currentRow.getCell(i).getDateCellValue());
                                    } catch (IllegalStateException e){
                                        dataRow.put(headers.get(i), null);
                                    }
                                    break;
                                default:
                                    dataRow.put(headers.get(i), currentRow.getCell(i).getStringCellValue());
                            }
                        }
                    }
                    data.add(dataRow);
                }
                //convert Java objects to JSON string
                final ObjectMapper mapper = new ObjectMapper();
                mapper.setDateFormat(new SimpleDateFormat(excelParams.getDatePattern()));
                dataList.add(mapper.writeValueAsString(data));
            }
            //close workBook
            workbook.close();

            logger.info("JSON list created successfully: Number of records - " + dataList.size());
            return dataList;
        } catch (final JsonProcessingException e) {
            logger.error("JsonProcessingException error. Message: " + e.getMessage());
            return null;
        } catch (final IOException e) {
            logger.error("IOException error. Message: " + e.getMessage());
            return null;
        }
    }
}

