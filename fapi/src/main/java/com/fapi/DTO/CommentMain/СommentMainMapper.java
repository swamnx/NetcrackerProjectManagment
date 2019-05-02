package com.fapi.DTO.CommentMain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface СommentMainMapper {

    СommentMainMapper INSTANCE = Mappers.getMapper(СommentMainMapper.class);
    com.fapi.DTO.Default.Comment commentDTOtoComment(Comment comment);
}
