package com.be.DTO.TaskMain;

import lombok.*;

import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTask",scope = Task.class)
public class Task {

    private int idTask;
    private int idCreatedBy;
    private int code;
    private String name;
    private String priority;
    private String status;
    private String description;
    private LocalDate createDate;
    private LocalDate updateDate;
    private LocalDate dueDate;
    private LocalDate estimationDate;

    private SortedSet<Comment> taskComments=new TreeSet<>();
    private User taskUser;
    private Project taskProject;

}
