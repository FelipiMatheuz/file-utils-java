package utils;

public class CsvParams {

    private char separator = ';';
    private String datePattern = "dd/MM/yyyy hh:mm:ss";

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
}
