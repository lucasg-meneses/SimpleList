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
import java.util.List;

@Entity
@Table(schema = "task", name = "TB_TASK_LIST")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskListModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column
    private String title;

    @Column(length = 7)
    private String color;// color hex

    @OneToMany(mappedBy = "tasklist", fetch = FetchType.EAGER)
    private List<TaskModel> tasks;

    @ManyToOne
    private UserModel owner;

    @Column
    private Date dateCreated;

    @Column
    private Date dateUpdated;

}
