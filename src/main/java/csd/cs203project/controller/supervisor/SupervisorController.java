package csd.cs203project.controller.supervisor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import org.springframework.dao.EmptyResultDataAccessException;



import java.util.List;

import javax.validation.Valid;

import csd.cs203project.exception.supervisor.EmployeeExistsException;
import csd.cs203project.exception.supervisor.EmployeeNotFoundException;
import csd.cs203project.model.*;
import csd.cs203project.service.supervisor.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SupervisorController {
    private SupervisorService supervisorService;

    @Autowired
    public SupervisorController(SupervisorService supervisorService){
        this.supervisorService = supervisorService;
    }

    @GetMapping("/employees/{company}")
    public List<User> getEmployeesUnderCompany(@PathVariable (value = "company") String company){
        return supervisorService.findEmployeesByCompany(company);
    }

    @GetMapping("/employees/administrator/{company}")
    public List<User> getEmployeesAndAdminsUnderCompany(@PathVariable (value = "company") String company) {
        return supervisorService.findEmployeesAndAdminsUnderCompany(company);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employees")
    public User addEmployee(@RequestBody User employee) {
        User savedEmployee = supervisorService.addEmployee(employee);
        if (savedEmployee == null) {
            throw new EmployeeExistsException(employee.getEmail());
        }
        return savedEmployee;
    }

    @PutMapping("/employees/{email}")
    public User updateEmployee(@PathVariable String email, @RequestBody User newEmployeeInfo) {
        
        User user = supervisorService.updateEmployee(email, newEmployeeInfo);
        if (user == null) {
            throw new EmployeeNotFoundException(email);
        }
        return user;
    }

    @DeleteMapping("/employees/{email}")
    public void deleteEmployee(@PathVariable String email) {
        try {
            supervisorService.deleteEmployee(email);
        } catch (EmptyResultDataAccessException e) {
            throw new EmployeeNotFoundException(email);
        }
    }

    
    
}