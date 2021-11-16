package csd.cs203project.controller.footfalldata;

import csd.cs203project.service.footfalldata.FootfallDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FootfallDataController {
    private FootfallDataService footfallDataService;

    @Autowired
    public FootfallDataController (FootfallDataService footfallDataService) {
        this.footfallDataService = footfallDataService;
    }

    /** 
     * Reload Footfall data
     * This method ensures that the Footfall data fetched is always up-to-date with the original source
     */
    @PostMapping("/footfallData")
    public void reloadFootfallData () {
        footfallDataService.reloadFootfallData();
    }

    
    /** 
     * Returns Footfall data in JSON string response
     * @return Footfall data
     */
    @GetMapping("/footfallData")
    public String getJsonResponse() {
        return footfallDataService.getJsonResponse();
    }
}
