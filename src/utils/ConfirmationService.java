package utils;

import java.io.Console;
import java.util.Locale;
import java.util.Scanner;

public class ConfirmationService {
    public static boolean confirm(String prompt, boolean defaultYes) {
        Console console = System.console();
        String suffix = defaultYes ? " [Y/n]: " : " [y/N]: ";
        String full = prompt + suffix;
        if (console != null) {
            String input = console.readLine(full);
            if (input == null || input.trim().isEmpty()) return defaultYes;
            input = input.trim().toLowerCase(Locale.ROOT);
            return input.equals("y") || input.equals("s") || input.equals("si") || input.equals("yes");
        }
        // fallback
        System.out.print(full);
        Scanner sc = new Scanner(System.in);
        try {
            String input = sc.nextLine();
            if (input == null || input.trim().isEmpty()) return defaultYes;
            input = input.trim().toLowerCase(Locale.ROOT);
            return input.equals("y") || input.equals("s") || input.equals("si") || input.equals("yes");
        } catch (Exception e) {
            return defaultYes;
        }
    }
}
