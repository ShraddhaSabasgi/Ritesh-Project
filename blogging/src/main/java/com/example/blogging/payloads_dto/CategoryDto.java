package com.example.blogging.payloads_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private int id;

    @NotBlank
    @Size(min = 3,message = "more than 3 char")
    private String category_title;

    @NotBlank
    @Size(min = 3,message = "more than 3 char")
    private String category_description;
}
