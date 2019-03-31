package com.be.repository;

import com.be.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAll();
    Task findTaskByIdTask(Integer id);
    Task save(Task task);
    void delete(Task task);
}
