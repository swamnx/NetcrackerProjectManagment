package com.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import lombok.*;
import org.hibernate.annotations.SortNatural;

@Data
@Table(name = "Users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idUser",scope = User.class)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idUser")
    @NotNull
    private int idUser;

    @Basic
    @Column(name = "name")
    @NotNull
    @Size(min = 1,max = 32)
    private String name;

    @Basic
    @Column(name = "email")
    @Size(min=1,max =60)
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    @Basic
    @Column(name = "password")
    @NotNull
    private String password;

    @Basic
    @Column(name = "role")
    @NotNull
    @Pattern(regexp = "^pm|tester|dev$")
    private String role;

    @SortNatural
    @ManyToMany
    @JoinTable(name = "us   er_and_project",
    joinColumns = @JoinColumn(name = "idUser"),
    inverseJoinColumns = @JoinColumn(name = "idProject"))
    private SortedSet<Project> userProjects = new TreeSet();

    @OneToMany(mappedBy = "taskUser")
    private Set<Task> userTasks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, name, email, password, role);
    }
}
