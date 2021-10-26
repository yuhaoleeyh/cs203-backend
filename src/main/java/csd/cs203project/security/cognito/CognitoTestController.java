package csd.cs203project.security.cognito;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CognitoTestController {

    @GetMapping("/cognito/all-allow")
    public String getAnyone() {
        return "Response: anyone can see this!";
    }
    
    @GetMapping(path = "/cognito/only-authenticated")
    public String getResp(){
        User user = (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String email = user.getUsername();
        System.out.println("email: " + email);
        
        return  "Response: You are cognito-authenticated at this email: " + email;
    }

}
