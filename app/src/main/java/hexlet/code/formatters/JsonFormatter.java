package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public final class JsonFormatter {

    private final ObjectMapper mapper = new ObjectMapper();

    public String format(List<Map<String, Object>> diffs) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diffs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
