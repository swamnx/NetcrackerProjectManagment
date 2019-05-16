package com.be.controller;

import com.be.DTO.CommentMain.СommentMainMapper;
import com.be.DTO.TaskMain.Comment;
import com.be.DTO.TaskMain.TaskMainMapper;

import com.be.repository.CommentRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentRepositroy commentRepositroy;

    @PostMapping("")
    ResponseEntity<Comment> createComment(@RequestBody com.be.DTO.CommentMain.Comment commentDTO){
        com.be.entity.Comment comment = СommentMainMapper.INSTANCE.commentDTOtoComment(commentDTO);
        com.be.entity.Comment createdComment = commentRepositroy.save(comment);
        System.out.println(createdComment.getCommentUser().getName());
        if(createdComment == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.commentToCommentDTO(createdComment),HttpStatus.OK);
    }
}
