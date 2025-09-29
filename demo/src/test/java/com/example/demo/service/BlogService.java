package com.example.demo.service;

import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) { this.blogRepository = blogRepository; }

    public Blog create(Blog blog) { return blogRepository.save(blog); }
    public List<Blog> findAll() { return blogRepository.findAll(); }
    public Optional<Blog> findById(Long id) { return blogRepository.findById(id); }
    public List<Blog> findByOwner(String owner) { return blogRepository.findByOwnerUsername(owner); }
    public Blog update(Blog blog) { return blogRepository.save(blog); }
    public void delete(Long id) { blogRepository.deleteById(id); }
}
