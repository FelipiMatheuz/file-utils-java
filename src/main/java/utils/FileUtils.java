package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtils {

    //static column types
    public static int STRING = 0;
    public static int INTEGER = 1;
    public static int DOUBLE = 2;
    public static int BOOLEAN = 3;

    //static extension types
    public static String EXCEL = "xlsx";
    public static String CSV = "csv";

    //generate log file
    public static String generateLogFile(final String fileType, final FileParams fp) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fp.getDateTimePattern());
        final LocalDateTime now = LocalDateTime.now();
        return fp.getFileName().replace(" ", fp.getFileSpace()) + fp.getFileSpace() + dtf.format(now) + "." + fileType;
    }
}

