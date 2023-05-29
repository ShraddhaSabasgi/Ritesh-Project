package com.example.blogging.controllers;


import com.example.blogging.entity.Comment;
import com.example.blogging.entity.Post;
import com.example.blogging.payloads_dto.CommentDto;
import com.example.blogging.services.CommentService;
import com.example.blogging.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/create/{postId}/comment")
    public CommentDto createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){

      return  this.commentService.createComment(commentDto,postId);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        this.commentService.deleteComment(id);
    }
}
