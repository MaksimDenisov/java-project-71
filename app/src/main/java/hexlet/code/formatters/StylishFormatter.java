package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class StylishFormatter {

    public static String format(List<Map<String, Object>> diffs) {
        StringBuilder builder = new StringBuilder("{\n");
        diffs.forEach(diff -> builder.append(formatLine(diff)));
        return builder.append("}").toString();
    }

    private static String formatLine(Map<String, Object> diff) {
        String key = diff.get("key").toString();
        return switch (diff.get("type").toString()) {
            case "updated" -> String.format("  - %s: %s\n", key, diff.get("value1"))
                    + String.format("  + %s: %s\n", key, diff.get("value2"));

            case "added" -> String.format("  + %s: %s\n", key, diff.get("value"));

            case "removed" -> String.format("  - %s: %s\n", key, diff.get("value"));

            default -> String.format("    %s: %s\n", key, diff.get("value"));
        };
    }
}

