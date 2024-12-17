package adventofcode2024.day02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class Day02Test {

    private File createTemporaryTestFile(String content) throws IOException {
        Path tempFilePath = Files.createTempFile("testReport", ".txt");
        Files.write(tempFilePath, content.getBytes(), StandardOpenOption.WRITE);
        return tempFilePath.toFile();
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels increase by only 3 (the 
     * upper limit for allowed increments).
     */
    @Test
    public void testAllIncreasingBy3() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 4 7 10 13");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(1, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels increase by only 2 (a 
     * value between the upper and lower limit for allowed increments).
     */
    @Test
    public void testAllIncreasingBy2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 3 5 7 9");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(1, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels increase by only 1 (the 
     * lower limit for allowed increments).
     */
    @Test
    public void testAllIncreasingBy1() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2 3 4 5");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(1, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels decrease by only 3 (the 
     * upper limit for allowed increments).
     */
    @Test
    public void testAllDecreasingBy3() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("13 10 7 4 1");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(1, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels decrease by only 2 (a 
     * value between the upper and lower limit for allowed increments).
     */
    @Test
    public void testAllDecreasingBy2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("9 7 5 3 1");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(1, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels decrease by only 1 (the 
     * lower limit for allowed increments).
     */
    @Test
    public void testAllDecreasingBy1() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("5 4 3 2 1");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(1, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with only 1 level
     */
    @Test
    public void testOnly1Level() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(1, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with only 2 increasing levels
     */
    @Test
    public void testOnly2LevelsIncreasing() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(1, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with only 2 decreasing levels
     */
    @Test
    public void testOnly2LevelsDecreasing() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("2 1");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(1, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with only 2 non-changing levels
     */
    @Test
    public void testOnly2LevelsNoChange() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("2 2");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with only 3 non-changing levels
     */
    @Test
    public void testOnly3LevelsNoChange() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("2 2 2");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level increasing within the 
     * allowed range of increments (by 0).
     */
    @Test
    public void testIncreasingWith1By0() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2 3 3 5");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 levels increasing within 
     * the allowed range of increments (by 0).
     */
    @Test
    public void testIncreasingWith2By0() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2 3 3 3 4");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level increasing within the 
     * allowed range of increments (by 4).
     */
    @Test
    public void testIncreasingWith1By4() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2 3 7 5");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 level increasing within 
     * the allowed range of increments (by 4).
     */
    @Test
    public void testIncreasingWith2By4() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 5 3 7 9 11 12");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level decreasing within the 
     * allowed range of increments (by 0).
     */
    @Test
    public void testDecreasingWith1By0() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("8 7 6 6 4");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 levels decreasing within 
     * the allowed range of increments (by 0).
     */
    @Test
    public void testDecreasingWith2By0() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("5 4 3 3 3 2");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level decreasing within the 
     * allowed range of increments (by 4).
     */
    @Test
    public void testDecreasingWith1By4() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("8 7 6 2 4");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but the first 2 levels increasing 
     * within the allowed range of increments.
     */
    @Test
    public void testNoIncreaseWithFirst2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 1 2 3 4");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but the first 3 levels 
     * increasing within the allowed range of increments.
     */
    @Test
    public void testNoIncreaseWithFirst3() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 1 1 2 3 4");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level increasing
     */
    @Test
    public void testAllIncreasingExceptFor1() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 3 5 4 7");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 levels increasing
     */
    @Test
    public void testAllIncreasingExceptFor2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 3 5 4 6 7 8 6 9");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level decreasing
     */
    @Test
    public void testAllDecreasingExceptFor1() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("7 5 3 4 1");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the getReportSafetyCount method with and without the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 levels decreasing
     */
    @Test
    public void testAllDecreasingExceptFor2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("9 10 8 7 6 8 5 4 3");

        // Verify the generated count without Problem Dampener (Part 1)
        assertEquals(0, Day02.getReportSafetyCount(testFile, false));

        // Verify the generated count with Problem Dampener (Part 2)
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }
}
