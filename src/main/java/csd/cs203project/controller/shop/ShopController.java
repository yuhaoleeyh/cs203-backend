package csd.cs203project.controller.shop;

import csd.cs203project.exception.ResourceExistsException;
import csd.cs203project.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import csd.cs203project.model.Shop;
import csd.cs203project.service.shop.ShopService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ShopController {

    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }

    @GetMapping("/shops/shopId/{shopId}")
    public Shop findById(@PathVariable("shopId") Long shopId){
        return shopService.findById(shopId);
    }

    @GetMapping("/shops")
    public List<Shop> getAllShops () {
        return shopService.findAllShops();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/shops")
    public Shop addShop (@RequestBody Shop shop) {
        Shop savedShop = shopService.addShop(shop);
        if (savedShop == null) {
            throw new ResourceExistsException("Shop with name " + shop.getName());
        }
        return savedShop;
    }

    @PutMapping("/shops/{id}")
    public Shop updateShop (@PathVariable Long id, @RequestBody Shop newShopInfo) {
        Shop shop = shopService.updateShop(id, newShopInfo);
        if (shop == null) {
            throw new ResourceNotFoundException("Shop with id " + id);
        }
        return shop;
    }

    @DeleteMapping("/shops/{id}")
    public void deleteShop (@PathVariable Long id) {
        try {
            shopService.deleteShop(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Shop with id " + id);
        }
    }
}
