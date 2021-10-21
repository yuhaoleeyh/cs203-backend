package csd.cs203project.service.tablelayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

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
    public ArrayList<ArrayList<HashMap<String, Integer>>> generateTableLayout() {
        int widthOfShop = 10000;
        int heightOfShop = 10000;
        int numOfTables = 100;
        int widthOfTable = 500;
        int heightOfTable = 500;

        int multiplierSpacing = 2; //spacing between each table (i assume it to be the width/height of 1 table)

        int numOfWidths = widthOfShop / (widthOfTable * multiplierSpacing);

        int numOfHeights = heightOfShop / (heightOfTable * multiplierSpacing);

        if (numOfTables > numOfWidths * numOfHeights) {
            return null;
        } 

        // HashMap<Character, Integer> mMap = new HashMap();
        // List<Map> list = new ArrayList();

        ArrayList<ArrayList<HashMap<String, Integer>>> resultantList = new ArrayList<>();


        for (int i = 0; i < numOfHeights; i++) {
            

            ArrayList<HashMap<String, Integer>> innerList = new ArrayList<>();
            for (int j = 0; j < numOfWidths; j++) {
                HashMap<String, Integer> hashInnerMap = new HashMap<>();

                hashInnerMap.put("x", j + 1);

                if (i % multiplierSpacing != 0 || j % multiplierSpacing != 0) {
                    hashInnerMap.put("y", 0);
                } else {
                    hashInnerMap.put("y", 100);
                }

                innerList.add(hashInnerMap);

            }
            resultantList.add(innerList);
        }


        return resultantList;
    }
}


// series: [
//     {
//         name: 'Net Profit',
//         data: [
//         { x: '1', y: 30 },
//         { x: '2', y: 40 },
//         { x: '3', y: 22 },
//         { x: '4', y: 82 },
//         { x: '5', y: 44 },
//         { x: '6', y: 87 },
//         { x: '7', y: 69 },
//         { x: '8', y: 53 },
//         { x: '9', y: 64 },
//         { x: '10', y: 5 },
//         { x: '11', y: 72 },
//         { x: '12', y: 88 },
//         { x: '13', y: 15 },
//         { x: '14', y: 67 },
//         { x: '15', y: 55 },
//         { x: '16', y: 22 },
//         { x: '17', y: 43 },
//         { x: '18', y: 41 }
//         ]
//     },
//     {
//         name: 'Revenue',
//         data: [
//         { x: '7', y: 69 },
//         { x: '8', y: 53 },
//         { x: '9', y: 64 },
//         { x: '10', y: 5 },
//         { x: '11', y: 72 },
//         { x: '1', y: 30 },
//         { x: '2', y: 40 },
//         { x: '3', y: 22 },
//         { x: '16', y: 22 },
//         { x: '4', y: 82 },
//         { x: '5', y: 44 },
//         { x: '6', y: 87 },
//         { x: '12', y: 88 },
//         { x: '13', y: 15 },
//         { x: '14', y: 67 },
//         { x: '15', y: 55 },
//         { x: '17', y: 43 },
//         { x: '18', y: 41 }
//         ]
//     },
//     {
//         name: 'Growth',
//         data: [
//         { x: '1', y: 30 },
//         { x: '2', y: 40 },
//         { x: '13', y: 15 },
//         { x: '14', y: 67 },
//         { x: '15', y: 55 },
//         { x: '3', y: 22 },
//         { x: '4', y: 82 },
//         { x: '5', y: 44 },
//         { x: '6', y: 87 },
//         { x: '7', y: 69 },
//         { x: '8', y: 53 },
//         { x: '9', y: 64 },
//         { x: '10', y: 5 },
//         { x: '11', y: 72 },
//         { x: '12', y: 88 },
//         { x: '16', y: 22 },
//         { x: '17', y: 43 },
//         { x: '18', y: 41 }
//         ]
//     }
// ]