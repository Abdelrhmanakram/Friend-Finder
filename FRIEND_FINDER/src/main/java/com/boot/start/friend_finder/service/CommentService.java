package com.boot.start.friend_finder.service;

import com.boot.start.friend_finder.service.dto.CommentDto;
import jakarta.transaction.SystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void saveComment(CommentDto commentDto) throws SystemException;
    List<CommentDto> findAllComments(Long postId) throws SystemException;
    CommentDto findCommentById(Long id) throws SystemException;
    void deleteCommentById(Long id) throws SystemException;
    List<CommentDto> findAllCommentsByUserId(Long userId) throws SystemException;
}
