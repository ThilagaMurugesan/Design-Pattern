/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package astronautshed;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private List<TaskObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    public void addTask(Task task) {
        for (Task t : tasks) {
            if (tasksOverlap(t, task)) {
                notifyObservers("Error: Task conflicts with existing task \"" + t.getDescription() + "\".");
                return;
            }
        }
        tasks.add(task);
        notifyObservers("Task added successfully. No conflicts.");
    }

    public void removeTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                tasks.remove(task);
                notifyObservers("Task removed successfully.");
                return;
            }
        }
        notifyObservers("Error: Task not found.");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        Collections.sort(tasks, (t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()));
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void editTask(String description, String newDescription, String newStartTime, String newEndTime, String newPriority) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                LocalTime newStart = LocalTime.parse(newStartTime);
                LocalTime newEnd = LocalTime.parse(newEndTime);
                Task newTask = new Task(newDescription, newStart, newEnd, newPriority);
                tasks.remove(task);
                addTask(newTask);
                return;
            }
        }
        notifyObservers("Error: Task not found.");
    }

    public void markTaskAsCompleted(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                task.setCompleted(true);
                notifyObservers("Task marked as completed.");
                return;
            }
        }
        notifyObservers("Error: Task not found.");
    }

    public void viewTasksByPriority(String priority) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks with priority: " + priority);
        }
    }

    private boolean tasksOverlap(Task t1, Task t2) {
        return t1.getStartTime().isBefore(t2.getEndTime()) && t2.getStartTime().isBefore(t1.getEndTime());
    }

    private void notifyObservers(String message) {
        for (TaskObserver observer : observers) {
            observer.notify(message);
        }
    }
}

