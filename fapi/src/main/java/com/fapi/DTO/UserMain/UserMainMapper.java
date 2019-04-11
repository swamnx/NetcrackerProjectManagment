package com.fapi.DTO.UserMain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMainMapper {

    UserMainMapper INSTANCE = Mappers.getMapper(UserMainMapper.class);
    User userToUserDTO(com.fapi.DTO.Default.User user);

}
