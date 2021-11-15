package csd.cs203project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FootfallData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String month;
    private double total;
    private double restaurants;
    private double fastFoodOutlets;
    private double caterers;
    private double otherPlaces;

    public FootfallData(String month, double total) {
        this.month = month;
        this.total = total;
    }
}
