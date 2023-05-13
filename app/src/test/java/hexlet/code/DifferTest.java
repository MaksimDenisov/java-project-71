package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private static final String RESOURCES = "src/test/resources/";

    @BeforeAll
    static void beforeAll() throws IOException {
        expectedPlain = Files.readString(getExpectedPath("plain.txt"));
        expectedStylish = Files.readString(getExpectedPath("stylish.txt"));
        expectedJson = Files.readString(getExpectedPath("json.txt"));
    }

    private static Path getExpectedPath(String filename) {
        return Paths.get(RESOURCES + "expected/" + filename)
                .toAbsolutePath()
                .normalize();
    }

    @Test
    void testGenerateFromJson() throws Exception {
        String fixture1 = getResourcePath("file1.json");
        String fixture2 = getResourcePath("file2.json");

        String actualStylish = Differ.generate(fixture1, fixture2, "stylish");
        String actualPlain = Differ.generate(fixture1, fixture2, "plain");
        String actualJson = Differ.generate(fixture1, fixture2, "json");

        assertEquals(expectedStylish, actualStylish);
        assertEquals(expectedPlain, actualPlain);
        JSONAssert.assertEquals(expectedJson, actualJson,false);
    }

    @Test
    void testGenerateFromYaml() throws Exception {
        String fixture1 = getResourcePath("file1.yml");
        String fixture2 = getResourcePath("file2.yml");

        String actualStylish = Differ.generate(fixture1, fixture2, "stylish");
        String actualPlain = Differ.generate(fixture1, fixture2, "plain");
        String actualJson = Differ.generate(fixture1, fixture2, "json");

        assertEquals(expectedStylish, actualStylish);
        assertEquals(expectedPlain, actualPlain);
        JSONAssert.assertEquals(expectedJson, actualJson,false);
    }

    private String getResourcePath(String fixtureName) {
        return RESOURCES + fixtureName;
    }
}
