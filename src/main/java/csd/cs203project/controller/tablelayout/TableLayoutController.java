package csd.cs203project.controller.tablelayout;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import csd.cs203project.model.*;
import csd.cs203project.service.supervisor.*;
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
    public ArrayList<ArrayList<HashMap<String, Integer>>> getTableLayout() {
    
        return tablelayoutService.generateTableLayout();
    }


}