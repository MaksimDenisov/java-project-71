package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {

    private static final TypeReference<Map<String, Object>> TYPE_REFERENCE = new TypeReference<>() {
    };

    public static Map<String, Object> parse(String data, String format) throws IOException {
        if ("json".equals(format)) {
            return parseJson(data);
        }
        if ("yaml".equals(format)) {
            return parseYaml(data);
        }
        throw new IllegalArgumentException(String.format("%s unknown format. Supported formats : json, yaml.", format));
    }

    private static Map<String, Object> parseJson(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, TYPE_REFERENCE);
    }

    private static Map<String, Object> parseYaml(String json) throws JsonProcessingException {
        return new YAMLMapper().readValue(json, TYPE_REFERENCE);
    }

}
