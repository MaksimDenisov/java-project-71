package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

public class Parser {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

    private static final TypeReference<Map<String, Object>> TYPE_REFERENCE = new TypeReference<>() {
    };

    public static Map<String, Object> parse(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (path.toString().endsWith(".json")) {
            return JSON_MAPPER.readValue(Files.readString(path), TYPE_REFERENCE);
        }
        if (path.toString().endsWith(".yml")) {
            return YAML_MAPPER.readValue(Files.readString(path), TYPE_REFERENCE);
        }
        return Collections.emptyMap();
    }
}
