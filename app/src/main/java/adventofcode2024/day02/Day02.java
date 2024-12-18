package adventofcode2024.day02;

/* Day 2: Password Philosophy (https://adventofcode.com/2024/day/2)

--- Part One ---
Fortunately, the first location The Historians want to search isn't a long walk from the Chief Historian's office.

While the Red-Nosed Reindeer nuclear fusion/fission plant appears to contain no sign of the Chief Historian, the 
engineers there run up to you as soon as they see you. Apparently, they still talk about the time Rudolph was saved 
through molecular synthesis from a single electron.

They're quick to add that - since you're already here - they'd really appreciate your help analyzing some unusual data 
from the Red-Nosed reactor. You turn to check if The Historians are waiting for you, but they seem to have already 
divided into groups that are currently searching every corner of the facility. You offer to help with the unusual data.

The unusual data (your puzzle input) consists of many reports, one report per line. Each report is a list of numbers 
called levels that are separated by spaces. For example:

7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
This example data contains six reports each containing five levels.

The engineers are trying to figure out which reports are safe. The Red-Nosed reactor safety systems can only tolerate 
levels that are either gradually increasing or gradually decreasing. So, a report only counts as safe if both of the 
following are true:

The levels are either all increasing or all decreasing.
Any two adjacent levels differ by at least one and at most three.
In the example above, the reports can be found safe or unsafe by checking those rules:

7 6 4 2 1: Safe because the levels are all decreasing by 1 or 2.
1 2 7 8 9: Unsafe because 2 7 is an increase of 5.
9 7 6 2 1: Unsafe because 6 2 is a decrease of 4.
1 3 2 4 5: Unsafe because 1 3 is increasing but 3 2 is decreasing.
8 6 4 4 1: Unsafe because 4 4 is neither an increase or a decrease.
1 3 6 7 9: Safe because the levels are all increasing by 1, 2, or 3.
So, in this example, 2 reports are safe.

Analyze the unusual data from the engineers. How many reports are safe?

--- Part Two ---
The engineers are surprised by the low number of safe reports until they realize they forgot to tell you about the 
Problem Dampener.

The Problem Dampener is a reactor-mounted module that lets the reactor safety systems tolerate a single bad level in 
what would otherwise be a safe report. It's like the bad level never happened!

Now, the same rules apply as before, except if removing a single level from an unsafe report would make it safe, the 
report instead counts as safe.

More of the above example's reports are now safe:

7 6 4 2 1: Safe without removing any level.
1 2 7 8 9: Unsafe regardless of which level is removed.
9 7 6 2 1: Unsafe regardless of which level is removed.
1 3 2 4 5: Safe by removing the second level, 3.
8 6 4 4 1: Safe by removing the third level, 4.
1 3 6 7 9: Safe without removing any level.
Thanks to the Problem Dampener, 4 reports are actually safe!

Update your analysis by handling situations where the Problem Dampener can remove a single level from unsafe reports. 
How many reports are now safe?
 */

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