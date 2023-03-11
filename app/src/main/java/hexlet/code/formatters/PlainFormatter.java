package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*private static final String EXPECTED_PLAIN = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'
            """;*/

public class PlainFormatter implements Formatter.TextFormatter {
    public static final String INDENT = "  ";
    public static final String FORMAT_STRING = "%s: %s\n";
    public static final String NOT_CHANGE_STRING = INDENT + "  " + FORMAT_STRING;
    public static final String ADD_STRING = "Property '%s' was added with value: %s\n";
    public static final String REMOVE_STRING = "Property '%s' was removed\n";
    private static final String UPDATE_STRING = "Property '%s' was updated. From %s to %s\n";

    private static final Map<Diff.TYPE, String> FORMATS = new HashMap<>();

    static {
        FORMATS.put(Diff.TYPE.IDENTITY, NOT_CHANGE_STRING);
        FORMATS.put(Diff.TYPE.ADD, ADD_STRING);
        FORMATS.put(Diff.TYPE.REMOVE, REMOVE_STRING);
        FORMATS.put(Diff.TYPE.UPDATED, UPDATE_STRING);
    }

    public String format(List<Diff> diffs) {
        StringBuilder builder = new StringBuilder();
        diffs.forEach(diff -> builder.append(formatLine(diff)));
        return builder.toString();
    }

    private String formatLine(Diff diff) {
        if (diff.getType() == Diff.TYPE.IDENTITY) {
            return "";
        }
        if (diff.getType() == Diff.TYPE.UPDATED) {
            Diff.Pair pair = (Diff.Pair) diff.getValue();
            return String.format(FORMATS.get(diff.getType()), diff.getKey(),
                    getStringValue(pair.getValue1()), getStringValue(pair.getValue2()));
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
