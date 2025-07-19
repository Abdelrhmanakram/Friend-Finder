package com.boot.start.friend_finder.service.Impl;

import com.boot.start.friend_finder.mapper.PostMapper;
import com.boot.start.friend_finder.model.Post;
import com.boot.start.friend_finder.model.User;
import com.boot.start.friend_finder.repository.PostRepository;
import com.boot.start.friend_finder.service.PostService;
import com.boot.start.friend_finder.service.dto.PostDto;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
   private PostRepository postRepository;


    @Override
    public void savePost(PostDto postDto) throws SystemException{
        if (postDto.getId() != null){
            throw new SystemException("id must be null");
        }

        Post post = PostMapper.post_mapper.toEntity(postDto);
        User user = new User();
        user.setId(postDto.getUserId());
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public List<PostDto> getPosts() throws SystemException{

        List<Post> posts = postRepository.findAll();

        if (posts.isEmpty()){
            throw new RuntimeException("error.posts");
        }else {
            List<PostDto> postDtos = PostMapper.post_mapper.toDtoList(posts);
            return postDtos;
        }
    }

    @Override
    public PostDto getPost(Long id) throws SystemException {
       Optional<Post> post = postRepository.findById(id);
       if (!post.isPresent()){
           throw new RuntimeException("error.post");
       }
       return PostMapper.post_mapper.toDto(post.get());
    }

    @Override
    public void deletePost(Long id) throws SystemException {

        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent()){
            throw new RuntimeException("error.post");
        }
        postRepository.deleteById(id);
    }
}
