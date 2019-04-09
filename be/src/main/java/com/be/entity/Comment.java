package com.be.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Table(name = "Comments")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComment",scope = User.class)
public class Comment {
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

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Task getCommentTask() {
        return commentTask;
    }

    public void setCommentTask(Task commentTask) {
        this.commentTask = commentTask;
    }
}
