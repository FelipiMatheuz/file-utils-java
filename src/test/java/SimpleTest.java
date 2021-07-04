import converter.Csv2Json;
import converter.Excel2Json;
import converter.Json2Csv;
import converter.Json2Excel;
import model.ExcelParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {

    @Test
    @DisplayName("Test JSON -> Excel Single")
    public void testJson2ExcelSingle() {
        assertTrue(Json2Excel.generate(VariablesTest.jsonMock, VariablesTest.excelParams));
        System.out.println("Excel file generated:\n" + VariablesTest.excelParams.getFileName());
    }

    @Test
    @DisplayName("Test JSON -> Excel Multiple")
    public void testJson2ExcelMultiple() {
        List<String> jsonMocks = new ArrayList<>();
        jsonMocks.add(VariablesTest.jsonMock);
        jsonMocks.add(VariablesTest.jsonMock);
        jsonMocks.add(VariablesTest.jsonMock);
        assertTrue(Json2Excel.generate(jsonMocks, VariablesTest.excelParams));
        System.out.println("Excel file generated:\n" + VariablesTest.excelParams.getFileName());
    }

    @Test
    @DisplayName("Test Excel -> JSON List")
    public void testExcel2Json() {
        List<String> jsonList = Excel2Json.generate(VariablesTest.excelParams);
        assertNotNull(jsonList);
        System.out.println("JSONs generated:");
        for (String json : jsonList) {
            System.out.println(json);
        }
    }

    @Test
    @DisplayName("Test JSON -> CSV")
    public void testJson2Csv() {
        assertTrue(Json2Csv.generate(VariablesTest.jsonMock, VariablesTest.csvParams));
        System.out.println("CSV file generated:\n" + VariablesTest.csvParams.getFileName());
    }

    @Test
    @DisplayName("Test CSV -> JSON")
    public void testCsv2Json() {
        String json = Csv2Json.generate(VariablesTest.csvParams);
        assertNotNull(json);
        System.out.println("JSON generated:\n" + json);
    }
}
