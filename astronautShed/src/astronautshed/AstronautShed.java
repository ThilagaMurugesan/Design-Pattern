/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package astronautshed;
import java.util.Scanner;

public class AstronautShed implements TaskObserver {

    public static void main(String[] args) {
        AstronautShed app = new AstronautShed();
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(app);
        app.run();
    }

    public void run() {
        ScheduleManager manager = ScheduleManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Edit Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. View Tasks by Priority");
            System.out.println("0. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter start time (HH:MM): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (HH:MM): ");
                    String endTime = scanner.nextLine();
                    System.out.print("Enter priority: ");
                    String priority = scanner.nextLine();
                    Task task = TaskFactory.createTask(description, startTime, endTime, priority);
                    manager.addTask(task);
                    break;
                case 2:
                    System.out.print("Enter description of the task to remove: ");
                    String descToRemove = scanner.nextLine();
                    manager.removeTask(descToRemove);
                    break;
                case 3:
                    manager.viewTasks();
                    break;
                case 4:
                    System.out.print("Enter description of the task to edit: ");
                    String descToEdit = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new start time (HH:MM): ");
                    String newStartTime = scanner.nextLine();
                    System.out.print("Enter new end time (HH:MM): ");
                    String newEndTime = scanner.nextLine();
                    System.out.print("Enter new priority: ");
                    String newPriority = scanner.nextLine();
                    manager.editTask(descToEdit, newDescription, newStartTime, newEndTime, newPriority);
                    break;
                case 5:
                    System.out.print("Enter description of the task to mark as completed: ");
                    String descToComplete = scanner.nextLine();
                    manager.markTaskAsCompleted(descToComplete);
                    break;
                case 6:
                    System.out.print("Enter priority level to view: ");
                    String priorityLevel = scanner.nextLine();
                    manager.viewTasksByPriority(priorityLevel);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}

