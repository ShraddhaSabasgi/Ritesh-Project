package com.example.blogging.services;

import com.example.blogging.payloads_dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto craeteCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);

    void deleteCategory(Integer id);

    CategoryDto getCategoryById(Integer id);

    List<CategoryDto> getAllCategory();
}
