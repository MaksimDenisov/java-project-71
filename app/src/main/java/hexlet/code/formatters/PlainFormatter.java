package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class PlainFormatter {

    public static String format(List<Map<String, Object>> diffs) {
        StringBuilder builder = new StringBuilder();
        diffs.forEach(diff -> builder.append(formatLine(diff)));
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static String formatLine(Map<String, Object> diff) {
        String key = diff.get("key").toString();
        return switch (diff.get("type").toString()) {
            case "updated" -> String.format("Property '%s' was updated. From %s to %s\n",
                    key, getComplexValueAsString(diff.get("value1")), getComplexValueAsString(diff.get("value2")));

            case "added" -> String.format("Property '%s' was added with value: %s\n",
                    key, getComplexValueAsString(diff.get("value")));

            case "removed" -> String.format("Property '%s' was removed\n", key);

            default -> "";
        };
    }

    private static String getComplexValueAsString(Object o) {
        if (o == null) {
            return null;
        }
        if (o.getClass().isArray() || o instanceof List || o instanceof Map) {
            return "[complex value]";
        }
        if (o instanceof String) {
            return String.format("'%s'", o);
        }
        return o.toString();
    }
}
