package com.lucasgmeneses.simplelist.api.repository.task;

import com.lucasgmeneses.simplelist.api.model.task.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, String> {
}
