package csd.cs203project.controller.measures;

import csd.cs203project.model.Measures;
import csd.cs203project.service.measures.MeasuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MeasuresController {
    private MeasuresService measuresService;

    @Autowired
    public MeasuresController(MeasuresService measuresService) {
        this.measuresService = measuresService;
    }

    @PostMapping("/measures")
    public void addMeasures(@RequestBody Measures measures) {
        System.out.println(measures);
        measuresService.addMeasures(measures);
    }

    @GetMapping("/measures/{shopType}")
    public Measures getMeasures(@PathVariable("shopType") String shopType){
        return measuresService.findByTypeOfShop(shopType);
    }

}
