package com.fapi.DTO.TaskMain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskForProjectTable {
    private int code;
    private String priority;
    private String status;
    private String description;
    private User taskUser;
}
