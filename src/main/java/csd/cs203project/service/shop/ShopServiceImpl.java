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

    
    /** 
     * @param shopId
     * @return Shop
     */
    @Override
    public Shop findById(Long shopId){
        return shopRepository.findById(shopId).orElse(null);
    }

    
    /** 
     * @return List<Shop>
     */
    @Override
    public List<Shop> findAllShops() {
        return shopRepository.findAll();
    }

    
    /** 
     * @param shopType
     * @return List<Shop>
     */
    @Override
    public List<Shop> findByShopType (String shopType) {
        return shopRepository.findByShopType(shopType);
    }

    
    /** 
     * @param shop
     * @return Shop
     */
    @Override
    public Shop addShop (Shop shop) {
        Optional<Shop> shopOptional = shopRepository.findByName(shop.getName());
        if (!shopOptional.isPresent()) {
            return shopRepository.save(shop);
        }
        return null;
    }

    
    /** 
     * @param id
     * @param newShop
     * @return Shop
     */
    @Override
    public Shop updateShop(Long id, Shop newShop) {
        return shopRepository.findById(id).map(shop -> {
            shop.setName(newShop.getName());
            shop.setShopType(newShop.getShopType());
            shop.setArea(newShop.getArea());
            shop.setNumTables(newShop.getNumTables());
            shop.setSizeTables(newShop.getSizeTables());
            return shopRepository.save(shop);
        }).orElse(null);
    }

    
    /** 
     * @param id
     */
    @Override
    public void deleteShop (Long id) {
        if (shopRepository.findById(id).isPresent()) {
            shopRepository.deleteById(id);
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }
}
