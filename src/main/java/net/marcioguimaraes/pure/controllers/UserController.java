package net.marcioguimaraes.pure.controllers;

import net.marcioguimaraes.pure.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @RequestMapping("/greeting")
    public User greeting() {
        return new User("Marcio Augusto", "marcioaug");
    }

}
