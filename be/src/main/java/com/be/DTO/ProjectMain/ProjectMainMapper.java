package com.be.DTO.ProjectMain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProjectMainMapper {

    ProjectMainMapper INSTANCE = Mappers.getMapper(ProjectMainMapper.class);
    Project projectToProjectDTO(com.be.entity.Project project);
    List<Project> projectsToProjectsDTO(List<com.be.entity.Project> projects);
    Set<Project> projectsToProjectsDTO(Set<com.be.entity.Project> projects);

}
