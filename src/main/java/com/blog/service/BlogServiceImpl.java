package com.blog.service;

import com.blog.model.Blog;
import com.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAllByOrderByIdDesc();
    }
    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> findTop6(int id) {
        return blogRepository.findTop6ByIdNotOrderByIdDesc(id);
    }

    @Override
    public List<Blog> findRelatedBlogs(int id,int categoryId) {
        return blogRepository.findTop6ByCategoryIdOrderByIdDesc(id,categoryId);
    }

    @Override
    public List<Blog> findByCategoryId(int categoryID) {
        return blogRepository.findByCategoryIdOrderByIdDesc(categoryID);
    }

    @Override
    public Page<Blog> findByCategoryId(int categoryID,Pageable pageable) {
        return blogRepository.findByCategoryId(categoryID,pageable);
    }

    @Override
    public Blog findById(int id) {
        return blogRepository.findById(id).orElse( null);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void deleteById(int id) {
        blogRepository.deleteById(id);
    }
}
