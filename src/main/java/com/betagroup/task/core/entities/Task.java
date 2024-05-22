package com.betagroup.task.core.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class Task {
    private String id;
    private String title;
    private String description;
    private boolean isDone;
    private Date dueDate;
    private Date createDate;
}
