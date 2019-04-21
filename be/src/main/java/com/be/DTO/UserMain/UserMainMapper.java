package com.be.DTO.UserMain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMainMapper {

    UserMainMapper INSTANCE = Mappers.getMapper(UserMainMapper.class);

    User userToUserDTO(com.be.entity.User user);
    List<User> usersToUserDTOs(List<com.be.entity.User> users);

    User userWithPasswordToUserDTO(com.be.DTO.UserMain.UserWithPassword userWithPassword);
    UserWithPassword userToUserWithPasswordDTO(com.be.entity.User user);
    List<UserWithPassword> usersToUserWithPasswordDTOs(List<com.be.entity.User> users);

    UserAuth userToUserAuthDTO(com.be.entity.User user);

}
