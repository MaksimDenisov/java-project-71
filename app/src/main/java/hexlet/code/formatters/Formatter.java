package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON = "json";

    public static String format(String formatName, List<Map<String, Object>> diffs) {
        if (STYLISH.equals(formatName)) {
            return new StylishFormatter().format(diffs);
        } else if (PLAIN.equals(formatName)) {
            return new PlainFormatter().format(diffs);
        } else if (JSON.equals(formatName)) {
            return new JsonFormatter().format(diffs);
        }
        throw new IllegalArgumentException(String.format("\"%s\" is unknown output format. Use %s, %s, %s.",
               formatName, STYLISH, PLAIN, JSON));
    }
}

