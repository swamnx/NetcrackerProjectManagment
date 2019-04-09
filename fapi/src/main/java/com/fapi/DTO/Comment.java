package com.fapi.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComment",scope = Comment.class)
public class Comment {

    private int idComment;
    private String comment;
    private Task commentTask;
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
