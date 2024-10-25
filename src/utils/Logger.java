package utils;

public class Logger {
    final String CLI_RESET = "\u001B[0m";
    final String CLI_RED = "\u001B[31m";
    final String CLI_GREEN = "\u001B[32m";
    final String CLI_BLUE = "\u001B[34m";

    public void error(String str) {
        System.out.println(CLI_RED + str + CLI_RESET);
    }

    public void info(String str) {
        System.out.println(CLI_BLUE + str + CLI_RESET);
    }

    public void success(String str) {
        System.out.println(CLI_GREEN + str + CLI_RESET);
    }
}
