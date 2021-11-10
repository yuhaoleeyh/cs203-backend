package csd.cs203project.service.user;

import java.util.List;
import java.util.Optional;

import csd.cs203project.model.User;

public interface UserService {
    List<User> findByShopShopType(String typeOfShop);

    User addUser(User user);

    User getUser(String email);

    public User updateUser(String email, User user);
}
