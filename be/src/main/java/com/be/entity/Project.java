package com.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "Projects")
@Entity
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
    @JsonBackReference
    private Set<User> projectUsers;

    @OneToMany(mappedBy = "taskProject",cascade = CascadeType.ALL)
    @JsonBackReference
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
