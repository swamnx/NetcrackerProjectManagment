package com.be.DTO.Default;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComment",scope = Comment.class)
public class Comment implements Comparable<Comment> {

    private int idComment;
    private String comment;
    private LocalDateTime date;
    private Task commentTask;
    private User commentUser;
    @Override
    public int compareTo(Comment comment){
        return this.date.compareTo(comment.date);
    }

}
