package controllers;

import java.io.IOException;

import models.Task;
import utils.Logger;

public class TaskController {
    private Task task = new Task();
    private Logger logger = new Logger();

    public void addTask(String title) {
        try {
            task.add(title);
            logger.success("Add task successful\n");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Add task unsuccessful\n");
        }
    }
}
