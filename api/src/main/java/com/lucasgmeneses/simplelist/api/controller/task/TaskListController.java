package com.lucasgmeneses.simplelist.api.controller.task;

import com.lucasgmeneses.simplelist.api.dto.task.tasklist.TaskListRequestDTO;
import com.lucasgmeneses.simplelist.api.dto.task.tasklist.TaskListResponseDTO;
import com.lucasgmeneses.simplelist.api.dto.task.tasklist.TaskListUpdatedRequestDTO;
import com.lucasgmeneses.simplelist.api.model.auth.UserModel;
import com.lucasgmeneses.simplelist.api.model.task.TaskListModel;
import com.lucasgmeneses.simplelist.api.repository.task.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/tasklist")
public class TaskListController {
    @Autowired
    private TaskListRepository taskListRepository;

    @GetMapping
    public ResponseEntity getAllTaskList(@AuthenticationPrincipal UserModel user ){
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getTaskList(@PathVariable String id){
        TaskListModel taskList = taskListRepository.findById(id).orElse(null);
        if(taskList != null){
            return ResponseEntity.ok(new TaskListResponseDTO(taskList.getId(),
                    taskList.getTitle(),
                    taskList.getColor(),
                    taskList.getDateUpdated(),
                    taskList.getDateCreated()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity updateTaskList(@AuthenticationPrincipal UserModel user,
                                         @RequestBody TaskListUpdatedRequestDTO requestDTO){
        try {
            TaskListModel taskListModel = taskListRepository.findById(requestDTO.id()).orElseThrow();
            taskListModel.setColor(requestDTO.color());
            taskListModel.setTitle(requestDTO.title());
            taskListModel.setDateUpdated(new Date());

            taskListRepository.save(taskListModel);
            return ResponseEntity.ok(new TaskListResponseDTO(taskListModel.getId(),
                    taskListModel.getTitle(),
                    taskListModel.getColor(),
                    taskListModel.getDateUpdated(),
                    taskListModel.getDateCreated()));

        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity registerTaskList(@AuthenticationPrincipal UserModel user ,
                                           @RequestBody TaskListRequestDTO requestDTO){
        try {
            TaskListModel taskListModel = new TaskListModel();
            taskListModel.setColor(requestDTO.color());
            taskListModel.setOwner(user);
            taskListModel.setTitle(requestDTO.title());
            taskListModel.setDateCreated(new Date());
            taskListModel.setDateUpdated(new Date());
            taskListRepository.save(taskListModel);
            return ResponseEntity.ok(new TaskListResponseDTO(taskListModel.getId(),
                    taskListModel.getTitle(),
                    taskListModel.getColor(),
                    taskListModel.getDateUpdated(),
                    taskListModel.getDateCreated()));

        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTaskList(@AuthenticationPrincipal UserModel user,
                                         @PathVariable String id){
        try {
            TaskListModel taskListModel = taskListRepository.findById(id).orElseThrow();

            if(!taskListModel.getOwner().getId().equals(user.getId())){
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            taskListRepository.delete(taskListModel);
            return ResponseEntity.ok(new TaskListResponseDTO(taskListModel.getId(),
                    taskListModel.getTitle(),
                    taskListModel.getColor(),
                    taskListModel.getDateUpdated(),
                    taskListModel.getDateCreated()));

        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

    }

}
