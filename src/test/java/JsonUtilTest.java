import json.JsonUtil;
import model.ColumnType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonUtilTest {

    @Test
    @DisplayName("Test JSON Export Pretty Document")
    public void testJsonExport() {
        assertTrue(JsonUtil.export(VariablesTest.jsonMock, VariablesTest.jsonParams));
        System.out.println("JSON file generated:\n" + VariablesTest.jsonParams.getFileName());
    }

    @Test
    @DisplayName("Test Generate ColumnTypeList from a JSON String")
    public void testGenerateColumnTypes() {
        List<ColumnType> columnTypes = JsonUtil.generateColumnTypes(VariablesTest.jsonMock, "dd/MM/yyyy");
        assertNotNull(columnTypes);
        System.out.println("ColumnType created :");
        for (ColumnType ct : columnTypes) {
            System.out.print(ct.toString() + " ");
        }
    }
}
