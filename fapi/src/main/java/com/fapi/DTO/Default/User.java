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
public class User {

    private int idUser;
    private String name;
    private String email;
    private String password;
    private String role;

    Set<Project> userProjects;
    Set<Task> userTasks;

}
