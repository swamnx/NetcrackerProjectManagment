package com.fapi.DTO.CommentMain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComment",scope = Comment.class)
public class Comment {

    private int idComment;
    private String comment;

    private User commentUser;
    private Task commentTask;
}
