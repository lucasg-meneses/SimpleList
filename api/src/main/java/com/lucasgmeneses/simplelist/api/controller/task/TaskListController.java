package com.lucasgmeneses.simplelist.api.controller.task;

import com.lucasgmeneses.simplelist.api.repository.task.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tasklist")
public class TaskListController {
    @Autowired
    private TaskListRepository taskListRepository;

    @GetMapping
    public ResponseEntity getAllTaskList(@AuthenticationPrincipal User user ){


        return ResponseEntity.badRequest().build();
    }
}
