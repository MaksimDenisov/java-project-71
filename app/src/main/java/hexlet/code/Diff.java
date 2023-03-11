package hexlet.code;

public final class Diff {

    public enum TYPE {
        IDENTITY,
        UPDATED,
        ADD,
        REMOVE
    }

    public record Pair(Object value1, Object value2) {
        public Object getValue1() {
            return value1;
        }

        public Object getValue2() {
            return value2;
        }
    }

    private final Diff.TYPE type;
    private final String key;
    private final Object value;

    public static Diff identity(String key, Object value) {
        return new Diff(Diff.TYPE.IDENTITY, key, value);
    }

    public static Diff added(String key, Object value) {
        return new Diff(Diff.TYPE.ADD, key, value);
    }

    public static Diff removed(String key, Object value) {
        return new Diff(TYPE.REMOVE, key, value);
    }

    public static Diff update(String key, Object value, Object newValue) {
        return  new Diff(TYPE.UPDATED, key, new Pair(value, newValue));
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
