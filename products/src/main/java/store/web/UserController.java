package store.web;

import store.domain.User;
import store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = GET, produces = "application/json")
    public List<User> readAllUsers() {
        return userRepository.findAll();
    }
}