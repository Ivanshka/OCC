package by.ivanshka.util;

import java.util.Scanner;

public class UserInput {
    public static int readInt(Scanner in) {
        while (true) {
            try {
                return in.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid number, try again.");
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
                System.out.println("Invalid number, try again.");
            } finally {
                in.nextLine();
            }
        }
    }
}
