package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String INDENT = "  ";
    public static final String FORMAT_STRING = "%s: %s\n";
    public static final String NOT_CHANGE_STRING = INDENT + "  " + FORMAT_STRING;
    public static final String ADD_STRING = INDENT + "+ " + FORMAT_STRING;
    public static final String REMOVE_STRING = INDENT + "- " + FORMAT_STRING;

    private static final Map<Diff.TYPE, String> FORMATS = new HashMap<>();

    static {
        FORMATS.put(Diff.TYPE.IDENTITY, NOT_CHANGE_STRING);
        FORMATS.put(Diff.TYPE.ADD, ADD_STRING);
        FORMATS.put(Diff.TYPE.REMOVE, REMOVE_STRING);
    }

    public static String format(List<Diff> diffs) {
        StringBuilder builder = new StringBuilder("{\n");
        diffs.forEach(diff ->
                builder.append(String.format(FORMATS.get(diff.getType()), diff.getKey(), diff.getValue())));
        return builder.append("}").toString();
    }
}

