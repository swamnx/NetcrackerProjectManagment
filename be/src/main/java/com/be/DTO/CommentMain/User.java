package com.be.DTO.CommentMain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUser",scope = User.class)
public class User {

    private int idUser;
    private String name;
    private String role;
    private String email;
}
