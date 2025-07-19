package com.boot.start.friend_finder.mapper;

import com.boot.start.friend_finder.model.Post;
import com.boot.start.friend_finder.service.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

    PostMapper post_mapper = Mappers.getMapper(PostMapper.class);


    Post toEntity(PostDto postDto);
    PostDto toDto(Post post);

    List<Post> toEntityList(List<PostDto> postDtos);
    List<PostDto> toDtoList(List<Post>  posts);
}
