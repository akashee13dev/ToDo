package com.akash.iam.taskserver.repo;

import com.akash.iam.taskserver.model.Task;
import com.akash.iam.taskserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {

    List<Task> findByCreatedBy(User user);

    @Query("SELECT t from Task t where t.id = :id and t.createdBy = :user")
    Optional<Task> getTaskForUser(@Param("id") Long id, @Param("user") User user);


}
