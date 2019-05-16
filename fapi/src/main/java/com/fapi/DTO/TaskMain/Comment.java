package com.fapi.DTO.TaskMain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Comparable<Comment>{

    private int idComment;
    private String comment;
    private User commentUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    @Override
    public int compareTo(Comment comment){
        return this.date.compareTo(comment.date);
    }

}
