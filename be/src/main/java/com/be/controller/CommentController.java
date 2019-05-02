package com.be.controller;

import com.be.DTO.CommentMain.СommentMainMapper;
import com.be.DTO.TaskMain.TaskMainMapper;
import com.be.entity.Comment;

import com.be.repository.CommentRepositroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentRepositroy commentRepositroy;

    @PostMapping("")
    ResponseEntity<com.be.DTO.TaskMain.Comment> createComment(@RequestBody com.be.DTO.CommentMain.Comment commentDTO){
        Comment comment = СommentMainMapper.INSTANCE.commentDTOtoComment(commentDTO);
        Comment createdComment = commentRepositroy.save(comment);
        System.out.println(createdComment.getCommentUser().getName());
        if(createdComment == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.commentToCommentDTO(createdComment),HttpStatus.OK);
    }
}
