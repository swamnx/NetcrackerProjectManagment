package com.fapi.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProject",scope = Project.class)
public class Project {

    private int idProject;
    private String code;
    private String description;
    private Set<User> projectUsers;
    private Set<Task> projectTasks;

    public int getIdProject() {
        return idProject;
    }
    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getProjectUsers() {
        return projectUsers;
    }
    public void setProjectUsers(Set<User> projectUsers) {
        this.projectUsers = projectUsers;
    }

    public Set<Task> getProjectTasks() {
        return projectTasks;
    }
    public void setProjectTasks(Set<Task> projectTasks) {
        this.projectTasks = projectTasks;
    }
}
