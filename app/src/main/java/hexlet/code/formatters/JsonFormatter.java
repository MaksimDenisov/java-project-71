package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Diff;

import java.util.List;

public final class JsonFormatter  implements Formatter.TextFormatter {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String format(List<Diff> diffs) {
        try {
            return   mapper.writeValueAsString(diffs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
