package com.fapi.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    private int idProject;
    private String codeProject;
    private String descriptionProject;
    private Set<User> projectUsers;
    private Set<Task> projectTasks;

    public int getIdProject() {
        return idProject;
    }
    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getCodeProject() {
        return codeProject;
    }
    public void setCodeProject(String code) {
        this.codeProject = code;
    }

    public String getDescriptionProject() {
        return descriptionProject;
    }
    public void setDescriptionProject(String descriptionProject) {
        this.descriptionProject = descriptionProject;
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
