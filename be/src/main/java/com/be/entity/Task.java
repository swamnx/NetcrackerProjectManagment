package com.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table(name ="Tasks")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTask",scope = Task.class)
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idTask")
    private int idTask;

    @Basic
    @Column(name ="idCreatedBy")
    private int idCreatedBy;

    @Basic
    @Column(name = "code")
    private String code;

    @Basic
    @Column(name = "priority")
    private String priority;

    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "createDate")
    private LocalDate createDate;

    @Basic
    @Column(name = "updateDate")
    private LocalDate updateDate;

    @Basic
    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Basic
    @Column(name = "estimationDate")
    private LocalDate estimationDate;

    //@JsonManagedReference(value = "task-User")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User taskUser;

    //@JsonManagedReference(value = "task-Project")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProject")
    private Project taskProject;

    public int getIdTask() {
        return idTask;
    }
    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getIdCreatedBy() {
        return idCreatedBy;
    }
    public void setIdCreatedBy(int idCreatedBy) {
        this.idCreatedBy = idCreatedBy;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idTask == task.idTask &&
                Objects.equals(code, task.code) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(status, task.status) &&
                Objects.equals(description, task.description) &&
                Objects.equals(createDate, task.createDate) &&
                Objects.equals(updateDate, task.updateDate) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(estimationDate, task.estimationDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTask, code, priority, status, description, createDate, updateDate, dueDate, estimationDate);
    }
}
