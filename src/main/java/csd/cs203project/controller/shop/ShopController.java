package csd.cs203project.controller.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import csd.cs203project.model.Shop;
import csd.cs203project.service.shop.ShopService;

@RestController
public class ShopController {

    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }

    @GetMapping("/shops/shopId/{shopId}")
    public Shop findById(@PathVariable("shopId") int shopId){
        return shopService.findById(shopId);
    }
}
