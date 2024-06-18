package RESTfulBlog.service;

import RESTfulBlog.model.Blog;
import RESTfulBlog.model.User;
import RESTfulBlog.repository.BlogRepo;
import RESTfulBlog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepo blogRepo;
    @Autowired
    private UserRepo userRepo;

    public boolean createBlog(String name, String description, String userName) {
        Blog blog = new Blog(name, description, userName);
        blogRepo.save(blog);
        return true;

    }

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public Blog getBlogById(Integer id) {
        return blogRepo.getById(id);
    }

    public boolean updateBlogById(Integer id,
                                  String name,
                                  String description,
                                  String userName) {
        Blog blog = new Blog(name,description,userName);
        blog.setId(id);

        blogRepo.save(getBlogById(id));
        return true;
    }

    public boolean deleteBlogById(Integer id) {
        blogRepo.deleteById(id);
    }


}
