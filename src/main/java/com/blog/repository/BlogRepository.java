package com.blog.repository;

import com.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findTop6ByIdNotOrderByIdDesc(int id);

    @Query(value = "select * from blog where id<>?1 and categoryId=?2 order by id desc limit 6", nativeQuery = true)
    List<Blog> findTop6ByCategoryIdOrderByIdDesc(int id,int categoryId);

    List<Blog> findByCategoryIdOrderByIdDesc(int categoryID);

    Page<Blog> findByCategoryId(int categoryID, Pageable pageable);
    Page<Blog> findAll(Pageable pageable);

    List<Blog> findAllByOrderByIdDesc();
}
