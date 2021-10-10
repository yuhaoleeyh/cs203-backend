package csd.cs203project.controller.supervisor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public SupervisorController(SupervisorService supervisorService){
        this.supervisorService = supervisorService;
    }

    @GetMapping("/users/{company}")
    public List<User> getEmployeesUnderCompany(@PathVariable (value = "company") String company){
        return supervisorService.findEmployeesByCompany(company);
    }

    @PostMapping("/users/validUser") 
    public void addValidUser(@RequestBody @Valid User user) {
        supervisorService.addValidUser(user);
    }

    
}