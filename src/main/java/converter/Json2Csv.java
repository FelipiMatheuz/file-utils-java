package converter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import org.apache.log4j.Logger;
import utils.FileParams;

public class Json2Csv {

    static Logger logger = Logger.getLogger(Json2Csv.class.getName());

    public static boolean generate(final String jsonStr, final FileParams fp) {
        try {
            //read JSON string into JSON object
            final JsonNode objJson = new ObjectMapper().readTree(jsonStr);
            final Builder csvBuilder = CsvSchema.builder();
            //read JSON column names (first element)
            final JsonNode firstObject = objJson.elements().next();
            firstObject.fieldNames().forEachRemaining(csvBuilder::addColumn);
            //create csv model
            final CsvSchema csvSchema = csvBuilder.build().withHeader().withColumnSeparator(fp.getCsvParams().getSeparator());
            //map JSON object to CSV
            final CsvMapper csvMapper = new CsvMapper();
            final DateFormat df = new SimpleDateFormat(fp.getCsvParams().getDatePattern());
            csvMapper.setDateFormat(df);
            //write data
            csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValue(new File(fp.getFullPath()), objJson);
            return true;
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException error. Message: " + e.getMessage());
            return false;
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException error. Message: " + e.getMessage());
            return false;
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException error. Message: " + e.getMessage());
            return false;
        } catch (IOException e) {
            logger.error("IOException error. Message: " + e.getMessage());
            return false;
        }
    }
}
