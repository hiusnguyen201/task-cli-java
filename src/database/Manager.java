package database;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import utils.JsonFile;

public class Manager<T> {
    private String table;
    private String filePath;
    private List<T> data;

    public Manager(String table, String filePath, Class<T> type) {
        this.table = table;
        this.filePath = filePath;
        try {
            JsonArray jsonArr = new JsonFile().read(filePath).getAsJsonArray(table);
            Type listType = TypeToken.getParameterized(ArrayList.class, type).getType();
            data = new Gson().fromJson(jsonArr, listType);
        } catch (Exception e) {
            data = new ArrayList<T>();
        }
    }

    private void save(List<T> data) throws Exception {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(this.table, gson.toJsonTree(data).getAsJsonArray());
        (new JsonFile()).write(this.filePath, jsonObject);
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> newData) throws Exception {
        this.data = newData;
        save(this.data);
    }
}
