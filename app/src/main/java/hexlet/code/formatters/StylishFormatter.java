package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StylishFormatter implements Formatter.TextFormatter {
    public static final String INDENT = "  ";
    public static final String FORMAT_STRING = "%s: %s\n";
    public static final String NOT_CHANGE_STRING = INDENT + "  " + FORMAT_STRING;
    public static final String ADD_STRING = INDENT + "+ " + FORMAT_STRING;
    public static final String REMOVE_STRING = INDENT + "- " + FORMAT_STRING;

    private static final Map<Diff.TYPE, String> FORMATS = new HashMap<>();

    static {
        FORMATS.put(Diff.TYPE.UNCHANGED, NOT_CHANGE_STRING);
        FORMATS.put(Diff.TYPE.ADDED, ADD_STRING);
        FORMATS.put(Diff.TYPE.REMOVED, REMOVE_STRING);
    }

    public String format(List<Diff> diffs) {
        StringBuilder builder = new StringBuilder("{\n");
        diffs.forEach(diff -> builder.append(formatLine(diff)));
        return builder.append("}").toString();
    }

    private String formatLine(Diff diff) {
        if (diff.getType() == Diff.TYPE.UPDATED) {
            Diff.Changes pair = (Diff.Changes) diff.getValue();
            return String.format(FORMATS.get(Diff.TYPE.REMOVED), diff.getKey(), pair.getOldValue())
                    + String.format(FORMATS.get(Diff.TYPE.ADDED), diff.getKey(), pair.getNewValue());
        } else {
            return String.format(FORMATS.get(diff.getType()), diff.getKey(), diff.getValue());
        }
    }
}

