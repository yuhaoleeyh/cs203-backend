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

    
    /** 
     * Get employees from a specific shop based on their role (authorities)
     * @param id for a specific shop
     * @param authorities specified for the users returned
     * @return List<User> users belonging to a specific shop and based on their authorities
     */
    @GetMapping("/users/id/{id}/authorities/{authorities}")
    public List<User> getEmployeesfromShopId (@PathVariable Long id, @PathVariable String authorities) {
        return userService.findByShopShopIdAndAuthorities(id, authorities);
    }

    
    /** 
     * Add new user into database
     * @param user information for new user
     * @return User
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        if (savedUser == null) {
            throw new ResourceExistsException("User with email " + user.getEmail());
        }
        return savedUser;
    }

    
    /** 
     * Update user based on their email and new user information
     * @param email of the user to be updated
     * @param newEmployeeInfo for the new information
     * @return User updated user information
     */
    @PutMapping("/users/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User newEmployeeInfo) {
        
        User user = userService.updateUser(email, newEmployeeInfo);
        if (user == null) {
            throw new ResourceNotFoundException("User with email " + email);
        }
        return user;
    }

    
    /** 
     * Delete user based on their email
     * @param email of the user to be deleted
     */
    @DeleteMapping("/users/{email}")
    public void deleteEmployee(@PathVariable String email) {
        try {
            userService.deleteUser(email);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User with email " + email);
        }
    }

    
    /** 
     * Get the user based on their email (identifier)
     * @param email of the user to be retrieved
     * @return User
     */
    @GetMapping("/users/{email}")
    public User getUser(@PathVariable String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authEmail = authentication.getName();
        if (!authEmail.equals(email))
            throw new UnauthorizedException("Authenticated email does not match");
        
        User user = userService.getUser(email);
        return user;
    }

    
    /** 
     * Update user based on their email and new user information
     * @param email
     * @param newUserInfo
     * @return User
     */
    @PutMapping("/users/email/{email}")
    public User updateUserProfile(@PathVariable String email, @RequestBody User newUserInfo) {
        User user = userService.updateUser(email, newUserInfo);
        if (user == null) return null;
        
        return user;
    }

    
    /** 
     * Get users of a certain user role (authorities)
     * @param authorities for the users to be retrieved
     * @return List<User> list of users
     */
    @GetMapping("/users/authorities/{authorities}")
    public List<User> getUserByAuthorities (@PathVariable String authorities) {
        return userService.findByAuthorities(authorities);
    }

}
