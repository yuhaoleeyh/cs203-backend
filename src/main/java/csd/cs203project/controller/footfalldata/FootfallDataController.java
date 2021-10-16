package csd.cs203project.controller.footfalldata;

import csd.cs203project.model.FootfallData;
import csd.cs203project.service.footfalldata.FootfallDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FootfallDataController {
    private FootfallDataServiceImpl footfallDataServiceImpl;

    @Autowired
    public FootfallDataController (FootfallDataServiceImpl footfallDataServiceImpl) {
        this.footfallDataServiceImpl = footfallDataServiceImpl;
    }

    @PostMapping("/footfallData")
    public void reloadFootfallData () {
        footfallDataServiceImpl.reloadFootfallData();
    }

    @GetMapping("/footfallData")
    public String getJsonResponse() {
        return footfallDataServiceImpl.getJsonResponse();
    }
}
