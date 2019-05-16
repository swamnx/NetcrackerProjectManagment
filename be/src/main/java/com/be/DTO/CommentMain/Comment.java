package com.be.DTO.CommentMain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComment",scope = Comment.class)
public class Comment implements Comparable<Comment> {

    private int idComment;
    private String comment;
    private LocalDateTime date;

    private User commentUser;
    private Task commentTask;
    @Override
    public int compareTo(Comment comment){
        return this.date.compareTo(comment.date);
    }
}
