package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlainFormatter {

    private final Map<String, String> formats = new HashMap<>();

    {
        formats.put(Diff.UNCHANGED, "    %s: %s\n");
        formats.put(Diff.ADDED, "Property '%s' was added with value: %s\n");
        formats.put(Diff.REMOVED, "Property '%s' was removed\n");
        formats.put(Diff.UPDATED, "Property '%s' was updated. From %s to %s\n");
    }

    public String format(List<Map<String, Object>> diffs) {
        StringBuilder builder = new StringBuilder();
        diffs.forEach(diff -> builder.append(formatLine(diff)));
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private String formatLine(Map<String, Object> diff) {
        String type = (String) diff.get("type");
        String key = (String) diff.get(Diff.KEY);
        if (Diff.UNCHANGED.equals(type)) {
            return "";
        }
        if (Diff.UPDATED.equals(type)) {
            return String.format(formats.get(type), key,
                    getStringValue(diff.get(Diff.OLD_VALUE)), getStringValue(diff.get(Diff.NEW_VALUE)));
        } else {
            return String.format(formats.get(type), key, getStringValue(diff.get(Diff.VALUE)));
        }
    }

    private String getStringValue(Object o) {
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
