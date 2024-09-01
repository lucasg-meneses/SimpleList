package com.lucasgmeneses.simplelist.api.model.task;

import com.lucasgmeneses.simplelist.api.model.auth.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "task", name = "TB_TASK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private long position;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean checked;

    @ManyToOne
    private UserModel owner;

    @ManyToOne
    private TaskListModel tasklist;

    @Column
    private Date dateCreated;



}
