package adventofcode2024.day01;

/* Day 1: Historian Hysteria (https://adventofcode.com/2024/day/1) */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
 
 public class Day01 {
     public static void main(String[] args) {
        // Declarations //////
        File locationListsFile = new File(System.getProperty("user.dir") 
            + "\\app\\src\\main\\resources\\day01\\input.txt");
        List<Integer> leftLocationList = new ArrayList<>(), rightLocationList = new ArrayList<>();
        //////////////////////
         
        scanLocationListsFile(locationListsFile, leftLocationList, rightLocationList);
 
        System.out.println("---------- DAY 1 2024 ----------");

        System.out.println("------------ Part 1 ------------");
        generateTotalDistance(leftLocationList, rightLocationList);
        
        System.out.println("\n------------ Part 2 ------------");
        generateSimilarityScore(leftLocationList, rightLocationList);
    }

    /**
     * Calculates the total distance between two lists of location IDs by summing the absolute differences of 
     * corresponding elements, and prints the result to the terminal. 
     * @param leftList List of location IDs
     * @param rightList List of location IDs
     */
    private static void generateTotalDistance(List<Integer> leftList, List<Integer> rightList) {
        int totalDistance = 0;
        
        // Sort the lists
        leftList.sort(Comparator.naturalOrder());
        rightList.sort(Comparator.naturalOrder());

        for (int i = 0; i < leftList.size(); i++) {
            totalDistance += Math.abs(leftList.get(i) - rightList.get(i));
        }

        System.out.println("Total Distance: " + totalDistance);
    }

    /**
     * Calculates the similarity score between two lists of location IDs by multiplying each element in the left list 
     * by its count of occurrences in the right list, and prints the result to the terminal. 
     * @param leftList List of location IDs
     * @param rightList List of location IDs
     */
    private static void generateSimilarityScore(List<Integer> leftList, List<Integer> rightList) {
        Map<Integer, Integer> rightListCounts = new HashMap<>();
        int similarityScore = 0;

        // Map out the count for the locations in the right list
        for (int i = 0; i < rightList.size(); i++) {
            int location = rightList.get(i);
            rightListCounts.put(location, rightListCounts.getOrDefault(location, 0) + 1);
        }

        // Walk through each value in the Left List and multiply it by that location IDs' count in the right list
        for (int i = 0; i < leftList.size(); i++) {
            int location = leftList.get(i);
            similarityScore += (location * rightListCounts.getOrDefault(location, 0));
        }

        System.out.println("Similarity Score: " + similarityScore);
    }
 
    /**
     * Scans the Location Lists and loads all of the integers into two lists
     * @param inputFile text file for the Location Ids
     * @param leftList List structure for storing the numbers list on the left side of each line
     * @param rightList List structure for storing the numbers list on the right side of each line
     */
    private static void scanLocationListsFile(File inputFile, List<Integer> leftList, List<Integer> rightList) {
        try (Scanner intTokenizer = new Scanner(inputFile)) {

            while (intTokenizer.hasNextLine()) {
                leftList.add(intTokenizer.nextInt());
                rightList.add(intTokenizer.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
 }