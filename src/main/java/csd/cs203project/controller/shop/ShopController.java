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

    @PutMapping("/shops/{name}")
    public Shop updateShop (@PathVariable String name, @RequestBody Shop newShopInfo) {
        Shop shop = shopService.updateShop(name, newShopInfo);
        if (shop == null) {
            throw new ResourceNotFoundException("Shop with name " + name);
        }
        return shop;
    }

    @DeleteMapping("/shops/{name}")
    public void deleteShop (@PathVariable String name) {
        try {
            shopService.deleteShop(name);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Shop with name " + name);
        }
    }
}
