package com.akash.iam.taskserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasklist")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id ;
    String message ;
    //int order;
    Long createdTime;

    @ManyToOne(fetch = FetchType.LAZY) // Use EAGER if you want it auto-fetched
    @JoinColumn(name = "createdBy", referencedColumnName = "id")
    User createdBy;

    @Enumerated(EnumType.STRING)
    TASK_STATUS status ;

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public TASK_STATUS getStatus() {
        return status;
    }



    public Task setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public Task setMessage(String message) {
        this.message = message;
        return this;
    }

    public Task setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Task setStatus(TASK_STATUS status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createdTime=" + createdTime +
                ", createdBy=" + (createdBy != null ? createdBy.getId() : null) +
                ", status=" + status +
                '}';
    }


}
