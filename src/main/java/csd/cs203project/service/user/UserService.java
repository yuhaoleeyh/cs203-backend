package csd.cs203project.service.user;

import csd.cs203project.model.User;

import java.util.List;

public interface UserService {
    List<User> findByShopShopType(String typeOfShop);
}
