package com.fapi.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    private int idTask;
    private String code;
    private String priority;
    private String status;
    private String description;
    private LocalDate createDate;
    private LocalDate updateDate;
    private LocalDate dueDate;
    private LocalDate estimationDate;
    private User reporter;
    private User taskUser;
    private Project taskProject;

    public int getIdTask() {
        return idTask;
    }
    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public User getReporter() {
        return reporter;
    }
    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getEstimationDate() {
        return estimationDate;
    }
    public void setEstimationDate(LocalDate estimationDate) {
        this.estimationDate = estimationDate;
    }

    public User getTaskUser() {
        return taskUser;
    }
    public void setTaskUser(User taskUser) {
        this.taskUser = taskUser;
    }

    public Project getTaskProject() {
        return taskProject;
    }
    public void setTaskProject(Project taskProject) {
        this.taskProject = taskProject;
    }
}
