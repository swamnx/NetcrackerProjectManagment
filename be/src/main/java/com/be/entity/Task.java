package com.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

import lombok.*;
import org.hibernate.annotations.SortNatural;

@Table(name ="Tasks")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTask",scope = Task.class)
public class Task{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idTask")
    private int idTask;

    @Basic
    @Column(name ="idCreatedBy")
    @NotNull
    private Integer idCreatedBy;

    @Basic
    @Column(name = "code")
    private Integer code;

    @Basic
    @Column(name = "name")
    @NotNull
    @Size(min=1,max=50)
    private String name;
    @Basic
    @Column(name = "priority")
    @NotNull
    @Pattern(regexp = "^low|normal|high$")
    private String priority;

    @Basic
    @Column(name = "status")
    @NotNull
    @Pattern(regexp = "^open|reopen|inProgress|readyForTest|closed|resolved$")
    private String status;

    @Basic
    @NotNull
    @Column(name = "description")
    @Size(min=1,max=300)
    private String description;

    @Basic
    @Column(name = "createDate")
    @NotNull
    private LocalDate createDate;

    @Basic
    @Column(name = "updateDate")
    @NotNull
    private LocalDate updateDate;

    @Basic
    @Column(name = "dueDate")
    @NotNull
    private LocalDate dueDate;

    @Basic
    @Column(name = "estimationDate")
    @NotNull
    private LocalDate estimationDate;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User taskUser;

    @SortNatural
    @OneToMany(mappedBy = "commentTask",cascade = CascadeType.ALL)
    private SortedSet<Comment> taskComments=new TreeSet<>();

    @ManyToOne
    @JoinColumn(name = "idProject")
    private Project taskProject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idTask == task.idTask;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTask,code,name, priority, status, description, createDate, updateDate, dueDate, estimationDate);
    }

}
