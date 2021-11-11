package csd.cs203project.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TableLayout {
    @Id @GeneratedValue
    private Long id;

    private int widthOfShop;
    private int heightOfShop;
    private int widthOfTable;
    private int heightOfTable;

    public TableLayout(int widthOfShop, int heightOfShop, int widthOfTable, int heightOfTable) {
        this.widthOfShop = widthOfShop;
        this.heightOfShop = heightOfShop;
        this.widthOfTable = widthOfTable;
        this.heightOfTable = heightOfTable;
    }
}