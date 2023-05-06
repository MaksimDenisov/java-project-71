package hexlet.code;


import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String filePath1, String filePath2) throws JsonProcessingException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws JsonProcessingException {
        List<Map<String, Object>> diffs = Diff.getDiffs(parseFile(filePath1), parseFile(filePath2));
        return Formatter.format(format, diffs);
    }

    private static Map<String, Object> parseFile(String filePath) {
        try {
            Path path = Paths.get(filePath).toAbsolutePath().normalize();
            return Parser.parse(Files.readString(path), getFileFormat(path));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't read " + e.getMessage());
        }
    }

    private static String getFileFormat(Path path) {
        if (path.toString().endsWith(".json")) {
            return "json";
        }
        if (path.toString().endsWith(".yml") || path.toString().endsWith(".yaml")) {
            return "yaml";
        }
        throw new IllegalArgumentException(String.format("\"%s\" is unsupported file. Supported: JSON, YAML.",
                path.getFileName()));
    }
}
