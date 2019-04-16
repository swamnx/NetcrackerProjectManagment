package com.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import lombok.*;

@Table(name ="Tasks")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private int code;

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

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User taskUser;

    @OneToMany(mappedBy = "commentTask",cascade = CascadeType.ALL)
    private Set<Comment> taskComments;

    @ManyToOne
    @JoinColumn(name = "idProject")
    private Project taskProject;

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
