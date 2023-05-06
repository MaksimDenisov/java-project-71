package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(String formatName, List<Map<String, Object>> diffs) {
        try {
            return switch (formatName) {
                case "stylish" -> StylishFormatter.format(diffs);
                case "plain" -> PlainFormatter.format(diffs);
                case "json" -> JsonFormatter.format(diffs);
                default -> throw new IllegalArgumentException(
                        String.format("\"%s\" is unknown output format. Use stylish, plain, json.", formatName));
            };
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't format file : " + e.getMessage());
        }
    }
}
