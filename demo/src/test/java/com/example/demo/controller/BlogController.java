package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;
    public BlogController(BlogService blogService) { this.blogService = blogService; }

    // public list
    @GetMapping
    public ResponseEntity<List<Blog>> getAll() {
        return ResponseEntity.ok(blogService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getOne(@PathVariable Long id) {
        return blogService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // create blog (authenticated)
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Blog> create(@RequestBody Blog blog, Authentication auth) {
        blog.setOwnerUsername(auth.getName());
        return ResponseEntity.ok(blogService.create(blog));
    }

    // update blog: only owner or admin
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Blog b, Authentication auth) {
        var existing = blogService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();
        Blog blog = existing.get();
        boolean isOwner = auth.getName().equals(blog.getOwnerUsername());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!isOwner && !isAdmin) return ResponseEntity.status(403).build();

        blog.setTitle(b.getTitle());
        blog.setContent(b.getContent());
        return ResponseEntity.ok(blogService.update(blog));
    }

    // delete blog: only owner or admin
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) {
        var existing = blogService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();
        Blog blog = existing.get();
        boolean isOwner = auth.getName().equals(blog.getOwnerUsername());
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!isOwner && !isAdmin) return ResponseEntity.status(403).build();
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    // get all blogs for current auth user
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Blog>> myBlogs(Authentication auth) {
        return ResponseEntity.ok(blogService.findByOwner(auth.getName()));
    }
}
