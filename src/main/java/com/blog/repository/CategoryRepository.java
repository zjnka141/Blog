package com.blog.repository;

import com.blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByNameContainingOrderByIdDesc(String name);
}
