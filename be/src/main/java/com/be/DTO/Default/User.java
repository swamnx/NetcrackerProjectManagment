package com.be.DTO.Default;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUser",scope = User.class)
public class User {

    private int idUser;
    private String name;
    private String email;
    private String password;
    private String role;

    Set<Project> userProjects;
    Set<Task> userTasks;

}