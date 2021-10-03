package csd.cs203project.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private @Id @GeneratedValue Long id;

    private String name;
    private String email;
    private String userType;
    private String vaccinationStatus;
    private String swabTestResult;
    private String fetStatus;
    private String company;
    private String telegramHandle;
    private String telegramSignUpToken;
    private String telegramChatId;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;


    public User(String name, String userType, String company) {
        this.name = name;
        this.userType = userType;
        this.company = company;
    }

}
