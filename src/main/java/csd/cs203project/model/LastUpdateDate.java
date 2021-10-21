package csd.cs203project.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LastUpdateDate {
    @Id //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String dataLastUpdated;
    private boolean isChanged;

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}
