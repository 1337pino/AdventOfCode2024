package adventofcode2024.day03;

/* Day 3: Mull It Over (https://adventofcode.com/2024/day/3) */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 
 public class Day03 {
     public static void main(String[] args) {
        // Declarations
        File memoryFile = new File(System.getProperty("user.dir") 
            + "\\app\\src\\main\\resources\\day03\\input.txt");

        System.out.println("---------- DAY 3 2024 ----------");

        System.out.println("------------ Part 1 ------------");
        System.out.println("Sum of multiplications: " + getMultiplicationSum(memoryFile));
        
        System.out.println("\n------------ Part 2 ------------");
        System.out.println("Sum of multiplications: " + getMultiplicationSumWithInstructions(memoryFile));
    }

    /**
     * Scans the corrupted memory file of a program to add up all of the values of the multiplication commands.
     * @param memoryFile text file strings representing the corrupted memory of the program
     * @return the sum of all of the results of the multiplication commands
     */
    public static long getMultiplicationSum(File memoryFile) {
        long sum = 0;

        try (Scanner reportScanner = new Scanner(memoryFile)) {
            while (reportScanner.hasNextLine()) {
                String memoryLine = reportScanner.nextLine();
                String multiplicationPattern = "mul\\(\\d{1,3},\\d{1,3}\\)";
                Matcher mulInstructions = Pattern.compile(multiplicationPattern).matcher(memoryLine);

                while (mulInstructions.find()) {
                    Matcher factors = Pattern.compile("\\d{1,3}").matcher(mulInstructions.group());

                    factors.find();
                    int factorA = Integer.parseInt(factors.group());

                    factors.find();
                    int factorB = Integer.parseInt(factors.group());

                    sum += (factorA*factorB);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return sum;
    }

    /**
     * Scans the corrupted memory file of a program to add up all of the values of the multiplication commands.
     * @param memoryFile text file strings representing the corrupted memory of the program
     * @return the sum of all of the results of the multiplication commands
     */
    public static long getMultiplicationSumWithInstructions(File memoryFile) {
        long sum = 0;
        boolean doMultiplication = true;

        try (Scanner reportScanner = new Scanner(memoryFile)) {
            System.out.println("Pre-Do/Don't:");

            while (reportScanner.hasNextLine()) {
                String memoryLine = reportScanner.nextLine();
                String multiplicationPattern = "^mul\\(\\d{1,3},\\d{1,3}\\)";
                String doInstructionPattern = "^do\\(\\)";
                String doNotInstructionPattern = "^don't\\(\\)";

                for (int i = 0; i < memoryLine.length(); i++) {
                    String substring = memoryLine.substring(i);
                    Matcher mulInstructions = Pattern.compile(multiplicationPattern).matcher(substring);
                    Matcher doInstructions = Pattern.compile(doInstructionPattern).matcher(substring);
                    Matcher doNotInstructions = Pattern.compile(doNotInstructionPattern).matcher(substring);
                    
                    if (doNotInstructions.find()) {
                        doMultiplication = false;
                    } else if (doInstructions.find()) {
                        doMultiplication = true;
                    } else if (doMultiplication && mulInstructions.find()) {
                        Matcher factors = Pattern.compile("\\d{1,3}").matcher(mulInstructions.group());

                        factors.find();
                        int factorA = Integer.parseInt(factors.group());

                        factors.find();
                        int factorB = Integer.parseInt(factors.group());

                        sum += (factorA*factorB);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return sum;
    }
 }