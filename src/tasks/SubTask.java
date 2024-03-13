package tasks;

import enums.TaskStatus;
import enums.TaskTypes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;


public class SubTask extends Task {
    private final int epicId;
    private final TaskTypes type;

    public SubTask(String title, int id, String description, int epicId, TaskStatus status, LocalDateTime startTime,
                   Duration duration) {
        super(title, id, description, status, startTime, duration);
        this.epicId = epicId;
        this.type = TaskTypes.SUBTASK;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Подзадача (SubTask){" +
                "Название: '" + title + '\'' +
                ". Описание: '" + description + '\'' +
                ". ID: '" + id +
                "'. Epic ID: '" + epicId +
                "'. Статус: '" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + getEndTime() + '\'' +
                ", duration='" + duration +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubTask)) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return getEpicId() == subTask.getEpicId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEpicId());
    }

    @Override
    public String toStringFileBacked() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                id, type, title, status, description, startTime, duration, endTime, epicId + "\n");
    }
}