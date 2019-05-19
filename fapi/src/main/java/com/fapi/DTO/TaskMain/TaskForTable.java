package com.fapi.DTO.TaskMain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskForTable {
    private int idTask;
    private int code;
    private String status;
    private String name;
    private String description;
    private LocalDate createDate;
    private LocalDate updateDate;
    private LocalDate dueDate;
    private LocalDate estimationDate;
    private User taskUser;
    private Project taskProject;
}
