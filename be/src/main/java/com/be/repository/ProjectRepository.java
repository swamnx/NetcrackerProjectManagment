package com.be.repository;

import com.be.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findProjectByCode(String code);
    Project findProjectByIdProject(Integer id);
    Project save(Project project);
    void delete(Project project);
}
