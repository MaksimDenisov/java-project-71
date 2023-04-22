package hexlet.code;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Set;

public final class Diff {

    public static final String UPDATED = "updated";
    public static final String UNCHANGED = "not-change";
    public static final String REMOVED = "removed";
    public static final String ADDED = "added";

    public static final String KEY = "key";
    public static final String TYPE = "type";
    public static final String VALUE = "value";
    public static final String OLD_VALUE = "oldValue";
    public static final String NEW_VALUE = "newValue";

    private final List<Map<String, Object>> diffs = new ArrayList<>();

    public Diff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            Map<String, Object> diff = new HashMap<>();
            diff.put(KEY, key);
            if (data1.containsKey(key) && data2.containsKey(key)) {
                if ((data1.get(key) == null && data2.get(key) != null) || !data1.get(key).equals(data2.get(key))) {
                    diff.put(TYPE, UPDATED);
                    diff.put(OLD_VALUE, data1.get(key));
                    diff.put(NEW_VALUE, data2.get(key));
                } else {
                    diff.put(TYPE, UNCHANGED);
                    diff.put(VALUE, data1.get(key));
                }
            } else {
                if (data1.get(key) != null && data1.containsKey(key)) {
                    diff.put(TYPE, REMOVED);
                    diff.put(VALUE, data1.get(key));
                }
                if (data2.containsKey(key)) {
                    diff.put(TYPE, ADDED);
                    diff.put(VALUE, data2.get(key));
                }
            }
            diffs.add(diff);
        }
    }

    public List<Map<String, Object>> getDiffs() {
        return diffs;
    }
}
