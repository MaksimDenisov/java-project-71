package hexlet.code;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

public class Differ {

    public static List<Diff> generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> json1 = Parser.parse(filePath1);
        Map<String, Object> json2 = Parser.parse(filePath2);
        Set<String> keys = new TreeSet<>();
        keys.addAll(json1.keySet());
        keys.addAll(json2.keySet());
        List<Diff> diffs = new ArrayList<>();
        for (String key : keys) {
            if (json1.containsKey(key) && json1.containsKey(key) && isIdentity(json1.get(key), (json2.get(key)))) {
                diffs.add(Diff.identity(key, json1.get(key)));
            } else {
                if (json1.containsKey(key)) {
                    diffs.add(Diff.removed(key, json1.get(key)));
                }
                if (json2.containsKey(key)) {
                    diffs.add(Diff.added(key, json2.get(key)));
                }
            }
        }
        return diffs;
    }

    private static boolean isIdentity(Object o1, Object o2) {
        if (o1 != null) {
            return o1.equals(o2);
        } else {
            return (o2 == null);
        }
    }
}
