package com.lucasgmeneses.simplelist.api.controller.task;

import com.lucasgmeneses.simplelist.api.dto.task.TaskRequestDTO;
import com.lucasgmeneses.simplelist.api.dto.task.TaskResponseDTO;
import com.lucasgmeneses.simplelist.api.model.auth.UserModel;
import com.lucasgmeneses.simplelist.api.model.task.TaskListModel;
import com.lucasgmeneses.simplelist.api.model.task.TaskModel;
import com.lucasgmeneses.simplelist.api.repository.task.TaskListRepository;
import com.lucasgmeneses.simplelist.api.repository.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskListRepository taskListRepository;

    @GetMapping
    public ResponseEntity getAllTask(@AuthenticationPrincipal UserModel user){

        List<TaskModel> taskModels = taskRepository.findByOwner(user).orElse(null);
        if(taskModels != null)
            return ResponseEntity
                    .ok(taskModels.stream()
                    .map(task -> new TaskResponseDTO(task.getId(),
                            task.getPosition(),
                            task.getDescription(),
                            task.isChecked(), task.getTasklist().getId(),
                            task.getDateCreated())).toList());


        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity registerTask(@RequestBody TaskRequestDTO taskRequestDTO){
        try {
            TaskModel taskModel = new TaskModel();
            taskModel.setChecked(taskRequestDTO.checked());
            taskModel.setPosition(taskRequestDTO.position());
            taskModel.setDescription(taskRequestDTO.description());
            TaskListModel taskListModel = taskListRepository.findById(taskRequestDTO.taskListId()).orElse(null);
            if (taskListModel == null) {
                return ResponseEntity.badRequest().build();
            }
            taskModel.setOwner(taskListModel.getOwner());
            taskModel.setTasklist(taskListModel);
            taskRepository.save(taskModel);

            return ResponseEntity.ok(new TaskResponseDTO(taskModel.getId(),taskModel.getPosition(),
                    taskModel.getDescription(), taskModel.isChecked(),
                    taskListModel.getId(),taskModel.getDateCreated()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }


    }
    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable String id, @RequestBody TaskRequestDTO taskRequestDTO){
        try {
            TaskModel taskModel = taskRepository.findById(id).orElse(null);
            if (taskModel == null) {
                return ResponseEntity.badRequest().build();
            }


            taskModel.setChecked(taskRequestDTO.checked());
            taskModel.setPosition(taskRequestDTO.position());
            taskModel.setDescription(taskRequestDTO.description());
            taskRepository.save(taskModel);

            return ResponseEntity.ok(new TaskResponseDTO(taskModel.getId(),taskModel.getPosition(),
                    taskModel.getDescription(), taskModel.isChecked(),
                    taskModel.getTasklist().getId(),taskModel.getDateCreated()));

        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTaskList(@AuthenticationPrincipal UserModel user,
                                         @PathVariable String id){
        try {
            TaskModel taskModel = taskRepository.findById(id).orElseThrow();

            if(!taskModel.getOwner().getId().equals(user.getId())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            taskRepository.delete(taskModel);
            return ResponseEntity.ok(new TaskResponseDTO(taskModel.getId(),taskModel.getPosition(),
                    taskModel.getDescription(), taskModel.isChecked(),
                    taskModel.getTasklist().getId(),taskModel.getDateCreated()));

        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

    }
}
