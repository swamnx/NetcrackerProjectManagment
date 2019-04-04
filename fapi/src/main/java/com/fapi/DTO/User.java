package com.fapi.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUser",scope = User.class)
public class User {

    private int idUser;
    private String name;
    private String email;
    private String password;
    private String role;
    Set<Project> userProjects;
    Set<Task> userTasks;

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Set<Project> getUserProjects() {
        return userProjects;
    }
    public void setUserProjects(Set<Project> userProjects) {
        this.userProjects = userProjects;
    }

    public Set<Task> getUserTasks() {
        return userTasks;
    }
    public void setUserTasks(Set<Task> userTasks) {
        this.userTasks = userTasks;
    }
}
