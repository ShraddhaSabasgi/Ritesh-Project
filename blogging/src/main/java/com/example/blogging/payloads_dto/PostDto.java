package com.example.blogging.payloads_dto;

import com.example.blogging.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private int id;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;

    private Set<CommentDto> comments=new HashSet<>();

}
