package com.example.blogging.payloads_dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDto> content;
    private int pageSize;
    private int pageNumber;
    private long totalElements;
    private long totalPages;
    private boolean lastPage;
}
