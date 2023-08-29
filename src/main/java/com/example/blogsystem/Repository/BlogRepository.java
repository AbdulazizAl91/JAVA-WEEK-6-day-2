package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    Blog findBlogById(Integer id);
    Blog findByUserAndId(User user,Integer id);
    List<Blog>findAllByUser(User user);
    Blog findByUserAndTitle(User user, String title);
}
