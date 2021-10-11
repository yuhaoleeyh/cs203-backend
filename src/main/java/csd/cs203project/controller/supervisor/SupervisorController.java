package csd.cs203project.controller.supervisor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PutMapping("/users/{company}")
    public User editValidUsers(@PathVariable (value = "company") String company, @RequestBody User listOfUsers) {
        System.out.println(company);
        System.out.println(listOfUsers);
        System.out.println(listOfUsers.getName());
        return listOfUsers;
        // supervisorService.editValidUsers(company, listOfUsers);
    }

    // @PostMapping("/users/{company}")
    // public User testing(@PathVariable String company, @RequestBody User listOfUsers) {
    //     System.out.println(company);
    //     System.out.println(listOfUsers);
    //     System.out.println(listOfUsers.getId());
    //     return listOfUsers;
    //     // supervisorService.editValidUsers(company, listOfUsers);
    // }

    
}