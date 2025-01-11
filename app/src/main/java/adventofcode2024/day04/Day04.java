package adventofcode2024.day04;

/* Day 4: Ceres Search (https://adventofcode.com/2024/day/4) */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

 
 public class Day04 {
     public static void main(String[] args) {
        // Declarations
        File puzzleFile = new File(System.getProperty("user.dir") 
            + "\\app\\src\\main\\resources\\day04\\input.txt");

        System.out.println("---------- DAY 4 2024 ----------");

        System.out.println("------------ Part 1 ------------");
        System.out.println("Count: " + getWordSearchCount(puzzleFile, "XMAS"));
        
        System.out.println("\n------------ Part 2 ------------");
        System.out.println("Count: " + getWordSearchXMASCount(puzzleFile));
    }

    /**
     * Searches the Wordseach puzzle and counts how many times a given word appears.
     * @param wordsearchFile text file containing the Wordsearch puzzle
     * @param word word hidden in the puzzle
     * @return the number of times the word appears in the puzzle
     */
    public static long getWordSearchCount(File wordsearchFile, String word) {
        int count = 0;
        ArrayList<String> wordsearchPuzzle = new ArrayList<>();

        // Import the Wordsearch Puzzle from the input file
        try (Scanner wordsearchScanner = new Scanner(wordsearchFile)) {
            while (wordsearchScanner.hasNextLine()) {
                String wordsearchRow = wordsearchScanner.nextLine();
                wordsearchPuzzle.add(wordsearchRow);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < wordsearchPuzzle.size(); i++) {
            for (int j = 0; j < wordsearchPuzzle.get(i).length(); j++) {
                if ((wordsearchPuzzle.get(i).charAt(j)) == word.charAt(0)) {
                    count += countHorizontalAppearances(wordsearchPuzzle.get(i), word, j);
                    count += countVerticalAppearances(wordsearchPuzzle, word, i, j);
                    count += countDiagonalAppearances(wordsearchPuzzle, word, i, j);
                }
            }
        }

        return count;
    }

    /**
     * Scans a row from a Wordsearch puzzle to see if the word appears forward or backwards from the given index.
     * @param wordsearchRow row from the Wordsearch puzzle
     * @param word word hidden in the puzzle
     * @param rootIndex index for where the local search for the word begins
     * @return the number of times (0-2) that the word appears from the given spot in the row.
     */
    private static int countHorizontalAppearances(String wordsearchRow, String word, int rootIndex) {
        int count = 0;
        
        // Check forward
        boolean wordMatches = true;
        for (int i = rootIndex, j = 0; i < rootIndex + word.length(); i++, j++) {
            if ((i >= wordsearchRow.length()) || (wordsearchRow.charAt(i)) != word.charAt(j)) {
                wordMatches = false;
                break;
            }
        }
        if (wordMatches) count++;

        // Check backward
        wordMatches = true;
        for (int i = rootIndex, j = 0; i > rootIndex - word.length(); i--, j++) {
            if ((i < 0) || (wordsearchRow.charAt(i)) != word.charAt(j)) {
                wordMatches = false;
                break;
            }
        }
        if (wordMatches) count++;

        return count;
    }

    /**
     * Scans a column from a Wordsearch puzzle to see if the word appears upward or downward from the given index.
     * @param wordsearchPuzzle the Wordsearch puzzle
     * @param word word hidden in the puzzle
     * @param rootRow index for the row where the local search for the word begins
     * @param rootColumn index for the column where the local search for the word begins
     * @return the number of times (0-2) that the word appears from the given spot in the column.
     */
    private static int countVerticalAppearances(ArrayList<String> wordsearchPuzzle, String word, int rootRow, 
                                                    int rootColumn) {
        int count = 0;

        // Check downwards
        boolean wordMatches = true;
        for (int i = rootRow, j = 0; i < rootRow + word.length(); i++, j++) {
            if ((i >= wordsearchPuzzle.size()) || (wordsearchPuzzle.get(i).charAt(rootColumn)) != word.charAt(j)) {
                wordMatches = false;
                break;
            }
        }
        if (wordMatches) count++;

        // Check upwards
        wordMatches = true;
        for (int i = rootRow, j = 0; i > rootRow - word.length(); i--, j++) {
            if ((i < 0) || (wordsearchPuzzle.get(i).charAt(rootColumn)) != word.charAt(j)) {
                wordMatches = false;
                break;
            }
        }
        if (wordMatches) count++;

        return count;
    }

    /**
     * Scans diagonally from the given index in a Wordsearch puzzle to see if the word appears.
     * @param wordsearchPuzzle the Wordsearch puzzle
     * @param word word hidden in the puzzle
     * @param rootRow index for the row where the local search for the word begins
     * @param rootColumn index for the column where the local search for the word begins
     * @return the number of times (0-2) that the word appears from the given spot in the column.
     */
    private static int countDiagonalAppearances(ArrayList<String> wordsearchPuzzle, String word, int rootRow, 
                                                    int rootColumn) {
        int count = 0;

        // Check downward-right diagonal
        boolean wordMatches = true;
        for (int i = rootRow, j = rootColumn, k = 0; i < rootRow + word.length(); i++, j++, k++) {
            if ((i >= wordsearchPuzzle.size()) || (j >= wordsearchPuzzle.get(i).length()) 
                    || (wordsearchPuzzle.get(i).charAt(j)) != word.charAt(k)) {
                wordMatches = false;
                break;
            }
        }
        if (wordMatches) count++;

        // Check downward-left diagonal
        wordMatches = true;
        for (int i = rootRow, j = rootColumn, k = 0; i < rootRow + word.length(); i++, j--, k++) {
            if ((i >= wordsearchPuzzle.size()) || (j < 0) || (wordsearchPuzzle.get(i).charAt(j)) != word.charAt(k)) {
                wordMatches = false;
                break;
            }
        }
        if (wordMatches) count++;

        // Check upward-right diagonal
        wordMatches = true;
        for (int i = rootRow, j = rootColumn, k = 0; i > rootRow - word.length(); i--, j++, k++) {
            if ((i < 0) || (j >= wordsearchPuzzle.get(i).length()) 
                    || (wordsearchPuzzle.get(i).charAt(j)) != word.charAt(k)) {
                wordMatches = false;
                break;
            }
        }
        if (wordMatches) count++;

        // Check upward-left diagonal
        wordMatches = true;
        for (int i = rootRow, j = rootColumn, k = 0; i > rootRow - word.length(); i--, j--, k++) {
            if ((i < 0) || (j < 0) || (wordsearchPuzzle.get(i).charAt(j)) != word.charAt(k)) {
                wordMatches = false;
                break;
            }
        }
        if (wordMatches) count++;

        return count;
    }

    /**
     * Searches the Wordseach puzzle and counts how many times two "MAS" words appear in an X pattern.
     * @param wordsearchFile text file containing the Wordsearch puzzle
     * @return the number of times MAS forms an X
     */
    public static long getWordSearchXMASCount(File wordsearchFile) {
        int count = 0;
        ArrayList<String> wordsearchPuzzle = new ArrayList<>();

        // Import the Wordsearch Puzzle from the input file
        try (Scanner wordsearchScanner = new Scanner(wordsearchFile)) {
            while (wordsearchScanner.hasNextLine()) {
                String wordsearchRow = wordsearchScanner.nextLine();
                wordsearchPuzzle.add(wordsearchRow);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < wordsearchPuzzle.size() - 1; i++) {
            for (int j = 1; j < wordsearchPuzzle.get(i).length() - 1; j++) {
                if ((wordsearchPuzzle.get(i).charAt(j)) == 'A') { // Find the center of a potential 'MAS' X
                    if ((wordsearchPuzzle.get(i-1).charAt(j-1) == 'M') && 
                            (wordsearchPuzzle.get(i+1).charAt(j+1) == 'S') || 
                            (wordsearchPuzzle.get(i-1).charAt(j-1) == 'S') && 
                            (wordsearchPuzzle.get(i+1).charAt(j+1) == 'M')) {
                        if ((wordsearchPuzzle.get(i-1).charAt(j+1) == 'M') && 
                                (wordsearchPuzzle.get(i+1).charAt(j-1) == 'S') || 
                                (wordsearchPuzzle.get(i-1).charAt(j+1) == 'S') && 
                                (wordsearchPuzzle.get(i+1).charAt(j-1) == 'M')) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
 }