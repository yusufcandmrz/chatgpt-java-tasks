package yusufcandmrz.tasktracker;

public class Task {

    private final int id;
    private String title;
    private Priority priority;
    private boolean completed;
    private static int taskNumber = 1;

    public Task(String title, Priority priority) {
        this.id = taskNumber++;
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
