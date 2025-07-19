package com.boot.start.friend_finder.mapper;

import com.boot.start.friend_finder.model.Comment;
import com.boot.start.friend_finder.model.Post;
import com.boot.start.friend_finder.service.dto.CommentDto;
import com.boot.start.friend_finder.service.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper comment_mapper = Mappers.getMapper(CommentMapper.class);

    Comment toEntity(CommentDto commentDto);
    CommentDto toDto(Comment comment);

    List<Comment> toEntityList(List<CommentDto> commentDtos);
    List<CommentDto> toDtoList(List<Comment>  comments);
}
