package csd.cs203project.service;

import csd.cs203project.model.Measures;

import java.util.Optional;

public interface MeasuresService {
    public void addMeasures(Measures measures);

    public Optional<Measures> findByTypeOfShopAndIsActive(String typeOfShop, String isActive);
}
