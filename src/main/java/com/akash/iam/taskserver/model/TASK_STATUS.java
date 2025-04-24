package com.akash.iam.taskserver.model;

public enum TASK_STATUS {
    OPEN(0),
    COMPLETED(1),
    DELETED(2);

    private final int value;

    TASK_STATUS(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
