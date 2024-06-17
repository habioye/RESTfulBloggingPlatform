package RESTfulBlog.controller;

import RESTfulBlog.model.User;
import RESTfulBlog.repository.BlogRepo;
//import com.springSecurityUpdated.springSecurityUpdated.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Controller {
    @Autowired
    private RESTfulBlog.repository.UserRepo userRepo;
    @Autowired
    private BlogRepo blogRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String goHome(){

        return "This is publically acceccible without needing authentication ";
    }
    @PostMapping("/user/save")
    public ResponseEntity<Object> saveUSer(@RequestBody User ourUser){
        ourUser.setPassword(passwordEncoder.encode(ourUser.getPassword()));
        User result = userRepo.save(ourUser);
        if (result.getId() > 0){
            return ResponseEntity.ok("USer Was Saved");
        }
        return ResponseEntity.status(404).body("Error, USer Not Saved");
    }
    @GetMapping("/product/all")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(blogRepo.findAll());
    }
    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUSers(){
        return ResponseEntity.ok(userRepo.findAll());
    }
    @GetMapping("/users/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getMyDetails(){
        return ResponseEntity.ok(userRepo.findByEmail(getLoggedInUserDetails().getUsername()));
    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
