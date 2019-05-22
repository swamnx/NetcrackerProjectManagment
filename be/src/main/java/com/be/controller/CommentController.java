package com.be.controller;

import com.be.DTO.CommentMain.СommentMainMapper;
import com.be.DTO.TaskMain.Comment;
import com.be.DTO.TaskMain.TaskMainMapper;

import com.be.entity.Task;
import com.be.entity.User;
import com.be.repository.CommentRepositroy;
import com.be.repository.TaskRepository;
import com.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentRepositroy commentRepositroy;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    ResponseEntity<Comment> createComment(@Valid @RequestBody com.be.DTO.CommentMain.Comment commentDTO){
        com.be.entity.Comment comment = СommentMainMapper.INSTANCE.commentDTOtoComment(commentDTO);
        Task foundTask = taskRepository.findTaskByIdTask(comment.getCommentTask().getIdTask());
        User foundUser = userRepository.findUserByIdUser(comment.getCommentUser().getIdUser());
        if(foundTask == null || foundUser == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(!foundTask.getTaskProject().getProjectUsers().contains(foundUser)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        com.be.entity.Comment createdComment = commentRepositroy.save(comment);
        createdComment.getCommentUser().setEmail(foundUser.getEmail());
        createdComment.getCommentUser().setRole(foundUser.getRole());
        createdComment.getCommentUser().setName(foundUser.getName());
        return new ResponseEntity<>(TaskMainMapper.INSTANCE.commentToCommentDTO(createdComment),HttpStatus.OK);
    }
}
