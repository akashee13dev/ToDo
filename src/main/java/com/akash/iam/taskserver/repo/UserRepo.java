package com.akash.iam.taskserver.repo;

import com.akash.iam.taskserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User getUserByMail(String mail);
}
