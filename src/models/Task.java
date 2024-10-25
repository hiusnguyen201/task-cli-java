package models;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import utils.JsonFile;

public class Task {
    final String FILE_PATH = "src/database/database.json";
    protected String TABLE = "tasks";
    private JsonFile jsonFile = new JsonFile();

    public JsonArray getAll() throws IOException {
        JsonObject data = jsonFile.read(FILE_PATH);
        System.out.println(data);
        return new JsonArray();
        // return data != null ? data.getAsJsonArray(TABLE) : new JsonArray();
    }

    public void add(String title) throws IOException {
        // New
        JsonObject item = new JsonObject();
        item.addProperty("id", String.valueOf(System.currentTimeMillis()));
        item.addProperty("title", title);

        // Add
        JsonArray data = getAll();
        data.add(item);

        // Save
        save(data);
    }

    public void save(JsonArray data) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(TABLE, data);
        jsonFile.write(FILE_PATH, jsonObject);
    }
}
