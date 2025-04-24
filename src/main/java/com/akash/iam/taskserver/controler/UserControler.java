package com.akash.iam.taskserver.controler;

import com.akash.iam.taskserver.model.User;
import com.akash.iam.taskserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class UserControler {

    @Autowired
    UserService userService ;

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam(name = "mail") String mail){
        System.out.println(mail);
        User user = userService.getUserForEmail(mail);
        if(Objects.isNull(user)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(user);
    }


    @PostMapping("/user")
    public ResponseEntity<Object> addUser(@RequestBody User user ){
        User existingUser = getUser(user.getMail() ).getBody();
        if (Objects.isNull(existingUser)) {
            String message = "Received user: " + user.getName() + ", " + user.getMail() + ", Id : "+ user.getId();
            System.out.println(message);
            userService.addUser(user);
            return ResponseEntity.ok(user);
        }
        String errorMessage = "User with email " + user.getMail() + " already exists.";
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
