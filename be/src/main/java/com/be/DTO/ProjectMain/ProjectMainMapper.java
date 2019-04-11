package com.be.DTO.ProjectMain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMainMapper {

    ProjectMainMapper INSTANCE = Mappers.getMapper(ProjectMainMapper.class);
    Project projectToProjectDTO(com.be.entity.Project project);

}
