package converter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.CsvParams;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Csv2Json {

    static Logger logger = Logger.getLogger(Csv2Json.class.getName());

    public static String generate(final CsvParams csvParams) {
        try {
            Pattern pattern = Pattern.compile(String.valueOf(csvParams.getSeparator()));
            BufferedReader in = new BufferedReader(new FileReader(csvParams.getFullPath()));
            String[] keys = pattern.split(in.readLine());
            List<Object> objectList = in.lines().map(line -> {
                List<String> x = Arrays.asList(pattern.split(line, keys.length));
                Map<String, Object> obj = new LinkedHashMap<>();
                if (csvParams.getColumnTypes() == null || csvParams.getColumnTypes().size() <= 0) {
                    //record simple string data
                    for (int i = 0; i < keys.length; i++) {
                        obj.put(keys[i], x.get(i).replace("\"", ""));
                    }
                } else {
                    //record data with different column types
                    for (int i = 0; i < keys.length; i++) {
                        //get string temporary to avoid parsing errors
                        String temp = x.get(i).replace("\"", "");

                        switch (csvParams.getColumnTypes().get(0).get(i)) {
                            case INTEGER:
                                obj.put(keys[i], temp.isEmpty() ? 0 : Integer.parseInt(temp));
                                break;
                            case DOUBLE:
                                obj.put(keys[i], temp.isEmpty() ? 0.0 : Double.parseDouble(temp));
                                break;
                            case BOOLEAN:
                                obj.put(keys[i], Boolean.valueOf(temp));
                                break;
                            case DATE:
                                try {
                                    obj.put(keys[i], new SimpleDateFormat(csvParams.getDatePattern()).parse(temp));
                                } catch (ParseException e) {
                                    obj.put(keys[i], temp);
                                }
                                break;
                            default:
                                obj.put(keys[i], temp);
                        }
                    }
                }
                return obj;
            }).collect(Collectors.toList());
            in.close();

            final ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat(csvParams.getDatePattern()));
            String data = mapper.writeValueAsString(objectList);

            logger.info("JSON created successfully: " + data);
            return data;
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException error. Message: " + e.getMessage());
            return null;
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException error. Message: " + e.getMessage());
            return null;
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException error. Message: " + e.getMessage());
            return null;
        } catch (IOException e) {
            logger.error("IOException error. Message: " + e.getMessage());
            return null;
        }
    }
}
