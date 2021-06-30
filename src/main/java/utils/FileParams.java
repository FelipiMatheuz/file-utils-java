package utils;

import java.util.List;

public class FileParams {

    //default param values
    private String filePath;
    private String fileName;
    private String dateTimePattern = "yyyyMMddhhmmss";
    private List<Integer> columnTypes = null;
    private String fileSpace = "_";
    private ExcelParams excelParams = new ExcelParams();
    private CsvParams csvParams = new CsvParams();

    public FileParams(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.fileName = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Integer> getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(List<Integer> columnTypes) {
        this.columnTypes = columnTypes;
    }

    public String getDateTimePattern() {
        return dateTimePattern;
    }

    public void setDateTimePattern(String dateTimePattern) {
        this.dateTimePattern = dateTimePattern;
    }

    public String getFullPath() {
        return filePath + fileName;
    }

    public String getFileSpace() {
        return fileSpace;
    }

    public void setFileSpace(String fileSpace) {
        this.fileSpace = fileSpace;
    }

    public ExcelParams getExcelParams() {
        return excelParams;
    }

    public void setExcelParams(ExcelParams excelParams) {
        this.excelParams = excelParams;
    }

    public CsvParams getCsvParams() {
        return csvParams;
    }

    public void setCsvParams(CsvParams csvParams) {
        this.csvParams = csvParams;
    }
}
