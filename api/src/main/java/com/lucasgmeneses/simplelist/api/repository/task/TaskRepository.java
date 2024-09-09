package com.lucasgmeneses.simplelist.api.repository.task;

import com.lucasgmeneses.simplelist.api.model.auth.UserModel;
import com.lucasgmeneses.simplelist.api.model.task.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, String> {
    Optional<List<TaskModel>> findByOwner(UserModel owner);
}
