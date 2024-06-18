package RESTfulBlog.service;

import RESTfulBlog.model.User;
import RESTfulBlog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public boolean addUser(String email,
                           String userName,
                           String password,
                           String roles) {
        if (userNameExists(userName)) return false;
        User user = new User(email, userName, password, roles);
        userRepo.save(user);
        return true;


    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    public boolean userIdExists(Integer id) {
        return userRepo.existsById(id);
    }

    public boolean userNameExists(String username) {

        User probe = new User();
        probe.setUserName(username);

        Example<User> example = Example.of(probe);

        return userRepo.exists(example);

    }

    public boolean updateUserByUserName(String userName) {
        if (!userNameExists(userName)) return false;
        User probe = new User();
        probe.setUserName(userName);

        Example<User> example = Example.of(probe);


        Optional<User> optional = userRepo.findOne(example);
        if (optional.isEmpty())
            return false;

        userRepo.save((User) optional.get());
        return true;

    }

    public boolean updateUserById(Integer id) {
        if (!userIdExists(id)) return false;
        User probe = new User();
        probe.setId(id);

        Example<User> example = Example.of(probe);


        Optional<User> optional = userRepo.findOne(example);
        if (optional.isEmpty())
            return false;

        userRepo.save((User) optional.get());

        return true;

    }

    public boolean removeUserByUserName(String userName) {

        if (!userNameExists(userName)) return false;
        User probe = new User();
        probe.setUserName(userName);

        Example<User> example = Example.of(probe);


        Optional<User> optional = userRepo.findOne(example);
        if (optional.isEmpty())
            return false;

        userRepo.delete((User) optional.get());
        return true;
    }
    public boolean removeUserById(Integer id) {

       userRepo.deleteById(id);
        return true;
    }


}
