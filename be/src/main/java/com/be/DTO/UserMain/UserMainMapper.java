package com.be.DTO.UserMain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMainMapper {

    UserMainMapper INSTANCE = Mappers.getMapper(UserMainMapper.class);
    User userToUserDTO(com.be.entity.User user);
    List<User> usersToUserDTOs(List<com.be.entity.User> users);

}
