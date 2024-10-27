package database;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import models.Task;
import utils.JsonFile;
import utils.Logger;

public class TaskManager {
    private static String FILE_PATH = "src/database/tasks.json";
    private static String TABLE = "tasks";
    private static JsonArray data = new JsonArray();

    public TaskManager() {
        try {
            data = (new JsonFile()).read(FILE_PATH).getAsJsonArray();
        } catch (Exception e) {
            Logger.info("Can't read database tasks");
        }
    }

    private static void save(JsonArray data) throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(TABLE, data);
        (new JsonFile()).write(FILE_PATH, jsonObject);
    }

    public static Task create(String title, String status) throws Exception {
        String id = String.valueOf(System.currentTimeMillis());

        JsonObject item = new JsonObject();
        item.addProperty("id", id);
        item.addProperty("title", title);
        item.addProperty("status", status);
        data.add(item);
        save(data);

        return new Task(id, title, status);
    }

    public static Task findById(String id) throws Exception {
        for (JsonElement item : data) {
            JsonObject task = item.getAsJsonObject();
            if (task.get("id").getAsString().equals(id)) {
                return new Task(task.get("id").getAsString(), task.get("title").getAsString(),
                        task.get("status").getAsString());
            }
        }
        return null;
    }

    public static List<Task> findAll(String status) throws Exception {
        List<Task> tasks = new ArrayList<Task>();
        if (!data.isEmpty()) {
            for (JsonElement item : data) {
                JsonObject taskJson = item.getAsJsonObject();
                Task task = new Task(
                        taskJson.get("id").getAsString(),
                        taskJson.get("title").getAsString(),
                        taskJson.get("status").getAsString());

                if (status == null || taskJson.get("status").getAsString().equals(status)) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    public static boolean update(String id, String newTitle, String newStatus) throws Exception {
        if (newTitle == null && newStatus == null)
            return false;

        for (JsonElement item : data) {
            JsonObject itemObj = item.getAsJsonObject();
            if (itemObj.get("id").getAsString().equals(id)) {
                if (newTitle != null) {
                    itemObj.addProperty("title", newTitle);
                }
                if (newStatus != null) {
                    itemObj.addProperty("status", newStatus);
                }
                save(data);
                return true;
            }
        }
        return false;
    }

    public static boolean delete(String id) throws Exception {
        for (JsonElement item : data) {
            JsonObject itemObj = item.getAsJsonObject();
            if (itemObj.get("id").getAsString().equals(id)) {
                data.remove(itemObj);
                save(data);
                return true;
            }
        }
        return false;
    }

}
