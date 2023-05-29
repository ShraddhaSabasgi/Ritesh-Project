package com.example.blogging.services;

import com.example.blogging.payloads_dto.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer id);
}
