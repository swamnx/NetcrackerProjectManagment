package com.fapi.DTO.TaskMain;

import com.fapi.DTO.Default.Task;
import com.fapi.DTO.Default.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int idComment;
    private String comment;
    private User commentUser;

}
