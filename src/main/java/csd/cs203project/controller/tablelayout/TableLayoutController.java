package csd.cs203project.controller.tablelayout;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import javax.validation.Valid;

import csd.cs203project.model.*;
import csd.cs203project.service.tablelayout.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TableLayoutController {
    private TableLayoutService tablelayoutService;

    @Autowired
    public TableLayoutController(TableLayoutService tableLayoutService) {
        this.tablelayoutService = tableLayoutService;
    }

    @RequestMapping(value = "/tablelayout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<ArrayList<HashMap<String, Double>>> getTableLayout(@Valid @RequestBody TableLayout tableLayout) {
        return tablelayoutService.generateTableLayout(tableLayout);
    }


}