package csd.cs203project.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    private int lengthOfShop;

    private int tableGap;

    @Max(value=9) @Min(value=1)
    private int widthOfTable;
    @Max(value=9) @Min(value=1)
    private int lengthOfTable;

    public TableLayout(int widthOfShop, int lengthOfShop, int widthOfTable, int lengthOfTable, int tableGap) {
        this.widthOfShop = widthOfShop;
        this.lengthOfShop = lengthOfShop;
        this.widthOfTable = widthOfTable;
        this.lengthOfTable = lengthOfTable;
        this.tableGap = tableGap;
    }
}