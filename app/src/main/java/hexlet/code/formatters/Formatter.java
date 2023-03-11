package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";

    public interface TextFormatter {
        String format(List<Diff> diffs);
    }

    private static final  Map<String, TextFormatter> FORMATTERS = new HashMap<>();

    static {
        FORMATTERS.put(STYLISH, new StylishFormatter());
        FORMATTERS.put(PLAIN, new PlainFormatter());
    }

    public static TextFormatter getFormatter(String formatName) {
        return FORMATTERS.getOrDefault(formatName, FORMATTERS.get(STYLISH));
    }
}
