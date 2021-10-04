package csd.cs203project.controller.user;

import csd.cs203project.model.User;
import csd.cs203project.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/addUser")
    public void addUser(@RequestBody @Valid User user) {
        userService.addUser(user);
    }
}
