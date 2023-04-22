package hexlet.code;


import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String filePath1, String filePath2) {
        return generate(filePath1, filePath2, Formatter.STYLISH);
    }

    public static String generate(String filePath1, String filePath2, String format) {
        return Formatter.format(format, generateDiffs(filePath1, filePath2));
    }

    private static List<Map<String, Object>> generateDiffs(String filePath1, String filePath2) {
        return new Diff(readData(filePath1), readData(filePath2)).getDiffs();
    }

    private static Map<String, Object> readData(String filePath) {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String format = getFormat(path);
        try {
            return Parser.parse(Files.readString(path), format);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't read " + e.getMessage());
        }
    }

    private static String getFormat(Path path) {
        if (path.toString().endsWith(".json")) {
            return "json";
        }
        if (path.toString().endsWith(".yml") || path.toString().endsWith(".yaml")) {
            return "yaml";
        }
        throw new IllegalArgumentException(String.format("\"%s\" is unsupported file. Supported: JSON, YAML ",
                path.getFileName()));
    }
}
