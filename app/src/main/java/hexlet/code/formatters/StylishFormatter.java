package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StylishFormatter {

    private final Map<String, String> formats = new HashMap<>();

    {
        formats.put(Diff.UNCHANGED, "    %s: %s\n");
        formats.put(Diff.ADDED, "  + %s: %s\n");
        formats.put(Diff.REMOVED, "  - %s: %s\n");
    }

    public String format(List<Map<String, Object>> diffs) {
        StringBuilder builder = new StringBuilder("{\n");
        diffs.forEach(diff -> builder.append(formatLine(diff)));
        return builder.append("}").toString();
    }

    private String formatLine(Map<String, Object> diff) {
        String type = (String) diff.get(Diff.TYPE);
        if (Diff.UPDATED.equals(type)) {
            return String.format(formats.get(Diff.REMOVED), diff.get(Diff.KEY), diff.get(Diff.OLD_VALUE))
                    + String.format(formats.get(Diff.ADDED), diff.get(Diff.KEY), diff.get(Diff.NEW_VALUE));
        } else {
            return String.format(formats.get(type), diff.get(Diff.KEY), diff.get(Diff.VALUE));
        }
    }
}

