package com.fapi.DTO.Default;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
