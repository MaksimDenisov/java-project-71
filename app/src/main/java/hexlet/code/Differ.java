package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static final String INDENT = "  ";
    public static final String FORMAT_STRING = "%s: %s\n";
    public static final String NOT_CHANGE_STRING = INDENT + "  " + FORMAT_STRING;
    public static final String ADD_STRING = INDENT + "+ " + FORMAT_STRING;
    public static final String REMOVE_STRING = INDENT + "- " + FORMAT_STRING;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String generate(String filePath1, String filePath2) throws Exception {
        StringBuilder builder = new StringBuilder();
        Map<String, String> json1 = Parser.parse(filePath1);
        Map<String, String> json2 = Parser.parse(filePath2);
        Set<String> keys = new TreeSet<>();
        keys.addAll(json1.keySet());
        keys.addAll(json2.keySet());
        builder.append("{\n");
        for (String key : keys) {
            if (json1.containsKey(key) && json1.containsKey(key) && json1.get(key).equals(json2.get(key))) {
                builder.append(String.format(NOT_CHANGE_STRING, key, json1.get(key)));
            } else {
                if (json1.containsKey(key)) {
                    builder.append(String.format(REMOVE_STRING, key, json1.get(key)));
                }
                if (json2.containsKey(key)) {
                    builder.append(String.format(ADD_STRING, key, json2.get(key)));
                }
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
