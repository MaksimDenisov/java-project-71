package hexlet.code;

public final class Diff {

    public enum TYPE {
        UNCHANGED,
        UPDATED,
        ADDED,
        REMOVED
    }

    public record Changes(Object oldValue, Object newValue) {
        public Object getOldValue() {
            return oldValue;
        }

        public Object getNewValue() {
            return newValue;
        }
    }

    private final Diff.TYPE type;
    private final String key;
    private final Object value;

    public static Diff identity(String key, Object value) {
        return new Diff(Diff.TYPE.UNCHANGED, key, value);
    }

    public static Diff added(String key, Object value) {
        return new Diff(Diff.TYPE.ADDED, key, value);
    }

    public static Diff removed(String key, Object value) {
        return new Diff(TYPE.REMOVED, key, value);
    }

    public static Diff update(String key, Object value, Object newValue) {
        return  new Diff(TYPE.UPDATED, key, new Changes(value, newValue));
    }

    private Diff(TYPE type, String key, Object value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public TYPE getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
