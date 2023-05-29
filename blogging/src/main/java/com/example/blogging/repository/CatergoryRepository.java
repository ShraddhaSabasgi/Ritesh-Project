package com.example.blogging.repository;

import com.example.blogging.entity.Category;
import com.example.blogging.payloads_dto.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatergoryRepository extends JpaRepository<Category,Integer> {


}
