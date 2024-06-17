package RESTfulBlog.service;

import RESTfulBlog.model.Blog;
import RESTfulBlog.model.User;
import RESTfulBlog.repository.BlogRepo;
import RESTfulBlog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class BlogService  {
    @Autowired
    private BlogRepo blogRepo;
    @Autowired
    private UserRepo userRepo;


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }




}
