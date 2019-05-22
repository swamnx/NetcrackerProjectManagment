package com.be.DTO.CommentMain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComment",scope = Comment.class)
public class Comment implements Comparable<Comment> {

    private Integer idComment;
    @Size(min=1,max=255)
    private String comment;
    @NotNull
    private LocalDateTime date;

    @NotNull
    private User commentUser;
    @NotNull
    private Task commentTask;
    @Override
    public int compareTo(Comment comment){
        return this.date.compareTo(comment.date);
    }
}
