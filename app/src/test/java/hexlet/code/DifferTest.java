package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static final String EXPECTED = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
    @Test
    void testGenerateFromJson() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        assertEquals(EXPECTED, actual);
    }

    @Test
    void testGenerateFromYaml() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.yaml", "src/test/resources/file2.yaml");
        assertEquals(EXPECTED, actual);
    }
}
