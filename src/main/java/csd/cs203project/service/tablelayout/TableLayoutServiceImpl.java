package csd.cs203project.service.tablelayout;

import java.util.ArrayList;
import java.util.HashMap;

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



    /**
     * Function to generate the table layout for the table 
     * @Input TableLayout tablelayout:
     * Consists of: widthOfShop, lengthOfShop, widthOfTable, lengthOfTable
     * @Output A 2D arraylist with each hashmap representing a grid in the table layout
     * 
     */
    @Override
    public ArrayList<ArrayList<HashMap<String, Double>>> generateTableLayout(TableLayout tableLayout) {
        int widthOfShop = tableLayout.getWidthOfShop(); 
        int lengthOfShop = tableLayout.getLengthOfShop(); 
        int widthOfTable = tableLayout.getWidthOfTable();
        int lengthOfTable = tableLayout.getLengthOfTable();
        int tableGap = tableLayout.getTableGap();
        int numOfWidths = widthOfShop / widthOfTable; //number of tables in each width
        int numOfLengths = lengthOfShop / lengthOfTable; //number of tables in each length

        final int multiplierSpacing = tableGap + 1; //number of tables for every multiplier

        if (numOfWidths == 0 || numOfLengths == 0) { //fail fast if the table is too small for the shop: edge case
            return null;
        }

        ArrayList<ArrayList<HashMap<String, Double>>> tableLayoutList = new ArrayList<>();
        //construction of the tablelayout list 
        for (int i = 0; i < numOfLengths; i++) { 
            ArrayList<HashMap<String, Double>> lengthList = new ArrayList<>();
            for (int j = 0; j < numOfWidths; j++) { 
                HashMap<String, Double> hashInnerMap = hashGridDetails(i, j, multiplierSpacing, widthOfTable, lengthOfTable);
                lengthList.add(hashInnerMap);
            }
            tableLayoutList.add(lengthList);
        }
        return tableLayoutList; 
    }


    /**
     * Function to generate whether the grid of dimension: widthOfTable by lengthOfTable should have a table there or not 
     * @Input 
     * int lengthNumber: the length we are currently on
     * int widthNumber: the width we are currently on
     * int multiplierSpacing: the number of tables we have for every space
     * int widthOfTable: width of each table
     * int lengthOfTable: length of each table
     * @Output A grid, which would be blank if no table should be there, and a shaded region with the table width/length dimensions if a table is there
     * 
     */                                                                              
    public HashMap<String, Double> hashGridDetails(int lengthNumber, int widthNumber, int multiplierSpacing, int widthOfTable, int lengthOfTable) {      
        final double BLANK_TABLE = 0.00;
        final double INCREMENT = 1.00;

        HashMap<String, Double> hashInnerMap = new HashMap<>();
        hashInnerMap.put("x", widthNumber + INCREMENT); //add the x which represents the width we are referring to at the moment

        if (lengthNumber % multiplierSpacing != 0 || widthNumber % multiplierSpacing != 0) { 
            hashInnerMap.put("y", BLANK_TABLE); //no table at this point
        } else if ((lengthNumber % multiplierSpacing == 0 && widthNumber % multiplierSpacing == 0)) { 
            String number = widthOfTable + "." + lengthOfTable; //string to show on the table with its dimensions
            hashInnerMap.put("y", Double.valueOf(number));      //adding a table at this point 
        }
        return hashInnerMap;
    }
}


