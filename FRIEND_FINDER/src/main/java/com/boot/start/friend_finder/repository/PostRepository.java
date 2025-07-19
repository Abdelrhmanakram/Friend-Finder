package com.boot.start.friend_finder.repository;

import com.boot.start.friend_finder.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
