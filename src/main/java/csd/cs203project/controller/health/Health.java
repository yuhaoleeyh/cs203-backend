package csd.cs203project.controller.health;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Health {
    
    /** 
     * Get Health Check ResponseEntity
     * @return Health Check
     */
    @GetMapping("/health")    
    public ResponseEntity getHealthCheck(){
        return ResponseEntity.ok("instance OK");
    }
}
