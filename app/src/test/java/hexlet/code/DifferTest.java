package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static final String EXPECTED = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";
    @Test
    void testGenerateFromJson() throws Exception {
        String actual =
                Formatter.format(Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json"));
        assertEquals(EXPECTED, actual);
    }

    @Test
    void testGenerateFromYaml() throws Exception {
        String actual =
                Formatter.format(Differ.generate("src/test/resources/file1.yaml", "src/test/resources/file2.yaml"));
        assertEquals(EXPECTED, actual);
    }
}
