package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlainFormatter implements Formatter.TextFormatter {

    private final Map<Diff.TYPE, String> formats = new HashMap<>();

    {
        formats.put(Diff.TYPE.UNCHANGED, "    %s: %s\n");
        formats.put(Diff.TYPE.ADDED, "Property '%s' was added with value: %s\n");
        formats.put(Diff.TYPE.REMOVED, "Property '%s' was removed\n");
        formats.put(Diff.TYPE.UPDATED, "Property '%s' was updated. From %s to %s\n");
    }

    public String format(List<Diff> diffs) {
        StringBuilder builder = new StringBuilder();
        diffs.forEach(diff -> builder.append(formatLine(diff)));
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private String formatLine(Diff diff) {
        if (diff.getType() == Diff.TYPE.UNCHANGED) {
            return "";
        }
        if (diff.getType() == Diff.TYPE.UPDATED) {
            Diff.Changes pair = (Diff.Changes) diff.getValue();
            return String.format(formats.get(diff.getType()), diff.getKey(),
                    getStringValue(pair.getOldValue()), getStringValue(pair.getNewValue()));
        } else {
            return String.format(formats.get(diff.getType()), diff.getKey(), getStringValue(diff.getValue()));
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
