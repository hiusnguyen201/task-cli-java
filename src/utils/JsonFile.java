package utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonFile {
    public JsonObject read(String path) throws IOException {
        FileReader fr = new FileReader(path);
        JsonElement jsonElement = JsonParser.parseReader(fr);
        return jsonElement.isJsonNull() ? new JsonObject() : jsonElement.getAsJsonObject();
    }

    public void write(String path, JsonObject data) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(data.toString());
        fw.close();
    }
}
