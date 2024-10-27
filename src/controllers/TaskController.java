package controllers;

import java.util.List;
import java.util.ArrayList;

import database.TaskManager;
import models.Task;
import utils.Logger;

public class TaskController {
    private TaskManager taskManager = new TaskManager();

    public void addTask(String title) {
        try {
            this.taskManager.create(title, "todo");
            Logger.success("Add task successful");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Add task unsuccessful");
        }
    }

    public List<Task> getTasks(String status) {
        try {
            Logger.success("Get all tasks successful");
            return this.taskManager.findAll(status);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Get all tasks unsuccessful");
            return new ArrayList<Task>();
        }
    }

    public void updateTask(String id, String title) {
        try {
            this.taskManager.update(id, title, null);
            Logger.success("Update task successful");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Update task unsuccessful");
        }
    }

    public void updateStatus(String id, String status) {
        try {
            this.taskManager.update(id, null, status);
            Logger.success("Update status task successful");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Update status task unsuccessful");
        }
    }

    public void deleteTask(String id) {
        try {
            this.taskManager.delete(id);
            Logger.success("Delete task successful");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Delete task unsuccessful");
        }
    }
}
