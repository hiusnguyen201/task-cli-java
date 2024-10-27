import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controllers.TaskController;
import models.Command;
import models.Task;
import utils.Logger;

public class App {
    private TaskController taskController = new TaskController();

    public static void main(String[] args) throws Exception {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        App app = new App();
        do {
            System.out.println("\n===== Welcome to Task List =====");
            Logger.info("Command start with \"task-cli <command>\"");
            Logger.info("Help: \"task-cli --help\"");
            Logger.info("Exit: \"task-cli --exit\"\n");
            String cli = scanner.nextLine();
            System.err.println();

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
                    app.handleAddTask(cli);
                    break;
                case "delete":
                    app.handleDeleteTask(arrCli[2]);
                    break;
                case "update":
                    app.handleUpdateTask(arrCli[2], cli);
                    break;
                case "list":
                    if (arrCli.length < 3) {
                        app.printAllTask(null);
                    } else {
                        switch (arrCli[2]) {
                            case "todo":
                                app.printAllTask("todo");
                                break;
                            case "in-progress":
                                app.printAllTask("in-progress");
                                break;
                            case "done":
                                app.printAllTask("done");
                                break;
                            default:
                                app.printAllTask(null);
                                break;
                        }
                    }

                    break;
                case "mark-todo":
                    app.handleUpdateStatus(arrCli[2], "todo");
                    break;
                case "mark-in-progress":
                    app.handleUpdateStatus(arrCli[2], "in-progress");
                    break;
                case "mark-done":
                    app.handleUpdateStatus(arrCli[2], "done");
                    break;
                case "--exit":
                    System.out.println("===== Bye =====");
                    scanner.close();
                    System.exit(1);
                    break;
                default:
                    Logger.error("Invalid command. Command not found");
                    break;
            }
        } while (true);
    }

    public void handleAddTask(String cli) {
        int firstQuoteIndex = cli.indexOf("\"");
        int secondQuoteIndex = cli.indexOf("\"", firstQuoteIndex + 1);
        String title = cli.substring(firstQuoteIndex + 1, secondQuoteIndex);
        this.taskController.addTask(title);
    }

    public void handleUpdateTask(String id, String cli) {
        int firstQuoteIndex = cli.indexOf("\"");
        int secondQuoteIndex = cli.indexOf("\"", firstQuoteIndex + 1);
        String title = cli.substring(firstQuoteIndex + 1, secondQuoteIndex);
        this.taskController.updateTask(id, title);
    }

    public void handleDeleteTask(String id) {
        this.taskController.deleteTask(id);
    }

    public void handleUpdateStatus(String id, String status) {
        this.taskController.updateStatus(id, status);
    }

    public void printAllTask(String status) {
        List<Task> data = new ArrayList<Task>();
        if (status == null || status.isEmpty()) {
            data = this.taskController.getTasks(null);
        } else {
            data = this.taskController.getTasks(status);
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
