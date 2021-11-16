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
     * Return shop by shop id
     * @param shopId shop id
     * @return Shop
     */
    @Override
    public Shop findById(Long shopId){
        return shopRepository.findById(shopId).orElse(null);
    }

    
    /** 
     * Return all shops
     * @return List<Shop>
     */
    @Override
    public List<Shop> findAllShops() {
        return shopRepository.findAll();
    }

    
    /** 
     * Return all shops of specific shop type
     * @param shopType specific shop type
     * @return List<Shop> all shops of specific shop type
     */
    @Override
    public List<Shop> findByShopType (String shopType) {
        return shopRepository.findByShopType(shopType);
    }

    
    /** 
     * Add new shop
     * @param shop new shop information
     * @return Shop new shop information
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
     * Update shop information based on shop id
     * @param id shop id
     * @param newShop Updated shop information
     * @return Shop Updated shop information
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
     * Delete shop by shop id
     * @param id shop id
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
