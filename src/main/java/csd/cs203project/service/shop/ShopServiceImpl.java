package csd.cs203project.service.shop;

import csd.cs203project.model.Shop;
import csd.cs203project.repository.shop.ShopRepository;
import csd.cs203project.service.shop.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService{
    
    private ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop findById(int shopId){
        return shopRepository.findById(shopId);
    }

}
