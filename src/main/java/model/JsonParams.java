package model;

public class JsonParams extends FileParams {

    public JsonParams(String filePath, String fileName) {
        super(filePath, fileName);
    }

    @Override
    public String getFileName() {
        return super.getFileName() + getLogDateTime() + Extension.JSON;
    }
}
