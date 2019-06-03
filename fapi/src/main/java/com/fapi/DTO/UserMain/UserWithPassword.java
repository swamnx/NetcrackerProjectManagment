package com.fapi.DTO.UserMain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPassword {

    private int idUser;
    private String name;
    private String role;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @Size(min=1,max =60)
    private String email;
    @NotNull
    @Size(min=8,max=32)
    @Pattern(regexp = "^(?=.*[0-9].*)(?=.*[a-z].*)(?=.*[A-Z].*)[0-9a-zA-Z!@#$%^&*_+-=;.,%:?*]{8,32}$")
    private String password;

}
