package utils;

public class Logger {
    private static String CLI_RESET = "\u001B[0m";
    private static String CLI_RED = "\u001B[31m";
    private static String CLI_GREEN = "\u001B[32m";
    private static String CLI_BLUE = "\u001B[34m";

    public static void error(String str) {
        System.out.println(CLI_RED + str + CLI_RESET);
    }

    public static void info(String str) {
        System.out.println(CLI_BLUE + str + CLI_RESET);
    }

    public static void success(String str) {
        System.out.println(CLI_GREEN + str + CLI_RESET);
    }
}
