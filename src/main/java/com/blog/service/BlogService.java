package com.blog.service;

import com.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();
    Page<Blog> findAll(Pageable pageable);
    List<Blog> findTop6(int id);
    List<Blog> findRelatedBlogs(int id,int categoryId);
    List<Blog> findByCategoryId(int categoryID);
    Page<Blog> findByCategoryId(int categoryID,Pageable pageable);
    Blog findById(int id);
    void save(Blog blog);
    void deleteById(int id);
}
