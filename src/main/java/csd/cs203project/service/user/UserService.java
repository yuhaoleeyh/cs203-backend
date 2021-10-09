package csd.cs203project.service.user;

import java.util.List;

import csd.cs203project.model.User;

public interface UserService {
    List<User> findByShopShopType(String typeOfShop);

    void addUser(User user);

    User getUser(String email);

    public User updateUser(String email, User user);
}
