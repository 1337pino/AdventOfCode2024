package adventofcode2024.day02;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Day02Test {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream pStream;

    @BeforeEach
    public void setUp() {
        pStream = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(pStream);
    }

    private File createTemporaryTestFile(String content) throws IOException {
        Path tempFilePath = Files.createTempFile("testReport", ".txt");
        Files.write(tempFilePath, content.getBytes(), StandardOpenOption.WRITE);
        return tempFilePath.toFile();
    }

    /**
     * Test the generateReportSafetyCount method with a valid input file containing a mix of safe and unsafe reports. 
     * This verifies that the method correctly identifies and counts the number of safe reports.
     */
    @Test
    public void testPart1SafetyCount() throws Exception {
        // Create a temporary file with the test input
        File testFile = new File(System.getProperty("user.dir")
            + "\\src\\test\\resources\\day02\\part1.txt");

        // Verify the generated count
        assertEquals(9, Day02.getReportSafetyCount(testFile, false));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels increase by only 3 (the 
     * upper limit for allowed increments).
     */
    @Test
    public void testPart2AllIncreasingBy3() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 4 7 10 13");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels increase by only 2 (a 
     * value between the upper and lower limit for allowed increments).
     */
    @Test
    public void testPart2AllIncreasingBy2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 3 5 7 9");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels increase by only 1 (the 
     * lower limit for allowed increments).
     */
    @Test
    public void testPart2AllIncreasingBy1() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2 3 4 5");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels decrease by only 3 (the 
     * upper limit for allowed increments).
     */
    @Test
    public void testPart2AllDecreasingBy3() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("13 10 7 4 1");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels decrease by only 2 (a 
     * value between the upper and lower limit for allowed increments).
     */
    @Test
    public void testPart2AllDecreasingBy2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("9 7 5 3 1");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe while the levels decrease by only 1 (the 
     * lower limit for allowed increments).
     */
    @Test
    public void testPart2AllDecreasingBy1() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("5 4 3 2 1");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with only 1 level
     */
    @Test
    public void testPart2Only1Level() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with only 2 increasing levels
     */
    @Test
    public void testPart2Only2LevelsIncreasing() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with only 2 decreasing levels
     */
    @Test
    public void testPart2Only2LevelsDecreasing() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("2 1");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with only 2 non-changing levels
     */
    @Test
    public void testPart2Only2LevelsNoChange() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("2 2");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with only 3 non-changing levels
     */
    @Test
    public void testPart2Only3LevelsNoChange() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("2 2 2");

        // Verify the generated count
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level increasing within the 
     * allowed range of increments (by 0).
     */
    @Test
    public void testPart2IncreasingWith1By0() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2 3 3 5");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 levels increasing within 
     * the allowed range of increments (by 0).
     */
    @Test
    public void testPart2IncreasingWith2By0() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2 3 3 3 4");

        // Verify the generated count
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level increasing within the 
     * allowed range of increments (by 4).
     */
    @Test
    public void testPart2IncreasingWith1By4() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 2 3 7 5");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 level increasing within 
     * the allowed range of increments (by 4).
     */
    @Test
    public void testPart2IncreasingWith2By4() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 5 3 7 9 11 12");

        // Verify the generated count
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level decreasing within the 
     * allowed range of increments (by 0).
     */
    @Test
    public void testPart2DecreasingWith1By0() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("8 7 6 6 4");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 levels decreasing within 
     * the allowed range of increments (by 0).
     */
    @Test
    public void testPart2DecreasingWith2By0() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("5 4 3 3 3 2");

        // Verify the generated count
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level decreasing within the 
     * allowed range of increments (by 4).
     */
    @Test
    public void testPart2DecreasingWith1By4() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("8 7 6 2 4");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but the first 2 levels increasing 
     * within the allowed range of increments.
     */
    @Test
    public void testPart2NoIncreaseWithFirst2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 1 2 3 4");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but the first 3 levels 
     * increasing within the allowed range of increments.
     */
    @Test
    public void testPart2NoIncreaseWithFirst3() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 1 1 2 3 4");

        // Verify the generated count
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level increasing
     */
    @Test
    public void testPart2AllIncreasingExceptFor1() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 3 5 4 7");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 levels increasing
     */
    @Test
    public void testPart2AllIncreasingExceptFor2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("1 3 5 4 6 7 8 6 9");

        // Verify the generated count
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is safe with all but 1 level decreasing
     */
    @Test
    public void testPart2AllDecreasingExceptFor1() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("7 5 3 4 1");

        // Verify the generated count
        assertEquals(1, Day02.getReportSafetyCount(testFile, true));
    }

    /**
     * Test the generateReportSafetyCount method using the Problem Dampener to ignore 1 of the levels. 
     * This verifies that the method correctly identifies the report is unsafe with all but 2 levels decreasing
     */
    @Test
    public void testPart2AllDecreasingExceptFor2() throws Exception {
        // Create a temporary file with the test input
        File testFile = createTemporaryTestFile("9 10 8 7 6 8 5 4 3");

        // Verify the generated count
        assertEquals(0, Day02.getReportSafetyCount(testFile, true));
    }
}
