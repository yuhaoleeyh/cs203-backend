package csd.cs203project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Shop {
    private @Id @GeneratedValue Long id;

    private String name;
    private String shopType;
    private double area;
    private int numTables;
    private double sizeTables;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<User> users;

    public Shop(String name) {
        this.name = name;
    }
}
