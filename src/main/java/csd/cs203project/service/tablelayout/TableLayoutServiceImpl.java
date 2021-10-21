package csd.cs203project.service.tablelayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import csd.cs203project.model.TableLayout;
import csd.cs203project.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TableLayoutServiceImpl implements TableLayoutService {
    private UserRepository userRepository;
    
    @Autowired
    public TableLayoutServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public ArrayList<ArrayList<HashMap<String, Integer>>> generateTableLayout(TableLayout tableLayout) {
        int widthOfShop = tableLayout.getWidthOfShop();
        int heightOfShop = tableLayout.getHeightOfShop();
        int numOfTables = tableLayout.getNumOfTables();
        int widthOfTable = tableLayout.getWidthOfTable();
        int heightOfTable = tableLayout.getHeightOfTable();

        int multiplierSpacing = 2; //spacing between each table (i assume it to be the width/height of 1 table)

        int numOfWidths = widthOfShop / (widthOfTable * multiplierSpacing);

        int numOfHeights = heightOfShop / (heightOfTable * multiplierSpacing);

        if (numOfTables > numOfWidths * numOfHeights) {
            return null;
        } 


        ArrayList<ArrayList<HashMap<String, Integer>>> resultantList = new ArrayList<>();

        int counterForTables = 0;

        boolean setTables = true;


        for (int i = 0; i < numOfHeights; i++) { //iterate through the height
            

            ArrayList<HashMap<String, Integer>> innerList = new ArrayList<>();
            for (int j = 0; j < numOfWidths; j++) { //for the width
                HashMap<String, Integer> hashInnerMap = new HashMap<>();

                hashInnerMap.put("x", j + 1);

                if (i % multiplierSpacing != 0 || j % multiplierSpacing != 0 || !setTables) { //indicating it will be a blank space
                    hashInnerMap.put("y", 0);
                } else { //indicating a table should be filled there
                    if (counterForTables == numOfTables) {
                        setTables = false;
                    }
                    hashInnerMap.put("y", 100);
                    counterForTables++;
                    
                }

                innerList.add(hashInnerMap);

            }
            resultantList.add(innerList);
        }


        return resultantList; //data now being represented as a 2D arraylist, within which we have a hashmap which represents each position.
        //print the list to find out more 
    }
}