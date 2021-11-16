package csd.cs203project.service.tablelayout;

import java.util.ArrayList;
import java.util.HashMap;

import csd.cs203project.model.TableLayout;

public interface TableLayoutService {
    ArrayList<ArrayList<HashMap<String, Double>>> generateTableLayout(TableLayout tableLayout);
}
