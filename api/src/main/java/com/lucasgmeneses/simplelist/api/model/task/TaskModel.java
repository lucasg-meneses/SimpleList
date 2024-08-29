package com.lucasgmeneses.simplelist.api.model.task;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(schema = "task", name = "TB_TASK")

public class TaskModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private long position;

    @Column
    private String description;

    @Column
    private boolean checked;

    @ManyToOne
    private TaskListModel tasklist;

    public TaskModel(long position, String description, boolean checked) {
        this.position = position;
        this.description = description;
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public TaskListModel getTasklist() {
        return tasklist;
    }

    public void setTasklist(TaskListModel tasklist) {
        this.tasklist = tasklist;
    }
}
