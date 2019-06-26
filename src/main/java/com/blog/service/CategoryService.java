package com.blog.service;

import com.blog.model.Category;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findAll(Sort sort);
    List<Category> findByNameContainingOrderByIdDesc(String name);
    Category findById(int id);
    void save(Category category);
    void deleteById(int id);
}
