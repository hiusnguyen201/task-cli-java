package controllers;

import java.io.IOException;

import com.google.gson.JsonArray;

import models.Task;
import utils.Logger;

public class TaskController {
    private Task task = new Task();
    private Logger logger = new Logger();

    public void addTask(String title) {
        try {
            task.add(title);
            logger.success("Add task successful");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Add task unsuccessful");
        }
    }

    public JsonArray getTasks() {
        try {
            logger.success("Get all tasks successful");
            return task.getAll();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Get all tasks unsuccessful");
            return new JsonArray();
        }
    }

    public JsonArray getTasksByStatus(String status) {
        try {
            logger.success("Get all tasks successful");
            return task.getAllByStatus(status);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Get all tasks unsuccessful");
            return new JsonArray();
        }
    }

    public void updateTask(String id, String title) {
        try {
            task.update(id, title);
            logger.success("Update task successful");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Update task unsuccessful");
        }
    }

    public void updateStatus(String id, String status) {
        try {
            task.updateStatus(id, status);
            logger.success("Update status task successful");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Update status task unsuccessful");
        }
    }

    public void deleteTask(String id) {
        try {
            task.delete(id);
            logger.success("Delete task successful");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Delete task unsuccessful");
        }
    }
}
