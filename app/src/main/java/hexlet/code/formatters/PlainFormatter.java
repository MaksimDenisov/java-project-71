package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlainFormatter implements Formatter.TextFormatter {
    public static final String INDENT = "  ";
    public static final String FORMAT_STRING = "%s: %s\n";
    public static final String NOT_CHANGE_STRING = INDENT + "  " + FORMAT_STRING;
    public static final String ADD_STRING = "Property '%s' was added with value: %s\n";
    public static final String REMOVE_STRING = "Property '%s' was removed\n";
    private static final String UPDATE_STRING = "Property '%s' was updated. From %s to %s\n";

    private static final Map<Diff.TYPE, String> FORMATS = new HashMap<>();

    static {
        FORMATS.put(Diff.TYPE.UNCHANGED, NOT_CHANGE_STRING);
        FORMATS.put(Diff.TYPE.ADDED, ADD_STRING);
        FORMATS.put(Diff.TYPE.REMOVED, REMOVE_STRING);
        FORMATS.put(Diff.TYPE.UPDATED, UPDATE_STRING);
    }

    public String format(List<Diff> diffs) {
        StringBuilder builder = new StringBuilder();
        diffs.forEach(diff -> builder.append(formatLine(diff)));
        return builder.toString();
    }

    private String formatLine(Diff diff) {
        if (diff.getType() == Diff.TYPE.UNCHANGED) {
            return "";
        }
        if (diff.getType() == Diff.TYPE.UPDATED) {
            Diff.Changes pair = (Diff.Changes) diff.getValue();
            return String.format(FORMATS.get(diff.getType()), diff.getKey(),
                    getStringValue(pair.getOldValue()), getStringValue(pair.getNewValue()));
        } else {
            return String.format(FORMATS.get(diff.getType()), diff.getKey(), getStringValue(diff.getValue()));
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
