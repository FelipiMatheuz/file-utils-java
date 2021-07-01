package converter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ExcelParams;

public class Json2Excel {

    static Logger logger = Logger.getLogger(Json2Excel.class.getName());

    public static boolean generate(final String jsonStr, final ExcelParams excelParams) {
        try {
            //create workbook with single sheet
            final Workbook workbook = new XSSFWorkbook();

            //convert JSON string to Java list objects
            final JsonNode jsonNode = new ObjectMapper().readTree(jsonStr);

            //get headers from JSON object
            final List<String> headers = new ArrayList<>();
            final JsonNode firstObject = jsonNode.elements().next();
            firstObject.fieldNames().forEachRemaining(headers::add);

            final Sheet sheet;
            if (excelParams.getSheetName().size() > 0) {
                sheet = workbook.createSheet(excelParams.getSheetName().get(0));
            } else {
                sheet = workbook.createSheet("Sheet");
            }

            //row for Header
            final Row headerRow = sheet.createRow(0);
            for (int col = 0; col < headers.size(); col++) {
                final Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers.get(col));
                //customize header
                if (excelParams.isFreezeHeader()) {
                    Font headerFont = workbook.createFont();
                    headerFont.setBold(true);
                    final CellStyle headerCellStyle = workbook.createCellStyle();
                    headerCellStyle.setFont(headerFont);
                    cell.setCellStyle(headerCellStyle);
                }
            }


            final Iterator<JsonNode> dataIterator = jsonNode.elements();
            int rows = 0;
            for (int r = 1; dataIterator.hasNext(); r++) {
                final Row row = sheet.createRow(r);
                final JsonNode dataNode = dataIterator.next();

                if (excelParams.getColumnTypes() == null) {
                    //record simple string data
                    for (int i = 0; i < dataNode.size(); i++) {
                        row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asText());
                    }
                } else {
                    //record data with different column types
                    for (int i = 0; i < dataNode.size(); i++) {
                        switch (excelParams.getColumnTypes().get(i)) {
                            case 1:
                                row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asInt());
                                break;
                            case 2:
                                row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asDouble());
                                break;
                            case 3:
                                row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asBoolean());
                                break;
                            default:
                                row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asText());
                        }
                    }
                }
                rows = r;
            }
            //filter
            if (excelParams.isHasFilter()) {
                sheet.setAutoFilter(new CellRangeAddress(0, rows, 0, headers.size() - 1));
            }
            //panel freeze
            if (excelParams.isFreezeHeader()) {
                sheet.createFreezePane(0, 1);
            }
            //resize columns to fit data
            if (excelParams.isAutoSizeColumn()) {
                for (int i = 0; i < headers.size(); i++) {
                    sheet.autoSizeColumn(i);
                }
            }
            //write file
            final FileOutputStream fileOut;
            fileOut = new FileOutputStream(excelParams.getFullPath());
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            logger.info("File created successfully: " + excelParams.getFileName());
            return true;
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException error. Message: " + e.getMessage());
            return false;
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException error. Message: " + e.getMessage());
            return false;
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException error. Message: " + e.getMessage());
            return false;
        } catch (IOException e) {
            logger.error("IOException error. Message: " + e.getMessage());
            return false;
        }
    }

    public static boolean generate(final List<String> jsonList, final ExcelParams excelParams) {

        try {
            //create workbook with multiple sheets
            final Workbook workbook = new XSSFWorkbook();
            for (int l = 0; l < jsonList.size(); l++) {
                //convert JSON string to Java list objects
                final JsonNode jsonNode = new ObjectMapper().readTree(jsonList.get(l));

                //get headers from JSON object
                final List<String> headers = new ArrayList<>();
                final JsonNode firstObject = jsonNode.elements().next();
                firstObject.fieldNames().forEachRemaining(headers::add);

                final Sheet sheet;
                if (excelParams.getSheetName().size() > 0 && l < excelParams.getSheetName().size()) {
                    sheet = workbook.createSheet(excelParams.getSheetName().get(l));
                } else {
                    sheet = workbook.createSheet("Sheet" + (l + 1));
                }

                //row for Header
                final Row headerRow = sheet.createRow(0);
                for (int col = 0; col < headers.size(); col++) {
                    final Cell cell = headerRow.createCell(col);
                    cell.setCellValue(headers.get(col));
                    //customize header
                    if (excelParams.isFreezeHeader()) {
                        Font headerFont = workbook.createFont();
                        headerFont.setBold(true);
                        final CellStyle headerCellStyle = workbook.createCellStyle();
                        headerCellStyle.setFont(headerFont);
                        cell.setCellStyle(headerCellStyle);
                    }
                }
                final Iterator<JsonNode> dataIterator = jsonNode.elements();
                int rows = 0;
                for (int r = 1; dataIterator.hasNext(); r++) {
                    final Row row = sheet.createRow(r);
                    final JsonNode dataNode = dataIterator.next();

                    if (excelParams.getColumnTypes() == null) {
                        //record simple string data
                        for (int i = 0; i < dataNode.size(); i++) {
                            row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asText());
                        }
                    } else {
                        //record data with different column types
                        for (int i = 0; i < dataNode.size(); i++) {
                            switch (excelParams.getColumnTypes().get(i)) {
                                case 1:
                                    row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asInt());
                                    break;
                                case 2:
                                    row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asDouble());
                                    break;
                                case 3:
                                    row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asBoolean());
                                    break;
                                default:
                                    row.createCell(i).setCellValue(dataNode.get(headers.get(i)).asText());
                            }
                        }
                    }
                    rows = r;
                }
                //filter
                if (excelParams.isHasFilter()) {
                    sheet.setAutoFilter(new CellRangeAddress(0, rows, 0, headers.size() - 1));
                }
                //panel freeze
                if (excelParams.isFreezeHeader()) {
                    sheet.createFreezePane(0, 1);
                }
                //resize columns to fit data
                if (excelParams.isAutoSizeColumn()) {
                    for (int i = 0; i < headers.size(); i++) {
                        sheet.autoSizeColumn(i);
                    }
                }
            }
            //write file
            final FileOutputStream fileOut;
            fileOut = new FileOutputStream(excelParams.getFullPath());
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            logger.info("File created successfully: " + excelParams.getFileName());
            return true;
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException error. Message: " + e.getMessage());
            return false;
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException error. Message: " + e.getMessage());
            return false;
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException error. Message: " + e.getMessage());
            return false;
        } catch (IOException e) {
            logger.error("IOException error. Message: " + e.getMessage());
            return false;
        }
    }
}
