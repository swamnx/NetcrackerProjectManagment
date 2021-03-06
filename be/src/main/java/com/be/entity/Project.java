package com.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;


@Table(name = "Projects")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProject",scope = Project.class)
public class Project implements Comparable<Project> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idProject")
    private Integer idProject;

    @Basic
    @Column(name ="code")
    @NotNull
    @Size(min=1,max=50)
    private String code;

    @Basic
    @Column(name = "description")
    @Size(min=1,max=300)
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
        return idProject == project.idProject;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProject, code, description);
    }

    @Override
    public int compareTo(Project o) {
        return this.code.compareTo(o.code);
    }
}
