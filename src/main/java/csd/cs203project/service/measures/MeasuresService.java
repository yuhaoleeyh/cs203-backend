package csd.cs203project.service.measures;

import csd.cs203project.model.Measures;

import java.util.List;
import java.util.Optional;

public interface MeasuresService {

    void addMeasures(Measures measures);

    List<Measures> findByTypeOfShopAndIsActive(String typeOfShop, Boolean isActive);

    List<String> getChangeInMeasures(Measures oldMeasures, Measures newMeasures);
}
