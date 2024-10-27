package models;

import com.google.gson.JsonObject;

public class Task {
    private String id;
    private String title;
    private String status;

    public Task(String id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public Task(JsonObject obj) {
        this.id = obj.get("id").getAsString();
        this.title = obj.get("title").getAsString();
        this.status = obj.get("status").getAsString();
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}