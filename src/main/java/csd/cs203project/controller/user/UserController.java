package csd.cs203project.controller.user;

import csd.cs203project.model.User;
import csd.cs203project.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/addUser")
    public void addUser(@RequestBody @Valid User user) {
        userService.addUser(user);
    }

    @GetMapping("/users/userByEmail")
    @ResponseBody
    public User getUserByEmail(@RequestParam String email) {
        System.out.println("controller: " + email);
        return userService.findByEmail(email);
    }
}
