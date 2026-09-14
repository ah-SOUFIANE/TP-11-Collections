package ma.projet;

import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String description, int priority) {
        tasks.add(new Task(description, priority));
    }

    public boolean removeTask(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

    public boolean updateStatus(int id, Status newStatus) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }

    public List<Task> listTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> filterByStatus(Status status) {
        return tasks.stream()
                     .filter(t -> t.getStatus() == status)
                     .collect(Collectors.toList());
    }

    public List<Task> filterByPriority(int priority) {
        return tasks.stream()
                     .filter(t -> t.getPriority() == priority)
                     .collect(Collectors.toList());
    }

    public void sortByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }
}