package csd.cs203project.controller.supervisor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import csd.cs203project.model.*;
import csd.cs203project.service.supervisor.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Supervisor {
    private SupervisorService supervisorService;

    public Supervisor(SupervisorService supervisorService){
        this.supervisorService = supervisorService;
    }

    @GetMapping("/users/{company}")
    public List<User> getUsersUnderCompany(@PathVariable (value = "company") String company){
        return supervisorService.findByCompany(company);
    }
}