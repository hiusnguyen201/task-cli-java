package models;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utils.JsonFile;

public class Task {
    private String FILE_PATH = "src/database/database.json";
    private String TABLE = "tasks";
    private JsonFile jsonFile = new JsonFile();

    public JsonArray getAll() throws IOException {
        JsonObject data = jsonFile.read(FILE_PATH);
        return data.isEmpty() ? new JsonArray() : data.getAsJsonArray(TABLE);
    }

    public JsonArray getAllByStatus(String status) throws IOException {
        JsonObject data = jsonFile.read(FILE_PATH);
        JsonArray dataStatus = new JsonArray();
        if (data.isEmpty()) {
            return new JsonArray();
        }

        for (JsonElement item : data.getAsJsonArray(TABLE)) {
            JsonObject obj = item.getAsJsonObject();
            if (obj.get("status").getAsString().equals(status)) {
                dataStatus.add(obj);
            }
        }

        return dataStatus;
    }

    public void add(String title) throws IOException {
        // New
        JsonObject item = new JsonObject();
        item.addProperty("id", String.valueOf(System.currentTimeMillis()));
        item.addProperty("title", title);
        item.addProperty("status", "todo");
        // Add
        JsonArray data = getAll();
        data.add(item);
        // Save
        save(data);
    }

    public void update(String id, String newTitle) throws IOException {
        JsonArray data = getAll();
        JsonObject obj = findOne(id, data);
        obj.addProperty("title", newTitle);
        save(data);
    }

    public void updateStatus(String id, String status) throws IOException {
        JsonArray data = getAll();
        JsonObject obj = findOne(id, data);
        obj.addProperty("status", status);
        save(data);
    }

    public void delete(String id) throws IOException {
        JsonArray data = getAll();
        JsonObject obj = findOne(id, data);
        data.remove(obj);
        save(data);
    }

    public void save(JsonArray data) throws IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(TABLE, data);
        jsonFile.write(FILE_PATH, jsonObject);
    }

    public JsonObject findOne(String id, JsonArray data) throws IOException {
        for (JsonElement item : data) {
            JsonObject task = item.getAsJsonObject();
            if (task.get("id").getAsString().equals(id)) {
                return task;
            }
        }
        return null;
    }
}
