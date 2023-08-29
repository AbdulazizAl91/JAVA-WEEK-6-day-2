package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getBlog(Integer user_id){
        User user = authRepository.findUserById(user_id);
        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Integer user_id,Blog blog){
        User user = authRepository.findUserById(user_id);
        blog.setUser(user);
        blogRepository.save(blog);
    }
    public void updateBlog(Integer user_id,Integer id,Blog blog){
        User user=authRepository.findUserById(user_id);
        Blog blog1 =blogRepository.findBlogById(id);
        if(blog1 !=null && blog1.getUser().equals(user)){
            blog1.setTitle(blog.getTitle());
            blog1.setBody(blog.getBody());
            blogRepository.save(blog1);

        }

        else {
            throw new ApiException("blog not founded");
        }

    }
    public void deleteBlog(Integer user_id,Integer todo_id){
        User user = authRepository.findUserById(user_id);
        Blog blog   = blogRepository.findBlogById(todo_id);
        if(blog != null && blog.getUser().equals(user) ){
            blogRepository.delete(blog);
        }
        else {
            throw new ApiException("blog not founded");
        }
    }
    public List<Blog> getAllBlogs(){
       return blogRepository.findAll();
    }
    public Blog searchById(Integer user_id,Integer id){
        User user = authRepository.findUserById(user_id);
        Blog blog = blogRepository.findByUserAndId(user,id);
        if (blog==null){
            throw new ApiException("id of bolg not founded");

        }
        return blog;
    }
    public Blog searchByTitle(Integer user_id , String title){
        User user = authRepository.findUserById(user_id);
        Blog blog = blogRepository.findByUserAndTitle(user,title);
        if (blog==null){
            throw new ApiException("blog not fonded");

        }
        return blog;
    }








}
