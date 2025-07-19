package com.boot.start.friend_finder.service.Impl;

import com.boot.start.friend_finder.mapper.CommentMapper;
import com.boot.start.friend_finder.model.Comment;
import com.boot.start.friend_finder.model.Post;
import com.boot.start.friend_finder.model.User;
import com.boot.start.friend_finder.repository.CommentRepository;
import com.boot.start.friend_finder.service.CommentService;
import com.boot.start.friend_finder.service.dto.CommentDto;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void  saveComment(CommentDto commentDto) throws SystemException {

        if(commentDto.getId() != null){
            throw new SystemException("id must be null");
        }

        Comment comment = CommentMapper.comment_mapper.toEntity(commentDto);
        User user = new User();
        user.setId(commentDto.getUserId());
        comment.setUser(user);
        Post post = new Post();
        post.setId(commentDto.getPostId());
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments(Long postId) throws SystemException {

        List<Comment> comments = commentRepository.findByPostId(postId);
        if (comments.isEmpty()) {
            throw new RuntimeException("error.comments");
        }else {
            List<CommentDto> commentDtos = CommentMapper.comment_mapper.toDtoList(comments);
            return commentDtos;
        }
    }

    @Override
    public CommentDto findCommentById(Long id) throws SystemException {
       Optional<Comment> comment = commentRepository.findById(id);
       if (comment.isEmpty()){
           throw new RuntimeException("error.comment");
       }
       return CommentMapper.comment_mapper.toDto(comment.get());
    }

    @Override
    public void deleteCommentById(Long id) throws SystemException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()){
            throw new RuntimeException("error.comment");
        }
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDto> findAllCommentsByUserId(Long userId) throws SystemException {
        List<Comment> comments = commentRepository.findByUserId(userId);
        if (comments.isEmpty()) {
            throw new RuntimeException("error.comments");
        }
        List<CommentDto> commentDtos = CommentMapper.comment_mapper.toDtoList(comments);
        return commentDtos;
    }
}
