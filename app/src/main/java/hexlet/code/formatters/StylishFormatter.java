package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StylishFormatter implements Formatter.TextFormatter {

    private final Map<Diff.TYPE, String> formats = new HashMap<>();

    {
        formats.put(Diff.TYPE.UNCHANGED, "    %s: %s\n");
        formats.put(Diff.TYPE.ADDED, "  + %s: %s\n");
        formats.put(Diff.TYPE.REMOVED, "  - %s: %s\n");
    }

    public String format(List<Diff> diffs) {
        StringBuilder builder = new StringBuilder("{\n");
        diffs.forEach(diff -> builder.append(formatLine(diff)));
        return builder.append("}").toString();
    }

    private String formatLine(Diff diff) {
        if (diff.getType() == Diff.TYPE.UPDATED) {
            Diff.Changes pair = (Diff.Changes) diff.getValue();
            return String.format(formats.get(Diff.TYPE.REMOVED), diff.getKey(), pair.getOldValue())
                    + String.format(formats.get(Diff.TYPE.ADDED), diff.getKey(), pair.getNewValue());
        } else {
            return String.format(formats.get(diff.getType()), diff.getKey(), diff.getValue());
        }
    }
}

