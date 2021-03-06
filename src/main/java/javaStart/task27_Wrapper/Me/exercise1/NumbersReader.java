package javaStart.task27_Wrapper.Me.exercise1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumbersReader {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Mateusz\\Desktop\\number.txt";

        try {
            List<BigInteger> integers = fromStringToBigInteger(fileName);
            printLine("The file contains the following numbers: ");
            printNumbers(integers);
            BigInteger sumShortNumbers = addShortNumbers(integers);
            BigInteger sumLongNumbers = addLongNumbers(integers);
            printLine("");
            printLine("Sum of short numbers is: " + sumShortNumbers);
            printLine("Sum of long numbers is: " + sumLongNumbers);
        } catch (FileNotFoundException e) {
            printLineError("No file: " + fileName);
        } catch (IOException e) {
            printLineError("Error read file: " + fileName);
        }
    }

    private static List<BigInteger> fromStringToBigInteger(String fileName) throws IOException {
        try (var bufferedReader = new BufferedReader(new FileReader(fileName))) {
            List<BigInteger> integers = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                integers.add(new BigInteger(line));
            }
            return integers;
        }
    }

    private static BigInteger addLongNumbers(List<BigInteger> integers) {
        BigInteger sum;
            sum = integers.get(3).add(integers.get(4));
        return sum;
    }

    private static BigInteger addShortNumbers(List<BigInteger> integers) {
        BigInteger sum;
            sum = integers.get(0).add(integers.get(1).add(integers.get(2)));
        return sum;
    }

    private static void printNumbers(List<BigInteger> integers) {
        for (BigInteger integer : integers) {
            printLine("" + integer);
        }
    }

    private static void printLine(String text) {
        System.out.println(text);
    }

    private static void printLineError(String text) {
        System.err.println(text);
    }
}

