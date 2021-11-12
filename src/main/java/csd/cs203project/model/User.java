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
    private String userType;
    private String vaccinationStatus;
    private String swabTestResult;
    private String fetStatus;
    private String company;
    private String telegramSignUpToken;
    private String telegramChatId;

    // Different roles/authorities: ROLE_PROF, ROLE_ADMIN, ROLE_SUPERVISOR, ROLE_EMPLOYEE
    private String authorities;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public User(String email, String name, String authorities, String company) {
        this.email = email;
        this.name = name;
        this.authorities = authorities;
        this.company = company;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(authorities));
    }

    @Override
    public String getUsername() {
        return null;
    }
    
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
