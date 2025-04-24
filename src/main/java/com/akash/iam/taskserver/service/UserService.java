package com.akash.iam.taskserver.service;

import com.akash.iam.taskserver.model.User;
import com.akash.iam.taskserver.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo repo ;

    public User getUserForEmail(String mail) {
        return  repo.getUserByMail(mail);
    }

    public void addUser(User user) {
        repo.save(user);
    }
}
