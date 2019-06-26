package com.blog.controller;

import com.blog.model.Blog;
import com.blog.service.BlogService;
import com.blog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BlogController {
    public static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/blogs")
    public ResponseEntity<?> showAllBlogs() {
        List<Blog> blogs = blogService.findAll();
        if (blogs.isEmpty()) {
            return new ResponseEntity<String>("Don't have any blog", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }

    @GetMapping(value = "/blogs", params = {"limit", "offset"})
    public ResponseEntity<?> showAllBlogs(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        Page<Blog> blogs = blogService.findAll(PageRequest.of(offset, limit));
        if (blogs.isEmpty()) {
            return new ResponseEntity<String>("Don't have any blog", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.OK);
    }

    @GetMapping(value = "/blogs", params = {"top"})
    public List<Blog> getTop6Blogs(@RequestParam("top") int id) {
        return blogService.findTop6(id);
    }

    @GetMapping(value = "/blogs", params = {"related"})
    public List<Blog> getRelatedBlogs(@RequestParam("related") int id) {
        Blog blog = blogService.findById(id);
        List<Blog> blogs = blogService.findRelatedBlogs(id, blog.getCategory().getId());
        return blogs;
    }

    @GetMapping("/blogs/{id}")
    public Blog getBlogById(@PathVariable("id") int id) {
        return blogService.findById(id);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<List<Blog>> deleteBlog(@PathVariable("id") int id) {
        blogService.deleteById(id);
        return new ResponseEntity<List<Blog>>(blogService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}/blogs")
    public ResponseEntity<?> showAllBlogsByCategory(@PathVariable("id") int id) {
        List<Blog> blogs = blogService.findByCategoryId(id);
        if (blogs.isEmpty()) {
            logger.error("Can't found blog with categoryID {}", id);
            return new ResponseEntity<String>("No content", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }

    @GetMapping(value = "/categories/{id}/blogs", params = {"limit", "offset"})
    public ResponseEntity<?> showAllBlogsByCategory(@RequestParam("limit") int limit, @RequestParam("offset") int offset, @PathVariable("id") int id) {
        Page<Blog> blogs = blogService.findByCategoryId(id, PageRequest.of(offset, limit));
        if (blogs.isEmpty()) {
            logger.error("Can't found blog with categoryID {}", id);
            return new ResponseEntity<String>("No content", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Blog>>(blogs, HttpStatus.OK);
    }

    @PostMapping("/categories/{id}/blogs")
    public ResponseEntity<String> createBlog(@Valid @RequestBody Blog blog, BindingResult bindingResult, @PathVariable("id") int id) {
        logger.info("Create blog in category {}", categoryService.findById(id));
        blog.setCategory(categoryService.findById(id));
        if (bindingResult.hasErrors()) {
            logger.error("Has error when create blog");
            return new ResponseEntity<String>("Can't create blog", HttpStatus.BAD_REQUEST);
        }
        blogService.save(blog);
        return new ResponseEntity<String>(String.valueOf(blog.getId()), HttpStatus.CREATED);
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<String> updateBlog(@RequestBody Blog blog,
                                             @PathVariable("id") int id) {
        Blog __blog = blogService.findById(id);
        __blog.setTitle(blog.getTitle());
        __blog.setContent(blog.getContent());
        __blog.setDescription(blog.getDescription());
        blogService.save(__blog);
        return new ResponseEntity<String>("Blog was updated", HttpStatus.OK);
    }

}
