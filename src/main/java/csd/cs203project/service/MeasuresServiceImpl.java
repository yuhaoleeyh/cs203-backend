package csd.cs203project.service;

import csd.cs203project.model.Measures;
import csd.cs203project.repository.measures.MeasuresRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeasuresServiceImpl implements MeasuresService {

    private MeasuresRepository measuresRepository;

    public MeasuresServiceImpl(MeasuresRepository measuresRepository) {
        this.measuresRepository = measuresRepository;
    }

    @Override
    public void addMeasures(Measures measures) {
        measuresRepository.save(measures);


    }

    @Override
    public Optional<Measures> findByTypeOfShopAndIsActive(String typeOfShop, String isActive) {
        return Optional.empty();
    }
}
