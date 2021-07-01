package model;

public class CsvParams extends FileParams {

    private char separator = ';';
    private String datePattern = "dd/MM/yyyy hh:mm:ss";

    public CsvParams(String filePath, String fileName) {
        super(filePath, fileName);
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public String getFileName() {
        return super.getFileName() + getLogDateTime() + FileParams.CSV;
    }
}
