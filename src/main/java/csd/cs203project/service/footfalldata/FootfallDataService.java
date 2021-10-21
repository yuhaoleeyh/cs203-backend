package csd.cs203project.service.footfalldata;

import csd.cs203project.model.FootfallData;

import java.util.List;

public interface FootfallDataService {
    /**
     * @return list of FootfallData objects from db
     */
    List<FootfallData> listFootfallData();

    /** Reloads footfall data db by calling API */
    void reloadFootfallData();

    /**
     * @return JSON response as a String
     */
    String getJsonResponse();
}
