package csd.cs203project.security.cognito;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CognitoTestController {

    
    /** 
     * Endpoint for cognito that should not be secured
     * @return String response
     */
    @GetMapping("/cognito/all-allow")
    public String getAnyone() {
        return "Response: anyone can see this!";
    }
    
    
    /** 
     * Endpoint for cognito that should be secured
     * @return String response
     */
    @GetMapping(path = "/cognito/only-authenticated")
    public String getResp(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authEmail = authentication.getName();
        
        return  "Response: You are cognito-authenticated at this email: " + authEmail;
    }

}
