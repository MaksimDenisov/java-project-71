package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();
    private static TypeReference<HashMap<String, String>> typeRef
            = new TypeReference<HashMap<String, String>>() {
                };

    public static Map<String, String> parse(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (path.toString().endsWith(".json")) {
            return parseJson(path);
        }
        if (path.toString().endsWith(".yaml")) {
            return parseYaml(path);
        }
        return Collections.emptyMap();
    }

    private static Map<String, String> parseYaml(Path path) throws IOException {
        return YAML_MAPPER.readValue(Files.readString(path), typeRef);
    }

    private static Map<String, String> parseJson(Path path) throws IOException {
        return JSON_MAPPER.readValue(Files.readString(path), typeRef);
    }
}
