package com.akash.iam.taskserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "iam_user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id ;
    String name ;
    @Column(unique = true)
    String mail ;


    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }
}
