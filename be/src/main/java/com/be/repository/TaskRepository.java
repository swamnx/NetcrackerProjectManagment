package com.be.repository;

import com.be.entity.Project;
import com.be.entity.Task;
import com.be.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task,Long> {
    Page<Task> findAllByTaskProjectInAndNameContains(Pageable pageable,Set<Project> set,String search);
    Page<Task> findAllByTaskUserAndNameContains(Pageable pageable, User user,String search);
    Page<Task> findAllByTaskProjectAndNameContains(Pageable pageable,Project project,String search);
    Page<Task> findAllByNameContains(Pageable pageable, String search);

    Task findTaskByIdTask(Integer id);
    Task save(Task task);
    void delete(Task task);
}
