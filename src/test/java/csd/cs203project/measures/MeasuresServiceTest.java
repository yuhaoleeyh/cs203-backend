package csd.cs203project.measures;

import csd.cs203project.model.Measures;
import csd.cs203project.model.User;
import csd.cs203project.repository.measures.MeasuresRepository;
import csd.cs203project.repository.user.UserRepository;
import csd.cs203project.service.measures.MeasuresService;
import csd.cs203project.service.measures.MeasuresServiceImpl;
import csd.cs203project.service.notifications.NotificationsService;
import csd.cs203project.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeasuresServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private MeasuresRepository measuresRepository;

    @Mock
    private NotificationsService notificationsService;

    @Spy
    @InjectMocks
    private MeasuresServiceImpl measuresService;


    @Test
    void findByTypeOfShop_ReturnFoundMeasures() {
        //arrange
        String typeOfShop = "fastfoodoutlet";
        Measures measures = new Measures(1L, "fastfoodoutlet", 2,2,2,2,"11:11:11", "3");
        // mock the "findByTypeOfShop" operation
        when(measuresRepository.findByTypeOfShop(any(String.class))).thenReturn(List.of(measures));

        //act
        Measures foundMeasures = measuresService.findByTypeOfShop(typeOfShop);

        //assert
        assertNotNull(foundMeasures);

        verify(measuresRepository).findByTypeOfShop(typeOfShop);
    }

    @Test
    void findByTypeOfShop_ReturnNull() {
        //arrange
        String typeOfShop = "fastfoodoutlet";
        // mock the "findByTypeOfShop" operation
        when(measuresRepository.findByTypeOfShop(any(String.class))).thenReturn(new ArrayList<Measures>());

        //act
        Measures foundMeasures = measuresService.findByTypeOfShop(typeOfShop);

        //assert
        assertNull(foundMeasures);

        verify(measuresRepository).findByTypeOfShop(typeOfShop);
    }

    @Test
    void getChangeInMeasures_NoChanges() {
        //arrange
        Measures measures = new Measures(1L, "fastfoodoutlet", 2,2,2,2,"11:11:11", "3");

        //act
        List<String> changes = measuresService.getChangeInMeasures(measures, measures);

        //assert
        assertEquals(changes, new ArrayList<>());

    }

    @Test
    void getChangeInMeasures_WithChanges() {
        //arrange
        Measures oldMeasures = new Measures(1L, "fastfoodoutlet", 2,2,2,2,"11:11:11", "3");
        Measures newMeasures = new Measures(1L, "fastfoodoutlet", 3,3,3,3,"22:22:22", "2");
        List<String> testChanges = List.of(
                "The Dine In Size changed from 2 to 3",
                "The Max Group Size for Vaccinated customers changed from 2 to 3",
                "The Max Group Size for Non Vaccinated customers changed from 2 to 3",
                "The Social Distance changed from 2 to 3",
                "The Closing Time changed from 11:11:11 to 22:22:22",
                "The Phase changed from 3 to 2"
        );

        //act
        List<String> changes = measuresService.getChangeInMeasures(oldMeasures, newMeasures);

        //assert
        assertEquals(changes, testChanges);

    }

    @Test
    void updateMeasures_NoOldMeasures_ReturnNewMeasures() {
        //arrange
        Measures newMeasures = new Measures(1L, "fastfoodoutlet", 3,3,3,3,"22:22:22", "2");
        //mock the "findByTypeOfShop" operation
        doReturn(null).when(measuresService).findByTypeOfShop(any(String.class));
        //mock the "deleteByTypeOfShop" operation
        doNothing().when(measuresRepository).deleteByTypeOfShop(any(String.class));
        //mock the "save" operation
        when(measuresRepository.save(any(Measures.class))).thenReturn(newMeasures);

        //act
        Measures measures = measuresService.updateMeasures(newMeasures);

        //assert
        assertEquals(newMeasures, measures);

        verify(measuresService).findByTypeOfShop(newMeasures.getTypeOfShop());
        verify(measuresRepository).deleteByTypeOfShop(newMeasures.getTypeOfShop());
        verify(measuresRepository).save(newMeasures);
    }

    @Test
    void updateMeasures_WithOldMeasures_ReturnNewMeasures() {
        //arrange
        Measures oldMeasures = new Measures(1L, "fastfoodoutlet", 2,2,2,2,"11:11:11", "3");
        Measures newMeasures = new Measures(1L, "fastfoodoutlet", 3,3,3,3,"22:22:22", "2");
        //mock the "findByTypeOfShop" operation
        doReturn(oldMeasures).when(measuresService).findByTypeOfShop(any(String.class));
        //mock the "getChangeInMeasures" operation
        doReturn(new ArrayList<String>()).when(measuresService).getChangeInMeasures(any(Measures.class), any(Measures.class));
        //mock the "findByShopShopType" operation
        when(userService.findByShopShopType(any(String.class))).thenReturn(new ArrayList<>());
        //mock the "deleteByTypeOfShop" operation
        doNothing().when(measuresRepository).deleteByTypeOfShop(any(String.class));
        //mock the "save" operation
        when(measuresRepository.save(any(Measures.class))).thenReturn(newMeasures);

        //act
        Measures measures = measuresService.updateMeasures(newMeasures);

        //assert
        assertEquals(newMeasures, measures);

        verify(measuresService).findByTypeOfShop(newMeasures.getTypeOfShop());
        verify(measuresService).getChangeInMeasures(oldMeasures, newMeasures);
        verify(userService).findByShopShopType(newMeasures.getTypeOfShop());
        verify(measuresRepository).deleteByTypeOfShop(newMeasures.getTypeOfShop());
        verify(measuresRepository).save(newMeasures);
    }


}
