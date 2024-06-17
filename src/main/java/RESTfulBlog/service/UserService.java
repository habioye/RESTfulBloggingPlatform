package RESTfulBlog.service;

import RESTfulBlog.model.Blog;
import RESTfulBlog.repository.BlogRepo;

import java.util.List;

public class UserService {


    public List<Blog> getAllBlogs() {
        return BlogRepo.findAll();
    }
}
