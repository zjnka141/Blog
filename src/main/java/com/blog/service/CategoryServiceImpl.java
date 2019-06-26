package com.blog.service;

import com.blog.exception.CategoryNotFoundException;
import com.blog.model.Category;
import com.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Category> findByNameContainingOrderByIdDesc(String name) {
        return categoryRepository.findByNameContainingOrderByIdDesc(name);
    }

    @Override
    public Category findById(int id){
        Category category= categoryRepository.findById(id).orElse(null);
        if (category==null){
            throw new CategoryNotFoundException("1001","Not found category");
        }
        return category;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
