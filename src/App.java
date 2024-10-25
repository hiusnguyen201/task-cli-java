import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import controllers.TaskController;
import utils.Logger;

public class App {
    private static TaskController taskController = new TaskController();
    private static Logger logger = new Logger();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        clearScreen();
        do {
            System.out.println("\n===== Welcome to Task List =====");
            logger.info("Command start with \"task-cli <command>\"");
            String cli = scanner.nextLine();
            String[] arrCli = cli.trim().split(" ");

            if (!arrCli[0].equals("task-cli") || arrCli.length < 2) {
                logger.error("Invalid command");
                continue;
            }

            switch (arrCli[1]) {
                case "add":
                    handleAddTask(cli);
                    break;
                case "delete":
                    handleDeleteTask(arrCli[2]);
                    break;
                case "update":
                    handleUpdateTask(arrCli[2], cli);
                    break;
                case "list":
                    if (arrCli.length < 3) {
                        printAllTask(null);
                    } else {
                        switch (arrCli[2]) {
                            case "todo":
                                printAllTask("todo");
                                break;
                            case "in-progress":
                                printAllTask("in-progress");
                                break;
                            case "done":
                                printAllTask("done");
                                break;
                            default:
                                printAllTask(null);
                                break;
                        }
                    }

                    break;
                case "mark-todo":
                    handleUpdateStatus(arrCli[2], "todo");
                    break;
                case "mark-in-progress":
                    handleUpdateStatus(arrCli[2], "in-progress");
                    break;
                case "mark-done":
                    handleUpdateStatus(arrCli[2], "done");
                    break;
                default:
                    logger.error("Invalid command. Command not found");
                    break;
            }

        } while (true);
    }

    public static void handleAddTask(String cli) {
        int firstQuoteIndex = cli.indexOf("\"");
        int secondQuoteIndex = cli.indexOf("\"", firstQuoteIndex + 1);
        String title = cli.substring(firstQuoteIndex + 1, secondQuoteIndex);
        taskController.addTask(title);
    }

    public static void handleUpdateTask(String id, String cli) {
        int firstQuoteIndex = cli.indexOf("\"");
        int secondQuoteIndex = cli.indexOf("\"", firstQuoteIndex + 1);
        String title = cli.substring(firstQuoteIndex + 1, secondQuoteIndex);
        taskController.updateTask(id, title);
    }

    public static void handleDeleteTask(String id) {
        taskController.deleteTask(id);
    }

    public static void handleUpdateStatus(String id, String status) {
        taskController.updateStatus(id, status);
    }

    public static void printAllTask(String status) {
        JsonArray data = new JsonArray();
        if (status == null || status.isEmpty()) {
            data = taskController.getTasks();
        } else {
            data = taskController.getTasksByStatus(status);
        }

        String leftAlignFormat = "| %-13s | %-20s | %-13s |%n";
        System.out.format("+---------------+----------------------+---------------+%n");
        System.out.format("| ID            | Title                | Status        |%n");
        System.out.format("+---------------+----------------------+---------------+%n");

        for (JsonElement item : data) {
            JsonObject obj = item.getAsJsonObject();
            System.out.format(leftAlignFormat, obj.get("id").getAsString(), obj.get("title").getAsString(),
                    obj.get("status").getAsString());
        }

        System.out.format("+---------------+----------------------+---------------+%n");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
