import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controllers.TaskController;
import models.Command;
import models.Task;
import utils.Logger;

public class App {
    public static void main(String[] args) throws Exception {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n===== Welcome to Task List =====");
            Logger.info("Command start with \"task-cli <command>\"");
            Logger.info("Help: \"task-cli --help\"");
            String cli = scanner.nextLine();
            String[] arrCli = cli.trim().split(" ");

            if (!arrCli[0].equals("task-cli") || arrCli.length < 2) {
                Logger.error("Invalid command");
                continue;
            }

            switch (arrCli[1]) {
                case "--help":
                    printHelp();
                    break;
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
                    Logger.error("Invalid command. Command not found");
                    break;
            }

        } while (true);
    }

    public static void handleAddTask(String cli) {
        int firstQuoteIndex = cli.indexOf("\"");
        int secondQuoteIndex = cli.indexOf("\"", firstQuoteIndex + 1);
        String title = cli.substring(firstQuoteIndex + 1, secondQuoteIndex);
        TaskController.addTask(title);
    }

    public static void handleUpdateTask(String id, String cli) {
        int firstQuoteIndex = cli.indexOf("\"");
        int secondQuoteIndex = cli.indexOf("\"", firstQuoteIndex + 1);
        String title = cli.substring(firstQuoteIndex + 1, secondQuoteIndex);
        TaskController.updateTask(id, title);
    }

    public static void handleDeleteTask(String id) {
        TaskController.deleteTask(id);
    }

    public static void handleUpdateStatus(String id, String status) {
        TaskController.updateStatus(id, status);
    }

    public static void printAllTask(String status) {
        List<Task> data = new ArrayList<Task>();
        if (status == null || status.isEmpty()) {
            data = TaskController.getTasks(null);
        } else {
            data = TaskController.getTasks(status);
        }

        String leftAlignFormat = "| %-13s | %-20s | %-13s |%n";
        System.out.format("+---------------+----------------------+---------------+%n");
        System.out.format("| ID            | Title                | Status        |%n");
        System.out.format("+---------------+----------------------+---------------+%n");

        for (Task item : data) {
            System.out.format(leftAlignFormat,
                    item.getId(), item.getTitle(),
                    item.getStatus());
        }

        System.out.format("+---------------+----------------------+---------------+%n");
    }

    public static void printHelp() {
        String leftAlignFormat = "| %-25s | %-40s |%n";
        System.out.format("+---------------------------+------------------------------------------+%n");
        System.out.format("| Command                   | Description                              |%n");
        System.out.format("+---------------------------+------------------------------------------+%n");

        ArrayList<Command> commands = new ArrayList<Command>();
        commands.add(new Command("add <title>", "Add new task"));
        commands.add(new Command("update <id>", "Update info task"));
        commands.add(new Command("delete <id>", "Delete task"));
        commands.add(new Command("list", "Print list task"));
        commands.add(new Command("list todo", "Print list task with status todo"));
        commands.add(new Command("list in-progress", "Print list task with status in-progress"));
        commands.add(new Command("list done", "Print list task with status done"));
        commands.add(new Command("mark-todo <id>", "Mark task have status todo"));
        commands.add(new Command("mark-in-progress <id>", "Mark task have status in-progress"));
        commands.add(new Command("mark-done <id>", "Mark task have status done"));

        for (Command item : commands) {
            System.out.format(leftAlignFormat, item.name, item.desc);
        }

        System.out.format("+---------------------------+------------------------------------------+%n");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
