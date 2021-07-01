package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

class FileParams {

    //default param values
    private String filePath;
    private String fileName;
    private String logDateTime = null;
    private List<List<ColumnType>> columnTypes = null;

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

    public List<List<ColumnType>> getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(List<List<ColumnType>> columnTypes) {
        this.columnTypes = columnTypes;
    }

    public String getLogDateTime() {
        if (logDateTime != null) {
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(logDateTime);
            final LocalDateTime now = LocalDateTime.now();
            return dtf.format(now);
        } else {
            return "";
        }
    }

    public void setLogDateTime(String logDateTime) {
        this.logDateTime = logDateTime;
    }

    public String getFullPath() {
        return getFilePath() + getFileName();
    }
}
