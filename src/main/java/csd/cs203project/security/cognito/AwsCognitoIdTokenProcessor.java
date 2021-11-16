package csd.cs203project.security.cognito;

import static java.util.List.of;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import csd.cs203project.service.user.UserService;

@Component
public class AwsCognitoIdTokenProcessor {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Autowired
    private ConfigurableJWTProcessor configurableJWTProcessor;

    
    /** 
     * Check Http Servlet Request and return Authentication object
     * @param request Http Servlet Request
     * @return Authentication authenticated user's information 
     * @throws Exception
     */
    public Authentication authenticate(HttpServletRequest request) throws Exception {
        String idToken = request.getHeader(this.jwtConfiguration.getHttpHeader());
        if (idToken != null) {
            JWTClaimsSet claims = this.configurableJWTProcessor.process(this.getBearerToken(idToken),null);
            validateIssuer(claims);
            verifyIfIdToken(claims);

            String email = getEmailFrom(claims);
            if (email != null) {
                List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) userService.getUser(email).getAuthorities();
                User user = new User(email, "", of());
                return new JwtAuthentication(user, claims, grantedAuthorities);
            }
        } else {
            
        }
        return null;
    }

    
    /** 
     * Get email from a user's JWTClaimsSet claims
     * @param claims JWT claims
     * @return String email
     */
    private String getEmailFrom(JWTClaimsSet claims) {
        return claims.getClaims().get("email").toString();
    }
    
    
    /** 
     * Get username from a user's JWTClaimsSet claims
     * @param claims JWT claims
     * @return String username
     */
    private String getUserNameFrom(JWTClaimsSet claims) {
        return claims.getClaims().get(this.jwtConfiguration.getUserNameField()).toString();
    }

    
    /** 
     * Verify if the JWT Token is an ID token
     * @param claims JWTClaimsSet claims
     * @throws Exception
     */
    private void verifyIfIdToken(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception("JWT Token is not an ID Token");
        }
    }

    
    /** 
     * Validate that the issuer matches cognito's identity pool
     * @param claims JWTClaimsSet claims
     * @throws Exception
     */
    private void validateIssuer(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception(String.format("Issuer %s does not match cognito idp %s", claims.getIssuer(), this.jwtConfiguration.getCognitoIdentityPoolUrl()));
        }
    }

    
    /** 
     * Get the bearer token from the token
     * @param token
     * @return String
     */
    private String getBearerToken(String token) {
        return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;
    }
}
