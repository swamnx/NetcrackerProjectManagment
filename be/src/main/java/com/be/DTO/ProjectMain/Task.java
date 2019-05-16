package com.be.DTO.ProjectMain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTask",scope = Task.class)
public class Task {

    private int idTask;
    private int idCreatedBy;
    private int code;
    private String priority;
    private String status;
    private String description;
    private LocalDate createDate;
    private LocalDate updateDate;
    private LocalDate dueDate;
    private LocalDate estimationDate;

    private User taskUser;

}
