package com.be.DTO.CommentMain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface СommentMainMapper {

    СommentMainMapper INSTANCE = Mappers.getMapper(СommentMainMapper.class);
    com.be.entity.Comment commentDTOtoComment(Comment comment);
}
