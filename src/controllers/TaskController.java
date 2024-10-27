package controllers;

import java.util.List;
import java.util.ArrayList;

import database.TaskManager;
import models.Task;
import utils.Logger;

public class TaskController {

    public static void addTask(String title) {
        try {
            TaskManager.create(title, "todo");
            Logger.success("Add task successful");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Add task unsuccessful");
        }
    }

    public static List<Task> getTasks(String status) {
        try {
            Logger.success("Get all tasks successful");
            return TaskManager.findAll(status);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Get all tasks unsuccessful");
            return new ArrayList<Task>();
        }
    }

    public static void updateTask(String id, String title) {
        try {
            TaskManager.update(id, title, null);
            Logger.success("Update task successful");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Update task unsuccessful");
        }
    }

    public static void updateStatus(String id, String status) {
        try {
            TaskManager.update(id, null, status);
            Logger.success("Update status task successful");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Update status task unsuccessful");
        }
    }

    public static void deleteTask(String id) {
        try {
            TaskManager.delete(id);
            Logger.success("Delete task successful");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Delete task unsuccessful");
        }
    }
}
