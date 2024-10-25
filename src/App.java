import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controllers.TaskController;
import utils.Logger;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);
        Logger logger = new Logger();
        RegexPattern regexPattern = new RegexPattern();
        TaskController taskController = new TaskController();

        do {
            System.out.println("===== Task List =====");
            String cli = scanner.nextLine();
            String[] arrCli = cli.trim().split(" ");

            if (!arrCli[0].equals("task-cli") || arrCli.length < 2) {
                logger.error("Invalid command. Command start with \"task-cli <command>\"\n");
                continue;
            }

            switch (arrCli[1]) {
                case "add":
                    int firstQuoteIndex = cli.indexOf("\"");
                    int secondQuoteIndex = cli.indexOf("\"", firstQuoteIndex + 1);
                    String title = cli.substring(firstQuoteIndex + 1, secondQuoteIndex);

                    taskController.addTask(title);
                    break;
                case "delete":
                    break;
                case "update":
                    break;
                case "list":
                    break;
                case "mark-todo":
                    break;
                case "mark-in-progress":
                    break;
                case "mark-done":
                    break;
                default:
                    logger.error("Invalid command. Command not found");
                    break;
            }
        } while (true);
    }
}
