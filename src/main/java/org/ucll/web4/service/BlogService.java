package org.ucll.web4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucll.web4.entity.BlogEntity;
import org.ucll.web4.repository.blog.BlogRepository;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(@Autowired BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public List<BlogEntity> getBlogTopics(){
        return blogRepository.getAll();
    }
}
