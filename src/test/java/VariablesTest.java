import utils.FileParams;

import java.io.File;

public class VariablesTest {

    public static FileParams fp = new FileParams(System.getProperty("user.home") + File.separator + "Desktop" + File.separator,"Test Log");
    public static String jsonMock = "[\n" +
            "   {\n" +
            "      \"id\":1,\n" +
            "      \"name\":\"John Doe\",\n" +
            "      \"verified\":false,\n" +
            "      \"age\":19,\n" +
            "      \"height\":1.80\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":2,\n" +
            "      \"name\":\"Adam Johnson\",\n" +
            "      \"verified\":false,\n" +
            "      \"age\":27,\n" +
            "      \"height\":1.95\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":3,\n" +
            "      \"name\":\"Mary Carter\",\n" +
            "      \"verified\":false,\n" +
            "      \"age\":26,\n" +
            "      \"height\":1.66\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":4,\n" +
            "      \"name\":\"Jack Oliver\",\n" +
            "      \"verified\":true,\n" +
            "      \"age\":28,\n" +
            "      \"height\":1.72\n" +
            "   },\n" +
            "   {\n" +
            "      \"id\":5,\n" +
            "      \"name\":\"Jason Walker\",\n" +
            "      \"verified\":true,\n" +
            "      \"age\":36,\n" +
            "      \"height\":1.78\n" +
            "   }, \n" +
            "   {\n" +
            "      \"id\":null,\n" +
            "      \"name\":\"\",\n" +
            "      \"verified\":null,\n" +
            "      \"age\":99,\n" +
            "      \"height\":null\n" +
            "   }\n" +
            "]";
}
