package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private static final String RESOURCES = "src/test/resources/";

    @BeforeAll
    static void beforeAll() throws IOException {
        expectedPlain = Files.readString(Paths.get(RESOURCES + "expected/plain.txt")
                .toAbsolutePath()
                .normalize());
        expectedStylish = Files.readString(Paths.get(RESOURCES + "expected/stylish.txt")
                .toAbsolutePath()
                .normalize());
        expectedJson = Files.readString(Paths.get(RESOURCES + "expected/json.txt")
                .toAbsolutePath()
                .normalize());
    }

    @Test
    void testGenerateFromJson() throws JsonProcessingException {
        String actualStylish =
                Differ.generate(RESOURCES + "file1.json", RESOURCES + "file2.json", "stylish");
        String actualPlain =
                Differ.generate(RESOURCES + "file1.json", RESOURCES + "file2.json", "plain");
        String actualJson =
                Differ.generate(RESOURCES + "file1.json", RESOURCES + "file2.json", "json");
        assertEquals(expectedStylish, actualStylish);
        assertEquals(expectedPlain, actualPlain);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    void testGenerateFromYaml() throws JsonProcessingException {
        String actualStylish =
                Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml", "stylish");
        String actualPlain =
                Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml", "plain");
        String actualJson =
                Differ.generate(RESOURCES + "file1.yml", RESOURCES + "file2.yml", "json");
        assertEquals(expectedStylish, actualStylish);
        assertEquals(expectedPlain, actualPlain);
        assertEquals(expectedJson, actualJson);
    }
}
