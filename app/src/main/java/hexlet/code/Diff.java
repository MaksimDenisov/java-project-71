package hexlet.code;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class Diff {

    public static List<Map<String, Object>> getDiffs(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> diffs = new ArrayList<>();
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            Map<String, Object> diff = new HashMap<>();
            diff.put("key", key);
            if (data1.containsKey(key) && data2.containsKey(key)) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);
                if (keysEquals(value1, value2)) {
                    diff.put("type", "not-change");
                    diff.put("value", value1);
                } else {
                    diff.put("type", "updated");
                    diff.put("value1", value1);
                    diff.put("value2", value2);
                }
            } else {
                if (data1.get(key) != null && data1.containsKey(key)) {
                    diff.put("type", "removed");
                    diff.put("value", data1.get(key));
                }
                if (data2.containsKey(key)) {
                    diff.put("type", "added");
                    diff.put("value", data2.get(key));
                }
            }
            diffs.add(diff);
        }
        return diffs;
    }

    private static boolean keysEquals(Object key1, Object key2) {
        return (key1 == null && key2 == null) || (key1 != null && key1.equals(key2));
    }
}
