package csd.cs203project.shop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import csd.cs203project.model.Shop;
import csd.cs203project.model.User;
import csd.cs203project.repository.shop.ShopRepository;
import csd.cs203project.service.shop.ShopServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {
    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopServiceImpl shopService;

    /**
     * Test for adding new shop that is valid
     */
    @Test
    void addShop_NewShop_ReturnSavedShop(){
        //Arrange
        Shop shop = new Shop(0L, "TestShop", "restaurant", 0.0, 0, 0.0, new ArrayList<User>());
        when(shopRepository.findByName(any(String.class))).thenReturn(Optional.ofNullable(null));
        when(shopRepository.save(any(Shop.class))).thenReturn(shop);

        //Act
        Shop savedShop = shopService.addShop(shop);

        //Assert & Verify
        assertNotNull(savedShop);
        verify(shopRepository).findByName(shop.getName());
        verify(shopRepository).save(shop);
    }

    /**
     * Test for adding new shop that is repeated
     */
    @Test
    void addShop_RepeatedShop_ReturnNull(){
        //Arrange
        Shop shop = new Shop(0L, "TestShop", "restaurant", 0.0, 0, 0.0, new ArrayList<User>());
        when(shopRepository.findByName(any(String.class))).thenReturn(Optional.ofNullable(shop));

        //Act
        Shop savedShop = shopService.addShop(shop);

        //Assert & Verify
        assertNull(savedShop);
        verify(shopRepository).findByName(shop.getName());
    }

    /**
     * Test for update shop, where it is Valid
     */
    @Test
    void updateShop_Found_ReturnUpdatedShop(){
        //Arrange
        Shop shop = new Shop(0L, "TestShop", "restaurant", 0.0, 0, 0.0, new ArrayList<User>());
        when(shopRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(shop));
        when(shopRepository.save(any(Shop.class))).thenReturn(shop);

        //Act
        Shop updatedShop = shopService.updateShop(shop.getId(), shop);

        //Assert & Verify
        assertNotNull(updatedShop);
        verify(shopRepository).findById(shop.getId());
        verify(shopRepository).save(shop);
    }

    /**
     * Test for update shop, where it is Invalid
     */
    @Test
    void updateShop_NotFound_ReturnNull(){
        //Arrange
        Shop shop = new Shop(0L, "TestShop", "restaurant", 0.0, 0, 0.0, new ArrayList<User>());
        when(shopRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));

        //Act
        Shop updatedShop = shopService.updateShop(shop.getId(), shop);

        //Assert & Verify
        assertNull(updatedShop);
        verify(shopRepository).findById(shop.getId());
    }

    /**
     * Test for deleting shop
     */
    @Test
    void deleteShop_Deleted(){
        //Arrange
        Shop shop = new Shop(0L, "TestShop", "restaurant", 0.0, 0, 0.0, new ArrayList<User>());
        when(shopRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(shop));

        //Act
        shopService.deleteShop(shop.getId());

        //Assert & Verify
        verify(shopRepository, times(1)).deleteById(shop.getId());
    }
}
