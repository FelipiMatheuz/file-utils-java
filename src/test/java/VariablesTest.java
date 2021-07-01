import model.CsvParams;
import model.ExcelParams;
import model.FileParams;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class VariablesTest {

    public static CsvParams cpMock = cpMock();
    public static ExcelParams epMock = epMock();

    private static ExcelParams epMock() {
        ExcelParams excelParams = new ExcelParams(System.getProperty("user.home") + File.separator + "Desktop" + File.separator, "ExcelTest");
        //generate columnTypes (default all String)
        List<Integer> intList = Arrays.asList(FileParams.INTEGER, FileParams.STRING, FileParams.BOOLEAN, FileParams.INTEGER, FileParams.DOUBLE);
        excelParams.setColumnTypes(intList);
        //generate log files (default null <- no log file)
        excelParams.setLogDateTime("yyMMddhhmmss");
        return excelParams;
    }

    private static CsvParams cpMock() {
        CsvParams csvParams = new CsvParams(System.getProperty("user.home") + File.separator + "Desktop" + File.separator, "CsvTest");
        //generate columnTypes (default all String)
        List<Integer> intList = Arrays.asList(FileParams.INTEGER, FileParams.STRING, FileParams.BOOLEAN, FileParams.INTEGER, FileParams.DOUBLE);
        csvParams.setColumnTypes(intList);
        //generate log files (default null <- no log file)
        csvParams.setLogDateTime("yyyyMMddhhmmss");
        return csvParams;
    }

    public static String jsonMock = "[\n" +
            "   {\n" +
            "      \"id\":1,\n" +
            "      \"name\":\"John Doe\",\n" +
            "      \"verified\":false,\n" +
            "      \"age\":19,\n" +
            "      \"height\":1.80\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":2,\n" +
            "      \"name\":\"Adam Johnson\",\n" +
            "      \"verified\":false,\n" +
            "      \"age\":27,\n" +
            "      \"height\":1.95\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":3,\n" +
            "      \"name\":\"Mary Carter\",\n" +
            "      \"verified\":false,\n" +
            "      \"age\":26,\n" +
            "      \"height\":1.66\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":4,\n" +
            "      \"name\":\"Jack Oliver\",\n" +
            "      \"verified\":true,\n" +
            "      \"age\":28,\n" +
            "      \"height\":1.72\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":5,\n" +
            "      \"name\":\"Jason Walker\",\n" +
            "      \"verified\":true,\n" +
            "      \"age\":36,\n" +
            "      \"height\":1.78\n" +
            "   }, \n" +
            "   {\n" +
            "      \"id\":null,\n" +
            "      \"name\":\"\",\n" +
            "      \"verified\":null,\n" +
            "      \"age\":99,\n" +
            "      \"height\":null\n" +
            "   }\n" +
            "]";
}
