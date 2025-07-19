package com.boot.start.friend_finder.controller;

import com.boot.start.friend_finder.service.PostService;
import com.boot.start.friend_finder.service.dto.PostDto;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create/post")
    ResponseEntity<Void> createPost(@RequestBody @Validated PostDto postDto) throws SystemException{

        postService.savePost(postDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<PostDto>> getPosts() throws SystemException{
//        try{
            List<PostDto> posts = postService.getPosts();
            return ResponseEntity.ok().body(posts);
//        }catch (SystemException e) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT)
//                    .body(Collections.emptyList());
//        }
    }

    @GetMapping("/{id}")
    ResponseEntity<PostDto> getPost(@PathVariable Long id) throws SystemException{
//        try{
            PostDto postDto = postService.getPost(id);
            return ResponseEntity.ok().body(postDto);
//        } catch (SystemException e) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePost(@PathVariable Long id) throws SystemException{
//        try{
            postService.deletePost(id);
            return ResponseEntity.ok().build();
//        } catch (SystemException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
    }
}
