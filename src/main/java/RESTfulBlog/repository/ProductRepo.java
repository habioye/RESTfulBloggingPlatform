package RESTfulBlog.repository;

import RESTfulBlog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Blog, Integer> {
}
