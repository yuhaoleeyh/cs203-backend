package csd.cs203project.controller.supervisor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import javax.validation.Valid;

import csd.cs203project.model.*;
import csd.cs203project.service.supervisor.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SupervisorController {
    private SupervisorService supervisorService;

    public SupervisorController(SupervisorService supervisorService){
        this.supervisorService = supervisorService;
    }

    @GetMapping("/users/{company}")
    public List<User> getUsersUnderCompany(@PathVariable (value = "company") String company){
        return supervisorService.findByCompany(company);
    }

    @PostMapping("/users/addValidUser") 
    public void addValidUser(@RequestBody @Valid User user) {
        supervisorService.addValidUser(user);
    }
}