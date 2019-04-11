package com.be.repository;

import com.be.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositroy extends JpaRepository<Comment,Long> {
}
