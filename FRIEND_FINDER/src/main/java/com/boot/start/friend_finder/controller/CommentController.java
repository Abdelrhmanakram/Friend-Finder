package com.boot.start.friend_finder.controller;

import com.boot.start.friend_finder.service.CommentService;
import com.boot.start.friend_finder.service.dto.CommentDto;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    ResponseEntity<Void> createComment(@RequestBody @Validated CommentDto commentDto) throws SystemException {
        commentService.saveComment(commentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteComment(@PathVariable Long id) throws SystemException {
//        try{
            commentService.deleteCommentById(id);
            return ResponseEntity.ok().build();
//        }catch (Exception e){
//            throw new SystemException(e.getMessage());
//        }
    }

    @GetMapping("/{id}")
    ResponseEntity<CommentDto> getComment(@PathVariable Long id) throws SystemException {
//        try{
            commentService.findCommentById(id);
            return ResponseEntity.ok().body(commentService.findCommentById(id));
//        }catch (Exception e){
//            throw new SystemException(e.getMessage());
//        }
    }


    @GetMapping("/posts/{postId}/comments")
    ResponseEntity<List<CommentDto>> getComments(@PathVariable Long postId) throws SystemException {
        List<CommentDto> commentDtos = commentService.findAllComments(postId);
        return ResponseEntity.ok().body(commentDtos);
    }


    @GetMapping("/user/{userId}")
    ResponseEntity<List<CommentDto>> getCommentByUserId(@PathVariable Long userId) throws SystemException{
        List<CommentDto> commentDtos = commentService.findAllCommentsByUserId(userId);
        return ResponseEntity.ok().body(commentDtos);
    }
}
