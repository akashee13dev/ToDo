package com.akash.iam.taskserver.controler;

import com.akash.iam.taskserver.model.Task;
import com.akash.iam.taskserver.model.User;
import com.akash.iam.taskserver.service.TaskService;
import com.akash.iam.taskserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/task/{taskId}")
    public ResponseEntity<Task> getTasksByHeaderAuthentication(@RequestHeader("X-Mail-Auth") String email , @PathVariable("taskId") Long taskId ){
        System.out.println(email);
        User user = userService.getUserForEmail(email);
        if(Objects.nonNull(user) && Objects.nonNull(taskId)){
            Task task = taskService.getTaskById(user , taskId);
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

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

    @PutMapping("/task/{taskId}/{action}")
    public ResponseEntity<String> doTaskActions(@RequestHeader("X-Mail-Auth") String email  , @PathVariable("taskId") String taskId , @PathVariable("action") String actionType){
        User user = userService.getUserForEmail(email);
        if(Objects.nonNull(user)){
            Task task = taskService.getTaskById(user , Long.valueOf(taskId));
            if(Objects.nonNull(task)){
                switch (actionType){
                    case "complete" : taskService.markComplete(task); break;
                    case "remove" : taskService.strikeTask(task); break;
                    case "reopen" : taskService.reOpen(task); break;
                }
                return ResponseEntity.ok("Success");
            }
            return ResponseEntity.badRequest().body("No Task for given ID");
        }
        return ResponseEntity.badRequest().body("No Authentication For the Application");
    }


}
