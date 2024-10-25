package utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonFile {
    public JsonObject read(String path) {
        try (FileReader fr = new FileReader(path)) {
            return JsonParser.parseReader(fr).getAsJsonObject();
        } catch (IOException e) {
            return null;
        }
    }

    public void write(String path, JsonObject data) {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(data.toString());
        } catch (IOException e) {
            return;
        }
    }
}
