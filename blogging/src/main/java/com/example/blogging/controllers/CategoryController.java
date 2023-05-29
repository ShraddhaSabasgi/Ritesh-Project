package com.example.blogging.controllers;

import com.example.blogging.ServiceImpl.CategoryImpl;
import com.example.blogging.payloads_dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryImpl category;

    @PostMapping("/categoryCreate")
    public String createCategory(@Valid @RequestBody CategoryDto categoryDto){
        category.craeteCategory(categoryDto);
        return "category save";
    }

    @PutMapping("categoryCreate/{id}")
    public String updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        category.updateCategory(categoryDto,id);
        return "update category";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Integer id){
        category.deleteCategory(id);
        return "delete category";
    }

    @GetMapping("/getCategory")
    public List<CategoryDto> getAllCategory(){
       List<CategoryDto> categoryDtos= this.category.getAllCategory();
        return categoryDtos;
    }

    @GetMapping("/getCategoryById/{id}")
    public CategoryDto getCatById(@PathVariable Integer id){
       return category.getCategoryById(id);

    }
}
