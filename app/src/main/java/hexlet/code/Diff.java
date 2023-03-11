package hexlet.code;

public final class Diff {

    enum TYPE {
        IDENTITY,
        ADD,
        REMOVE
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
