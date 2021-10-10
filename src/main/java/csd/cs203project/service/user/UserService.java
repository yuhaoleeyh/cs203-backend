package csd.cs203project.service.user;

import csd.cs203project.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findByShopShopType(String typeOfShop);

    void addUser(User user);

    User findByEmail (String s);
}
