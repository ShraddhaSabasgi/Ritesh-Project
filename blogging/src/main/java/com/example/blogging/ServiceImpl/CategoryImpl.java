package com.example.blogging.ServiceImpl;

import com.example.blogging.entity.Category;
import com.example.blogging.payloads_dto.CategoryDto;
import com.example.blogging.repository.CatergoryRepository;
import com.example.blogging.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    private CatergoryRepository catergoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto craeteCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto,Category.class);
        Category saveCategory=this.catergoryRepository.save(category);
        return this.modelMapper.map(saveCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {

        Category category=this.catergoryRepository.findById(id).orElseThrow(RuntimeException::new);
        category.setCategory_title(categoryDto.getCategory_title());
        category.setCategory_description(category.getCategory_description());
        Category saveUpdate=this.catergoryRepository.save(category);
        return this.modelMapper.map(saveUpdate,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category=this.catergoryRepository.findById(id).orElseThrow(RuntimeException::new);
        this.catergoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
     Category category=this.catergoryRepository.findById(id).orElseThrow(RuntimeException::new);

        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories=this.catergoryRepository.findAll();
        List<CategoryDto> categoryDtos= categories.stream().map((category -> this.modelMapper.map(category,CategoryDto.class))).collect(Collectors.toList());
        return categoryDtos;
    }
}
