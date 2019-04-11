package com.fapi.DTO.Default;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private int idProject;
    private String code;
    private String description;

    private Set<User> projectUsers;
    private Set<Task> projectTasks;

}
