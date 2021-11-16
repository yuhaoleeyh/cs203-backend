package csd.cs203project.service.measures;

import csd.cs203project.model.Measures;

import java.util.List;

public interface MeasuresService {

    /**
     * 
     * @param measures new measure to create or update
     * @return newly created measure
     */
    Measures updateMeasures(Measures measures);

    /**
     * 
     * @param typeOfShop type of shop to find measures for 
     * @return
     */
    Measures findByTypeOfShop(String typeOfShop);

    /**
     * 
     * @param oldMeasures 
     * @param newMeasures
     * @return List of strings describing changes in measures
     */
    List<String> getChangeInMeasures(Measures oldMeasures, Measures newMeasures);

    /**
     * 
     * @return all measures
     */
    List<Measures> findAllMeasures();
}
