package csd.cs203project.security.cognito;

import org.springframework.stereotype.Component;

@Component
public class JwtIdTokenCredentialsHolder {
    private String idToken;

    public JwtIdTokenCredentialsHolder() {
    }

    
    /** 
     * Get id token
     * @return String
     */
    public String getIdToken() {
        return this.idToken;
    }

    
    /** 
     * Set id token
     * @param idToken id token
     * @return JwtIdTokenCredentialsHolder id token
     */
    public JwtIdTokenCredentialsHolder setIdToken(String idToken) {
        this.idToken = idToken;
        return this;
    }
}