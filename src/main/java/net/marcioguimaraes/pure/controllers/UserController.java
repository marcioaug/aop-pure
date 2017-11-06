package net.marcioguimaraes.pure.controllers;

import net.marcioguimaraes.pure.models.User;
import net.marcioguimaraes.pure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/greeting")
    public User greeting() {
        return userRepository.save(new User("Marcio Augusto", "marcioaug"));
    }

    @RequestMapping(method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User get(@PathVariable Long id) {
        return userRepository.findOne(id);
    }
}



