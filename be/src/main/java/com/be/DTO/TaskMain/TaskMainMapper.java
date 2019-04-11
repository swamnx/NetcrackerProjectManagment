package com.be.DTO.TaskMain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMainMapper {

    TaskMainMapper INSTANCE = Mappers.getMapper(TaskMainMapper.class);
    Task taskToTaskDTO(com.be.entity.Task task);
    List<Task> tasksToTasksDTO(List<com.be.entity.Task> tasks);

}
