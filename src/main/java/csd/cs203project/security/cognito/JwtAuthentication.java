package csd.cs203project.security.cognito;

import java.util.Collection;

import com.nimbusds.jwt.JWTClaimsSet;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private final Object principal;
    private JWTClaimsSet jwtClaimsSet;

    public JwtAuthentication(Object principal, JWTClaimsSet jwtClaimsSet, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.jwtClaimsSet = jwtClaimsSet;
        super.setAuthenticated(true);
    }

    
    /** 
     * @return Object
     */
    public Object getCredentials() {
        return null;
    }

    
    /** 
     * @return Object
     */
    public Object getPrincipal() {
        return this.principal;
    }

    
    /** 
     * @return JWTClaimsSet
     */
    public JWTClaimsSet getJwtClaimsSet() {
        return this.jwtClaimsSet;
    }
}
