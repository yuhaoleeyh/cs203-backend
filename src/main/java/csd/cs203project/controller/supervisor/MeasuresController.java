package csd.cs203project.controller.supervisor;

import csd.cs203project.model.Measures;
import csd.cs203project.service.measures.MeasuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MeasuresController {
    private MeasuresService measuresService;

    @Autowired
    public MeasuresController(MeasuresService measuresService) {
        this.measuresService = measuresService;
    }

    @PostMapping("/measures")
    public void addMeasures(@RequestBody @Valid Measures measures) {
        measuresService.addMeasures(measures);

    }
}
