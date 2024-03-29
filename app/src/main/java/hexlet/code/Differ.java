package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        List<Map<String, Object>> diffs = Diff.getDiffs(parseFile(filePath1), parseFile(filePath2));
        return Formatter.format(format, diffs);
    }

    private static Map<String, Object> parseFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        return Parser.parse(Files.readString(path), getFileExtension(path.toString()));
    }

    private static String getFileExtension(String filename) {
        return (filename.contains(".")) ? filename.substring(filename.lastIndexOf(".") + 1).toLowerCase() : "";
    }
}
