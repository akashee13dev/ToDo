package com.akash.iam.taskserver.controler;

import com.akash.iam.taskserver.model.Task;
import com.akash.iam.taskserver.model.User;
import com.akash.iam.taskserver.service.TaskService;
import com.akash.iam.taskserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class TaskControler {

    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasksByHeaderAuthentication(@RequestHeader("X-Mail-Auth") String email){
        System.out.println(email);
        User user = userService.getUserForEmail(email);
        if(Objects.nonNull(user)){
            List<Task> tasks = taskService.getTasksForCurrentUser(user);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(List.of());
    }


    @PostMapping("/task")
    public ResponseEntity<Task> addTaskByHeaderAuthentication(@RequestHeader("X-Mail-Auth") String email , @RequestBody Task task){
        System.out.println(email);
        User user = userService.getUserForEmail(email);
        if(Objects.nonNull(user)){
            System.out.println(task.toString());
            Task tasks = taskService.addTaskForCurrentUser(task,user);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


}
