package com.example.blogging.ServiceImpl;

import com.example.blogging.entity.Comment;
import com.example.blogging.entity.Post;
import com.example.blogging.payloads_dto.CommentDto;
import com.example.blogging.repository.CommentRepository;
import com.example.blogging.repository.PostRepository;
import com.example.blogging.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(RuntimeException::new);
        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedCom=this.commentRepository.save(comment);
        return this.modelMapper.map(savedCom,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer id) {
            Comment delete=this.commentRepository.findById(id).orElseThrow(RuntimeException::new);
            this.commentRepository.delete(delete);
    }
}
