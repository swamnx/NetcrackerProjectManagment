package com.fapi.DTO.UserMain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {
    private int idUser;
    private String name;
    private String role;
    private String email;
}
