import converter.Csv2Json;
import converter.Excel2Json;
import converter.Json2Csv;
import converter.Json2Excel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionTest {

    @Test
    @DisplayName("Test Logfile names")
    public void testLogFile() {
        final String fileNameExcel = FileUtils.generateLogFile(FileUtils.EXCEL, VariablesTest.fp);
        final String fileNameCsv = FileUtils.generateLogFile(FileUtils.CSV, VariablesTest.fp);
        assertNotNull(fileNameCsv, fileNameExcel);
        System.out.println("Excel file created: " + fileNameExcel);
        System.out.println("CSV file created: " + fileNameCsv);
    }

    @Test
    @DisplayName("Test JSON -> Excel Single")
    public void testJson2ExcelSingle() {
        init(FileUtils.EXCEL);
        assertTrue(Json2Excel.generate(VariablesTest.jsonMock, VariablesTest.fp));
        System.out.println("Excel file generated:\n" + VariablesTest.fp.getFileName());
    }

    @Test
    @DisplayName("Test JSON -> Excel Multiple")
    public void testJson2ExcelMultiple() {
        init(FileUtils.EXCEL);
        List<String> jsonMocks = new ArrayList<>();
        jsonMocks.add(VariablesTest.jsonMock);
        jsonMocks.add(VariablesTest.jsonMock);
        jsonMocks.add(VariablesTest.jsonMock);
        assertTrue(Json2Excel.generate(jsonMocks, VariablesTest.fp));
        System.out.println("Excel file generated:\n" + VariablesTest.fp.getFileName());
    }

    @Test
    @DisplayName("Test Excel -> JSON List")
    public void testExcel2Json() {
        init(FileUtils.EXCEL);
        List<String> jsonList = Excel2Json.generate(VariablesTest.fp);
        assertNotNull(jsonList);
        System.out.println("JSONs generated:");
        for (String json : jsonList) {
            System.out.println(json);
        }
    }

    @Test
    @DisplayName("Test JSON -> CSV")
    public void testJson2Csv() {
        init(FileUtils.CSV);
        assertTrue(Json2Csv.generate(VariablesTest.jsonMock, VariablesTest.fp));
        System.out.println("CSV file generated:\n" + VariablesTest.fp.getFileName());
    }

    @Test
    @DisplayName("Test CSV -> JSON")
    public void testCsv2Json() {
        init(FileUtils.CSV);
        String json = Csv2Json.generate(VariablesTest.fp);
        assertNotNull(json);
        System.out.println("JSON generated:\n" + json);
    }

    private void init(String extension) {
        VariablesTest.fp.setFileName("Test_File." + extension);
        List<Integer> intList = Arrays.asList(FileUtils.INTEGER, FileUtils.STRING, FileUtils.BOOLEAN, FileUtils.INTEGER, FileUtils.DOUBLE);
        VariablesTest.fp.setColumnTypes(intList);
    }
}
