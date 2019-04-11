package com.fapi.DTO.TaskMain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMainMapper {

    TaskMainMapper INSTANCE = Mappers.getMapper(TaskMainMapper.class);
    Task taskToTaskDTO(com.fapi.DTO.Default.Task task);
    List<Task> tasksToTasksDTO(List<com.fapi.DTO.Default.Task> tasks);

}
