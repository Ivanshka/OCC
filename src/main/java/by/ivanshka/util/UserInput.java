package by.ivanshka.util;

import java.util.Scanner;

public class UserInput {

    private static final String INVALID_NUMBER_ERROR_MESSAGE = "Invalid number, try again.";

    public static int readInt(Scanner in) {
        while (true) {
            try {
                return in.nextInt();
            } catch (Exception e) {
                System.out.println(INVALID_NUMBER_ERROR_MESSAGE);
            } finally {
                in.nextLine();
            }
        }
    }

    public static float readFloat(Scanner in) {
        while (true) {
            try {
                return in.nextFloat();
            } catch (Exception e) {
                System.out.println(INVALID_NUMBER_ERROR_MESSAGE);
            } finally {
                in.nextLine();
            }
        }
    }
}
