package com.akash.iam.taskserver.repo;

import com.akash.iam.taskserver.model.Task;
import com.akash.iam.taskserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {

    List<Task> findByCreatedBy(User user);

}
