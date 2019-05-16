package com.fapi.DTO.TaskMain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    private List<Comment> taskComments;
    private User taskUser;
    private Project taskProject;

}
