package com.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Table(name = "Projects")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProject",scope = Project.class)
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idProject")
    private int idProject;

    @Basic
    @Column(name ="code")
    private String code;

    @Basic
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "userProjects")
    private Set<User> projectUsers;

    @OneToMany(mappedBy = "taskProject")
    private Set<Task> projectTasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return idProject == project.idProject &&
                Objects.equals(code, project.code) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProject, code, description);
    }
}
