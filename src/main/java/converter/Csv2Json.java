package converter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.FileParams;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Csv2Json {
    public static String generate(final FileParams fp) {
        try {
            Pattern pattern = Pattern.compile(String.valueOf(fp.getCsvParams().getSeparator()));
            BufferedReader in = new BufferedReader(new FileReader(fp.getFullPath()));
            String[] keys = pattern.split(in.readLine());
            List<Object> objectList = in.lines().map(line -> {
                List<String> x = Arrays.asList(pattern.split(line, keys.length));
                Map<String, Object> obj = new LinkedHashMap<>();
                if (fp.getColumnTypes() == null) {
                    //record simple string data
                    for (int i = 0; i < keys.length; i++) {
                        obj.put(keys[i], x.get(i).replace("\"", ""));
                    }
                } else {
                    //record data with different column types
                    for (int i = 0; i < keys.length; i++) {
                        //get string temporary to avoid parsing errors
                        String temp = x.get(i).replace("\"", "");
                        switch (fp.getColumnTypes().get(i)) {
                            case 1:
                                obj.put(keys[i], temp.isEmpty() ? 0 : Integer.parseInt(temp));
                                break;
                            case 2:
                                obj.put(keys[i], temp.isEmpty() ? 0.0 : Double.parseDouble(temp));
                                break;
                            case 3:
                                obj.put(keys[i], Boolean.valueOf(temp));
                                break;
                            default:
                                obj.put(keys[i], temp);
                        }
                    }
                }
                return obj;
            }).collect(Collectors.toList());
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(objectList);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException error. Message: " + e.getMessage());
            return null;
        } catch (JsonMappingException e) {
            System.out.println("JsonMappingException error. Message: " + e.getMessage());
            return null;
        } catch (JsonGenerationException e) {
            System.out.println("JsonGenerationException error. Message: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("IOException error. Message: " + e.getMessage());
            return null;
        }
    }
}
