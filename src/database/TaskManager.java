package database;

import java.util.List;
import java.util.stream.Collectors;

import models.Task;

public class TaskManager extends Manager<Task> {
    private static String filePath = "src/database/tasks.json";
    private static String table = "tasks";
    private static List<Task> tasks;

    public TaskManager() {
        super(table, filePath, Task.class);
        tasks = getData();
    }

    public Task create(String title, String status) throws Exception {
        Task task = new Task(String.valueOf(System.currentTimeMillis()), title, status);
        tasks.add(task);
        setData(tasks);
        return task;
    }

    public boolean update(String id, String newTitle, String newStatus) throws Exception {
        Task task = this.findById(id);
        if (task == null) {
            throw new Exception("Task not found");
        }

        if (newTitle != null) {
            task.setTitle(newTitle);
        }
        if (newStatus != null) {
            task.setStatus(newStatus);
        }

        setData(tasks);
        return true;
    }

    public boolean delete(String id) throws Exception {
        Task task = this.findById(id);
        if (task == null) {
            throw new Exception("Task not found");
        }

        tasks.remove(task);

        setData(tasks);
        return true;
    }

    public Task findById(String id) throws Exception {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Task> findAll(String status) throws Exception {
        if (status != null) {
            return tasks.stream().filter(task -> task.getStatus().equals(status)).collect(Collectors.toList());
        }

        return tasks;
    }
}
