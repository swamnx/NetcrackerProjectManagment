package com.be.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "Projects")
@Entity
public class Project {

    @Id
    @Column(name = "idProject")
    private int idProject;

    @Basic
    @Column(name ="codeProject")
    private String codeProject;

    @Basic
    @Column(name = "descriptionProject")
    private String descriptionProject;

    @ManyToMany(mappedBy = "userProjects")
    private Set<User> projectUsers;

    @OneToMany(mappedBy = "taskProject",cascade = CascadeType.ALL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return idProject == project.idProject &&
                Objects.equals(codeProject, project.codeProject) &&
                Objects.equals(descriptionProject, project.descriptionProject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProject, codeProject, descriptionProject);
    }
}
