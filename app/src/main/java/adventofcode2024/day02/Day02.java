package adventofcode2024.day02;

/* Day 2: Password Philosophy (https://adventofcode.com/2024/day/2) */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
 
 public class Day02 {
     public static void main(String[] args) {
        // Declarations
        File reportsFile = new File(System.getProperty("user.dir") 
            + "\\app\\src\\main\\resources\\day02\\input.txt");

        System.out.println("---------- DAY 2 2024 ----------");

        System.out.println("------------ Part 1 ------------");
        System.out.println("Number of safe reports: " + getReportSafetyCount(reportsFile, false));
        
        System.out.println("\n------------ Part 2 ------------");
        System.out.println("Number of safe reports: " + getReportSafetyCount(reportsFile, true));
    }

    /**
     * Scans a list of reports to determine how many reports are safe. If a Problem Dampener is used, a report can 
     * tolerate a single bad level.
     * @param reportsFile text file with reports. Each line is a separate report. In each report, levels are represented
     * as integers and separated by a space
     * @param useProblemDampener If true, a given report can be safe if ignoring a single bad level makes the rest of 
     * the report safe
     * @return the count of safe reports
     */
    public static int getReportSafetyCount(File reportsFile, boolean useProblemDampener) {
        int count = 0;

        try (Scanner reportScanner = new Scanner(reportsFile)) {


            while (reportScanner.hasNextLine()) {
                String report = reportScanner.nextLine();
                StringTokenizer levelTokenizer = new StringTokenizer(report, " ");

                List<Integer> levels = new ArrayList<>();
                while (levelTokenizer.hasMoreTokens()) {
                    levels.add(Integer.parseInt(levelTokenizer.nextToken()));
                }

                if (isReportSafe(levels, useProblemDampener)) count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Evaulates if the report is safe.
     * @param levels a list of levels representing a report
     * @param enableProblemDampener If true, a report is safe if ignoring a single bad level makes the rest of the 
     * report safe
     * @return true if the report is safe, false otherwise
     */
    public static boolean isReportSafe(List<Integer> levels, boolean enableProblemDampener) {
        if (enableProblemDampener) {
            for (int i = 0; i < levels.size(); i++) {
                if (isReportSafe(levels, i)) {
                    return true;
                }
            }

            return false;
        } else {
            return isReportSafe(levels, -1);
        }
    }

    /**
     * Evaulates if the report is safe.
     * @param levels a list of levels representing a report
     * @param ignoredIndex index of a level in the report that is ignored during evaluation
     * @return true if the report is safe, false otherwise
     */
    public static boolean isReportSafe(List<Integer> levels, int ignoredIndex) {
        int levelAIndex = 0, levelBIndex = 1;
        int slope = 0, levelDifference;
        boolean isSafe = true;
        boolean hasSlope = false;
        
        if (levels.size() == 1 || (levels.size() == 2 && ignoredIndex >= 0)) return true;

        while (levelBIndex < levels.size()) {
            if (levelAIndex == ignoredIndex) {
                levelAIndex++;
                levelBIndex++;
            } else if (levelBIndex == ignoredIndex) {
                levelBIndex++;
            } else {
                if (!hasSlope) {
                    slope = levels.get(levelBIndex) - levels.get(levelAIndex);
                    hasSlope = true;
                }
                levelDifference = levels.get(levelBIndex) - levels.get(levelAIndex);

                if (Math.abs(levelDifference) >= 4 || levelDifference == 0) {
                    isSafe = false;
                    break;
                }

                if ((slope > 0 && levelDifference < 0) || (slope < 0 && levelDifference > 0)) {
                    isSafe = false;
                    break;
                }

                levelAIndex = levelBIndex;
                levelBIndex++;
            }
        }

        return isSafe;
    }
 }