package csd.cs203project.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Measures {
    //admin keys this
    private @Id @GeneratedValue Long id;
    private String typeOfShop;
    private boolean isActive;
    private int dineInSize;
    private int maxGrpSizeVacc;
    private int maxGrpSizeNonVacc;
    private int socialDistance;
    private Date closingTime;
    private String phase;

}
