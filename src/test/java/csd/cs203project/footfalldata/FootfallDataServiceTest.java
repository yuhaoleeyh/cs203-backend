package csd.cs203project.footfalldata;

import csd.cs203project.model.FootfallData;
import csd.cs203project.repository.footfalldata.FootfallDataRepository;
import csd.cs203project.repository.footfalldata.LastUpdateDateRepository;
import csd.cs203project.service.footfalldata.FootfallDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FootfallDataServiceTest {
    @Mock
    private FootfallDataRepository footfallDataRepository;

    @Mock
    private LastUpdateDateRepository lastUpdateDateRepository;

    @InjectMocks
    private FootfallDataServiceImpl footfallDataService;

    @Test
    void calculateAverageTest() {
        //Arrange
        FootfallData footfallData1 = new FootfallData(1L, "01/01", 100.0, 10.0, 20.0, 30.0, 40.0);
        FootfallData footfallData2 = new FootfallData(2L, "02/01", 100.0, 10.0, 20.0, 30.0, 40.0);
        FootfallData footfallData3 = new FootfallData(3L, "01/01", 100.0, 10.0, 20.0, 30.0, 40.0);
        FootfallData footfallData4 = new FootfallData(4L, "01/01", 100.0, 10.0, 20.0, 30.0, 40.0);
        FootfallData footfallData5 = new FootfallData(5L, "01/01", 100.0, 10.0, 20.0, 30.0, 40.0);
        List<FootfallData> list = new ArrayList<>();
        list.add(footfallData1);
        list.add(footfallData2);
        list.add(footfallData3);
        list.add(footfallData4);
        list.add(footfallData5);

        //Act
        List<Double> averages = footfallDataService.calculateAverage(list);

        //Assert
        assertEquals(10.0, averages.get(0));
        assertEquals(20.0, averages.get(1));
        assertEquals(30.0, averages.get(2));
        assertEquals(40.0, averages.get(3));
    }
}

