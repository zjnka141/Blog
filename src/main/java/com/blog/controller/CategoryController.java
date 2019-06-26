package com.blog.controller;

import com.blog.model.Category;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> listAll(){
        return categoryService.findAll();
    }

    @GetMapping(value = "/categories",params = "sort")
    public List<Category> listAll(@RequestParam("sort") String sort){
        return categoryService.findAll(Sort.by(sort).descending());
    }

    @GetMapping(value = "/categories",params = {"sort","q"})
    public List<Category> listAll(@RequestParam("sort") String sort, @RequestParam("q") String q){
        return categoryService.findByNameContainingOrderByIdDesc(q);
    }

    @GetMapping(value = "/categories/{id}")
    public Category getCategory(@PathVariable("id") int id){
        return categoryService.findById(id);
    }

    @PostMapping("/categories")
    public void createPost(@RequestBody Category category) {
        categoryService.save(category);
    }
}
