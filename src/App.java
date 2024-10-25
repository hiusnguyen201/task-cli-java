import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.Logger;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);
        Logger logger = new Logger();

        try (FileReader reader = new FileReader("src/database/data.json")) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray tasksJsonArray = jsonObject.getAsJsonArray("tasks");
        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("===== Task List =====");
            String cli = scanner.nextLine();
            String[] arrCli = cli.split(" ");

            if (arrCli[0] != "task-cli") {
                logger.error("Invalid command. Command start with \"task-cli\"\n");
                continue;
            }

            switch (arrCli[1]) {
                case "add":
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
