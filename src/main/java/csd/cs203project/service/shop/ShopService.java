package csd.cs203project.service.shop;

import csd.cs203project.model.Shop;

import java.util.List;

public interface ShopService {
    
    Shop findById(Long shopId);

    List<Shop> findAllShops();

    Shop addShop (Shop shop);

    Shop updateShop (Long id, Shop newShop);

    void deleteShop (Long id);
}
