package csd.cs203project.security.cognito;

import org.springframework.stereotype.Component;

@Component
public class JwtIdTokenCredentialsHolder {
    private String idToken;

    public JwtIdTokenCredentialsHolder() {
    }

    
    /** 
     * @return String
     */
    public String getIdToken() {
        return this.idToken;
    }

    
    /** 
     * @param idToken
     * @return JwtIdTokenCredentialsHolder
     */
    public JwtIdTokenCredentialsHolder setIdToken(String idToken) {
        this.idToken = idToken;
        return this;
    }
}