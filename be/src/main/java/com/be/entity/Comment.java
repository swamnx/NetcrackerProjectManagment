package com.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Table(name = "Comments")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComment",scope = Comment.class)
public class Comment implements Comparable<Comment> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idComment")
    private int idComment;

    @Basic
    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "idTask")
    private Task commentTask;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User commentUser;

    @Basic
    @Column(name="date")
    private LocalDateTime date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return idComment == comment1.idComment &&
                Objects.equals(comment, comment1.comment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idComment, comment);
    }
    @Override
    public int compareTo(Comment comment){
        return this.date.compareTo(comment.date);
    }
}
