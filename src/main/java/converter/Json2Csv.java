package converter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import utils.FileParams;

public class Json2Csv {

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
            final DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            csvMapper.setDateFormat(df);
            //write data
            csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValue(new File(fp.getFullPath()), objJson);
            return true;
        } catch (final Exception e) {
            System.out.println("Erro ao gerar arquivo CSV: " + e.getMessage());
            return false;
        }
    }
}
