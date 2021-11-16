package csd.cs203project.measures;

import csd.cs203project.model.Measures;
import csd.cs203project.model.Shop;
import csd.cs203project.model.User;
import csd.cs203project.repository.measures.MeasuresRepository;
import csd.cs203project.service.measures.MeasuresServiceImpl;
import csd.cs203project.service.notifications.NotificationsService;
import csd.cs203project.service.shop.ShopService;
import csd.cs203project.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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

    @Mock
    private ShopService shopService;

    @Spy
    @InjectMocks
    private MeasuresServiceImpl measuresService;


    /**
     * Test for Measures Existing and findByTypeOfShop
     */
    @Test
    void findByTypeOfShop_MeasuresExist_ReturnFoundMeasures() {
        //arrange
        String typeOfShop = "fastfoodoutlet";
        Measures measures = new Measures(1L, "fastfoodoutlet", 2,2,2,2,"11:11:11", "3");
        // mock the "findByTypeOfShop" operation
        when(measuresRepository.findByTypeOfShop(any(String.class))).thenReturn(List.of(measures));

        //act
        Measures foundMeasures = measuresService.findByTypeOfShop(typeOfShop);

        //assert
        assertEquals(foundMeasures, measures);

        verify(measuresRepository).findByTypeOfShop(typeOfShop);
    }

    /**
     * Test for Measures NOT Existing and findByTypeOfShop
     */
    @Test
    void findByTypeOfShop_NoSuchMeasures_ReturnNull() {
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

    /**
     * Test for No Change in Measures
     */
    @Test
    void getChangeInMeasures_NoChanges_ReturnEmptyArrayList() {
        //arrange
        Measures measures = new Measures(1L, "fastfoodoutlet", 2,2,2,2,"11:11:11", "3");

        //act
        List<String> changes = measuresService.getChangeInMeasures(measures, measures);

        //assert
        assertEquals(changes, new ArrayList<>());

    }

    /**
     * Test for Changes in Measures
     */
    @Test
    void getChangeInMeasures_WithChanges_ReturnArrayListWithChanges() {
        
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

    /**
     * Test for Updating Measures
     */
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

    /**
     * Test for Updating Measures with No Changes
     */
    @Test
    void updateMeasures_WithOldMeasuresAndNoChanges_ReturnNewMeasures() {
        //arrange
        Measures oldMeasures = new Measures(1L, "fastfoodoutlet", 2,2,2,2,"11:11:11", "3");
        Measures newMeasures = new Measures(1L, "fastfoodoutlet", 3,3,3,3,"11:11:11", "3");
        //mock the "findByTypeOfShop" operation
        doReturn(oldMeasures).when(measuresService).findByTypeOfShop(any(String.class));
        //mock the "getChangeInMeasures" operation
        doReturn(new ArrayList<String>()).when(measuresService).getChangeInMeasures(any(Measures.class), any(Measures.class));
        //mock the "shopService.findByShopType" operation
        when(shopService.findByShopType(any(String.class))).thenReturn(new ArrayList<Shop>());
        //mock the "userService.findByShops" operation
        when(userService.findByShops(any(List.class))).thenReturn(new ArrayList<User>());
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
        verify(shopService).findByShopType(newMeasures.getTypeOfShop());
        verify(userService).findByShops(new ArrayList<Shop>());
        verify(measuresRepository).deleteByTypeOfShop(newMeasures.getTypeOfShop());
        verify(measuresRepository).save(newMeasures);
    }

    /**
     * Test for Updating Measures with Changes
     */
    @Test
    void updateMeasures_WithOldMeasuresAndWithChanges_ReturnNewMeasures() {
        //arrange
        Measures oldMeasures = new Measures(1L, "fastfoodoutlet", 2,2,2,2,"11:11:11", "3");
        Measures newMeasures = new Measures(1L, "fastfoodoutlet", 3,3,3,3,"22:22:22", "2");
        List<String> changes = List.of("1");
        //mock the "findByTypeOfShop" operation
        doReturn(oldMeasures).when(measuresService).findByTypeOfShop(any(String.class));
        //mock the "getChangeInMeasures" operation
        doReturn(changes).when(measuresService).getChangeInMeasures(any(Measures.class), any(Measures.class));
        //mock the "shopService.findByShopType" operation
        when(shopService.findByShopType(any(String.class))).thenReturn(new ArrayList<Shop>());
        //mock the "userService.findByShops" operation
        when(userService.findByShops(any(List.class))).thenReturn(new ArrayList<User>());
        //mock the "notificationsService.sendChangedMeasures" operation
        doNothing().when(notificationsService).sendChangedMeasures(any(List.class), any(List.class), any(String.class));
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
        verify(shopService).findByShopType(newMeasures.getTypeOfShop());
        verify(userService).findByShops(new ArrayList<Shop>());
        verify(notificationsService).sendChangedMeasures(changes, new ArrayList<User>(), newMeasures.getTypeOfShop());
        verify(measuresRepository).deleteByTypeOfShop(newMeasures.getTypeOfShop());
        verify(measuresRepository).save(newMeasures);
    }
}
