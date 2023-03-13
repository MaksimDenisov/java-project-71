package hexlet.code;


import hexlet.code.formatters.Formatter;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;


public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, Formatter.STYLISH);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        return Formatter.getFormatter(format).format(generateDiffs(filePath1, filePath2));
    }

    private static List<Diff> generateDiffs(String filePath1, String filePath2) throws Exception {
        Map<String, Object> json1 = Parser.parse(filePath1);
        Map<String, Object> json2 = Parser.parse(filePath2);
        Set<String> keys = new TreeSet<>();
        keys.addAll(json1.keySet());
        keys.addAll(json2.keySet());
        List<Diff> diffs = new ArrayList<>();
        for (String key : keys) {
            if (json1.containsKey(key) && json2.containsKey(key)) {
                if ((json1.get(key) == null && json2.get(key) != null) || !json1.get(key).equals(json2.get(key))) {
                    diffs.add(Diff.update(key, json1.get(key), json2.get(key)));
                } else {
                    diffs.add(Diff.identity(key, json1.get(key)));
                }
            } else {
                if (json1.get(key) != null && json1.containsKey(key)) {
                    diffs.add(Diff.removed(key, json1.get(key)));
                }
                if (json2.containsKey(key)) {
                    diffs.add(Diff.added(key, json2.get(key)));
                }
            }
        }
        return diffs;
    }
}
