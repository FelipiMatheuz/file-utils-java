import model.CsvParams;
import model.ExcelParams;
import model.ColumnType;
import model.JsonParams;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class VariablesTest {

    public static CsvParams cpMock = cpMock();
    public static ExcelParams epMock = epMock();

    public static CsvParams csvParams = new CsvParams(System.getProperty("user.home") + File.separator + "Desktop" + File.separator, "CsvTestSimple");
    public static ExcelParams excelParams = new ExcelParams(System.getProperty("user.home") + File.separator + "Desktop" + File.separator, "ExcelTestSimple");
    public static JsonParams jsonParams = new JsonParams(System.getProperty("user.home") + File.separator + "Desktop" + File.separator, "JsonTestSimple");

    private static ExcelParams epMock() {
        ExcelParams excelParams = new ExcelParams(System.getProperty("user.home") + File.separator + "Desktop" + File.separator, "ExcelTest");
        //generate columnTypes (default all String)
        List<ColumnType> columnTypeList = Arrays.asList(ColumnType.INTEGER, ColumnType.STRING, ColumnType.BOOLEAN, ColumnType.DATE, ColumnType.DOUBLE);
        List<List<ColumnType>> matrixList = Arrays.asList(columnTypeList, columnTypeList);
        excelParams.setColumnTypes(matrixList);
        excelParams.setDatePattern("dd/MM/yyyy");
        //generate log files (default null <- no log file)
        //excelParams.setLogDateTime("yyMMddhhmmss");
        return excelParams;
    }

    private static CsvParams cpMock() {
        CsvParams csvParams = new CsvParams(System.getProperty("user.home") + File.separator + "Desktop" + File.separator, "CsvTest");
        //generate columnTypes (default all String)
        List<ColumnType> columnTypeList = Arrays.asList(ColumnType.INTEGER, ColumnType.STRING, ColumnType.BOOLEAN, ColumnType.DATE, ColumnType.DOUBLE);
        List<List<ColumnType>> matrixList = Arrays.asList(columnTypeList, columnTypeList);
        csvParams.setColumnTypes(matrixList);
        csvParams.setDatePattern("dd/MM/yyyy");
        //generate log files (default null <- no log file)
        //csvParams.setLogDateTime("yyyyMMddhhmmss");
        return csvParams;
    }

    public static String jsonMock = "[\n" +
            "   {\n" +
            "      \"id\":1,\n" +
            "      \"name\":\"John Doe\",\n" +
            "      \"verified\":false,\n" +
            "      \"birthday\":\"19/01/2000\",\n" +
            "      \"height\":1.80\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":2,\n" +
            "      \"name\":\"Adam Johnson\",\n" +
            "      \"verified\":false,\n" +
            "      \"birthday\":\"27/05/1981\",\n" +
            "      \"height\":1.95\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":3,\n" +
            "      \"name\":\"Mary Carter\",\n" +
            "      \"verified\":false,\n" +
            "      \"birthday\":\"26/08/1995\",\n" +
            "      \"height\":1.66\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":4,\n" +
            "      \"name\":\"Jack Oliver\",\n" +
            "      \"verified\":true,\n" +
            "      \"birthday\":\"28/11/2001\",\n" +
            "      \"height\":1.72\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":5,\n" +
            "      \"name\":\"Jason Walker\",\n" +
            "      \"verified\":true,\n" +
            "      \"birthday\":\"03/06/2001\",\n" +
            "      \"height\":1.78\n" +
            "   }, \n" +
            "   {\n" +
            "      \"id\":null,\n" +
            "      \"name\":\"\",\n" +
            "      \"verified\":null,\n" +
            "      \"birthday\":\"\",\n" +
            "      \"height\":null\n" +
            "   }\n" +
            "]";
}
