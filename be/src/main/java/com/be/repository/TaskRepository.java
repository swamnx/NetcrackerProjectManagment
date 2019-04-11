package com.be.repository;

import com.be.entity.Project;
import com.be.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task,Long> {
    //Page<Task> findAll(Pageable pageable);
    Page<Task> findAllByTaskProjectIn(Pageable pageable,Set<Project> set);
    Task findTaskByIdTask(Integer id);
    Task save(Task task);
    void delete(Task task);
}
