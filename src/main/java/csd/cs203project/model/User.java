package csd.cs203project.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails {
    private @Id @GeneratedValue Long id;

    private String name;
    private String email;
    private String vaccinationStatus;
    private String swabTestResult;
    private String fetStatus;
    private String telegramSignUpToken;
    private String telegramChatId;

    // Different roles/authorities: ROLE_PROF, ROLE_ADMIN, ROLE_SUPERVISOR, ROLE_EMPLOYEE
    private String authorities;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public User(String email, String name, String authorities) {
        this.email = email;
        this.name = name;
        this.authorities = authorities;
    }

    
    /** 
     * Get role (authorities) for the user
     * @return Collection<? extends GrantedAuthority>
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(authorities));
    }

    @Override
    public String getUsername() {
        return null;
    }
    
    /** 
     * Get password for user
     * @return String
     */
    @Override
    public String getPassword() {
        return null;
    }

    
    /** 
     * Get boolean for whether Account is Non-Expired
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    /** 
     * Get boolean for whether Account is Non-Locked
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    /** 
     * Get boolean for whether Account is Non-Expired
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    /** 
     * Get boolean for whether Account is Enabled
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    
    /** 
     * Return Shop for the user
     * @return Shop
     */
    public Shop getShop() {
        return shop;
    }

    
    /** 
     * Set shop for the user
     * @param shop
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
