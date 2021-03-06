package javaStart.task29_ArraysOperation.Me.exercise1Array.io;

import javaStart.task29_ArraysOperation.Me.exercise1Array.exception.IdLengthException;
import javaStart.task29_ArraysOperation.Me.exercise1Array.model.Car;
import javaStart.task29_ArraysOperation.Me.exercise1Array.model.Person;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataReader {
    private ConsolePrinter printer = new ConsolePrinter();
    private Scanner input = new Scanner(System.in);

    public Person createPerson() {
        printer.printLine("Enter a first name");
        String firstName = input.nextLine();
        printer.printLine("Enter a second name");
        String secondName = input.nextLine();
        printer.printLine("Enter an id");
        int id = getNumber();
        return new Person(firstName, secondName, id);
    }

    public Car createCar() {
        printer.printLine("Enter a mark");
        String mark = input.nextLine();
        printer.printLine("Enter a model");
        String model = input.nextLine();
        printer.printLine("Enter an engine capacity");
        int engineCapacity = getEngineCapacity();
        printer.printLine("Enter a kind of fuel");
        String fuel = input.nextLine();
        printer.printLine("Enter an vin");
        int vin = getNumber();
        return new Car(mark, model, engineCapacity, fuel, vin);
    }

    public int getNumberToRemove() {
        printer.printLine("Enter a suitable number to remove");
        return getNumber();
    }

    private int getEngineCapacity() {
        boolean capacityOk = false;
        int capacity = 0;
        while (!capacityOk) {
            try {
                capacity = input.nextInt();
                capacityOk = true;
            } catch (InputMismatchException e) {
                printer.printLineError("Enter digit, try again");
            } catch (IdLengthException e) {
                e.getMessage();
            } finally {
                clearBuffer();
            }
        }
        return capacity;
    }

    private int getNumber() {
        boolean numberOk = false;
        int number = 0;
        while (!numberOk) {
            try {
                number = input.nextInt();
                checkLength(number);
                numberOk = true;
            } catch (InputMismatchException e) {
                printer.printLineError("Enter digit, try again");
            } catch (IdLengthException e) {
                e.getMessage();
            } finally {
                clearBuffer();
            }
        }
        return number;
    }

    private int checkLength(int number) {
        int length = String.valueOf(number).length();
        if (length != 3) {
            throw new IdLengthException("Number must be 3 digits, try again");
        }
        return number;
    }

    public void clearBuffer() {
        input.nextLine();
    }

    public int getInt() {
        try {
            return input.nextInt();
        } finally {
            input.nextLine();
        }
    }

    public int getIntNoBuffer() {
        return input.nextInt();
    }

    public void close() {
        input.close();
    }
}
