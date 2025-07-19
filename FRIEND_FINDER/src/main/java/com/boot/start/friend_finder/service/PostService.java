package com.boot.start.friend_finder.service;

import com.boot.start.friend_finder.model.Post;
import com.boot.start.friend_finder.service.dto.PostDto;
import jakarta.transaction.SystemException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    void savePost(PostDto postDto) throws SystemException;
    List<PostDto> getPosts() throws SystemException;
    PostDto getPost(Long id) throws SystemException;
    void deletePost(Long id) throws SystemException;

}
