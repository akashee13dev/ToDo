package com.akash.iam.taskserver.service;

import com.akash.iam.taskserver.model.Task;
import com.akash.iam.taskserver.model.User;
import com.akash.iam.taskserver.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    public List<Task> getTasksForCurrentUser(User user) {
        return taskRepo.findByCreatedBy(user);
    }

    public Task addTaskForCurrentUser(Task task, User user) {
        task.setCreatedTime(Instant.now().getEpochSecond());
        task.setCreatedBy(user);
        return taskRepo.save(task);
    }
}
