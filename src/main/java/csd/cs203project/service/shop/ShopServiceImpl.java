package csd.cs203project.service.shop;

import csd.cs203project.model.Shop;
import csd.cs203project.repository.shop.ShopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService{
    
    private ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop findById(Long shopId){
        return shopRepository.findById(shopId).orElse(null);
    }

    @Override
    public List<Shop> findAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public List<Shop> findByShopType (String shopType) {
        return shopRepository.findByShopType(shopType);
    }

    @Override
    public Shop addShop (Shop shop) {
        Optional<Shop> shopOptional = shopRepository.findByName(shop.getName());
        if (!shopOptional.isPresent()) {
            return shopRepository.save(shop);
        }
        return null;
    }

    @Override
    public Shop updateShop(String name, Shop newShop) {
        return shopRepository.findByName(name).map(shop -> {
            shop.setName(newShop.getName());
            shop.setShopType(newShop.getShopType());
            shop.setArea(newShop.getArea());
            shop.setNumTables(newShop.getNumTables());
            shop.setSizeTables(newShop.getSizeTables());
            return shopRepository.save(shop);
        }).orElse(null);
    }

    @Override
    public void deleteShop (String name) {
        if (shopRepository.findByName(name).isPresent()) {
            shopRepository.deleteByName(name);
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }
}
