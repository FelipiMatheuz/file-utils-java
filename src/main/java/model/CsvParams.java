package model;

public class CsvParams extends FileParams {

    private char separator = ';';

    public CsvParams(String filePath, String fileName) {
        super(filePath, fileName);
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    @Override
    public String getFileName() {
        return super.getFileName() + getLogDateTime() + Extension.CSV;
    }
}
