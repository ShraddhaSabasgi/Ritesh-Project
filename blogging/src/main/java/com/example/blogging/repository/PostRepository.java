package com.example.blogging.repository;

import com.example.blogging.entity.Category;
import com.example.blogging.entity.Post;
import com.example.blogging.entity.User;
import com.example.blogging.payloads_dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
