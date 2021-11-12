package csd.cs203project.service.tablelayout;

import java.util.ArrayList;
import java.util.HashMap;

import csd.cs203project.model.TableLayout;
import org.springframework.stereotype.Service;

@Service
public class TableLayoutServiceImpl implements TableLayoutService {

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

        final int multiplierSpacing = tableGap + 1;

        int numOfWidths = widthOfShop / widthOfTable;

        int numOfLengths = lengthOfShop / lengthOfTable;


        if (numOfWidths == 0 || numOfLengths == 0) {
            return null;
        }

        ArrayList<ArrayList<HashMap<String, Double>>> tableLayoutList = new ArrayList<>();


        for (int i = 0; i < numOfLengths; i++) { 
            
            ArrayList<HashMap<String, Double>> lengthList = new ArrayList<>();
            for (int j = 0; j < numOfWidths; j++) { 
                HashMap<String, Double> hashInnerMap = hashMapLength(i, j, multiplierSpacing, widthOfTable, lengthOfTable);
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
                                                                                   
    public HashMap<String, Double> hashMapLength(int lengthNumber, int widthNumber, int multiplierSpacing, int widthOfTable, int lengthOfTable) {      
        final double BLANK_TABLE = 0.00;
        final double INCREMENT = 1.00;

        HashMap<String, Double> hashInnerMap = new HashMap<>();

        hashInnerMap.put("x", widthNumber + INCREMENT);

        if (lengthNumber % multiplierSpacing != 0 || widthNumber % multiplierSpacing != 0) { 
            hashInnerMap.put("y", BLANK_TABLE);
        } else if ((lengthNumber % multiplierSpacing == 0 && widthNumber % multiplierSpacing == 0)) { 
            String number = widthOfTable + "." + lengthOfTable;
            hashInnerMap.put("y", Double.valueOf(number));
            
            
        }

        
        return hashInnerMap;
    }
}