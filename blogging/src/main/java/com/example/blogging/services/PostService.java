package com.example.blogging.services;

import com.example.blogging.entity.Post;
import com.example.blogging.payloads_dto.PostDto;
import com.example.blogging.payloads_dto.PostResponse;
import com.example.blogging.repository.PostRepository;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);


    PostDto updatePost(PostDto postDto, Integer id);

    void deletePost(Integer id);

    PostResponse getAllPost(Integer pageSize, Integer pageNumber);

    PostDto getPostById(Integer id);

    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPostTitle(String keyword);
}
