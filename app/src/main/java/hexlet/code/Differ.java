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
        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());
        List<Diff> diffs = new ArrayList<>();
        for (String key : keys) {
            if (data1.containsKey(key) && data2.containsKey(key)) {
                if ((data1.get(key) == null && data2.get(key) != null) || !data1.get(key).equals(data2.get(key))) {
                    diffs.add(Diff.update(key, data1.get(key), data2.get(key)));
                } else {
                    diffs.add(Diff.identity(key, data1.get(key)));
                }
            } else {
                if (data1.get(key) != null && data1.containsKey(key)) {
                    diffs.add(Diff.removed(key, data1.get(key)));
                }
                if (data2.containsKey(key)) {
                    diffs.add(Diff.added(key, data2.get(key)));
                }
            }
        }
        return diffs;
    }
}
