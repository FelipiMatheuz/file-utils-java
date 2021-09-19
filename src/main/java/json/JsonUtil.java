package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ColumnType;
import model.JsonParams;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    static Logger logger = Logger.getLogger(JsonUtil.class.getName());

    public static boolean export(String jsonStr, JsonParams jsonParams) {
        try {
            //Beautify JSON string
            JsonNode jsonNode = new ObjectMapper().setDateFormat(new SimpleDateFormat(jsonParams.getDatePattern())).readTree(jsonStr);
            String formattedJson = jsonNode.toPrettyString();
            //write a JSON file
            Path path = Paths.get(jsonParams.getFullPath());
            byte[] strToBytes = formattedJson.getBytes();
            Files.write(path, strToBytes);
            return true;
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException error. Message: " + e.getMessage());
            return false;
        } catch (IOException e) {
            logger.error("IOException error. Message: " + e.getMessage());
            return false;
        }
    }

    public static List<ColumnType> generateColumnTypes(String jsonStr, String datePattern) {
        try {
            List<ColumnType> columnTypes = new ArrayList<>();
            //read JSON first object
            final JsonNode firstNode = new ObjectMapper().readTree(jsonStr).elements().next();
            //get fieldNames
            List<String> fieldNames = new ArrayList<>();
            firstNode.fieldNames().forEachRemaining(fieldNames::add);
            //get each item to analyse
            for (String name : fieldNames) {
                String item = firstNode.get(name).asText();
                try {
                    //item is numeric
                    if (item.contains(".")) {
                        Double.parseDouble(item);
                        columnTypes.add(ColumnType.DOUBLE);
                    } else {
                        Integer.parseInt(item);
                        columnTypes.add(ColumnType.INTEGER);
                    }
                } catch (Exception e) {
                    try {
                        //item is a date
                        new SimpleDateFormat(datePattern).parse(item);
                        columnTypes.add(ColumnType.DATE);
                    } catch (Exception e1) {
                        //item is boolean or text
                        if (item.equals("true") || item.equals("false")) {
                            columnTypes.add(ColumnType.BOOLEAN);
                        } else {
                            columnTypes.add(ColumnType.STRING);
                        }
                    }
                }
            }
            return columnTypes;
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException error. Message: " + e.getMessage());
            return null;
        }
    }
}
