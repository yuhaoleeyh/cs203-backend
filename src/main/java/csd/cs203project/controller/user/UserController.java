package csd.cs203project.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import csd.cs203project.exception.ResourceExistsException;
import csd.cs203project.exception.ResourceNotFoundException;
import csd.cs203project.exception.UnauthorizedException;
import csd.cs203project.model.*;
import csd.cs203project.service.user.UserService;


import org.springframework.http.HttpStatus;

import java.util.List;


@CrossOrigin
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/id/{id}/authorities/{authorities}")
    public List<User> getEmployeesfromShopId (@PathVariable Long id, @PathVariable String authorities) {
        return userService.findByShopShopIdAndAuthorities(id, authorities);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public User addUser(@RequestBody User employee) {
        User savedEmployee = userService.addUser(employee);
        if (savedEmployee == null) {
            throw new ResourceExistsException("User with email " + employee.getEmail());
        }
        return savedEmployee;
    }

    @PutMapping("/users/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User newEmployeeInfo) {
        
        User user = userService.updateUser(email, newEmployeeInfo);
        if (user == null) {
            throw new ResourceNotFoundException("User with email " + email);
        }
        return user;
    }

    @DeleteMapping("/users/{email}")
    public void deleteEmployee(@PathVariable String email) {
        try {
            userService.deleteUser(email);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User with email " + email);
        }
    }

    @GetMapping("/users/{email}")
    public User getUser(@PathVariable String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authEmail = authentication.getName();
        if (!authEmail.equals(email))
            throw new UnauthorizedException("Authenticated email does not match");
        
        User user = userService.getUser(email);
        return user;
    }

    @PutMapping("/users/email/{email}")
    public User updateUserProfile(@PathVariable String email, @RequestBody User newUserInfo) {
        User user = userService.updateUser(email, newUserInfo);
        if (user == null) return null;
        
        return user;
    }

    @GetMapping("/users/authorities/{authorities}")
    public List<User> getUserByAuthorities (@PathVariable String authorities) {
        return userService.findByAuthorities(authorities);
    }

}
