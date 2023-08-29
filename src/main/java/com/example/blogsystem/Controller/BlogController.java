package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get")
    public ResponseEntity getBlog(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getBlog(user.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user,@RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body("blog added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user,@PathVariable Integer id,@RequestBody @Valid Blog blog){
        blogService.updateBlog(user.getId(),id,blog);
        return ResponseEntity.status(200).body("blog updated");

    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user,@PathVariable Integer id){
        blogService.deleteBlog(user.getId(),id);
        return ResponseEntity.status(200).body("blog deleted");
    }
    @GetMapping("/get-all-blog")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }
    @GetMapping("/search-by-title/{title}")

    public ResponseEntity searchByTitle(@AuthenticationPrincipal User user,@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.searchByTitle(user.getId(), title));
    }
    @GetMapping("/search-by-id/{id}")
    public ResponseEntity searchById(@AuthenticationPrincipal User user,@PathVariable Integer id){
        return ResponseEntity.status(200).body(blogService.searchById(user.getId(),id));
    }









}
