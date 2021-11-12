package csd.cs203project.service.user;

import java.util.List;
import java.util.Optional;

import csd.cs203project.model.Shop;
import csd.cs203project.model.User;

public interface UserService {
    List<User> findByShopShopType(String typeOfShop);

    User addUser(User user);

    User getUser(String email);

    User updateUser(String email, User newUserInfo);

    void deleteUser(String email);

//    List<User> findEmployeesByCompany(String company);
//
//    List<User> findEmployeesAndAdminsUnderCompany(String company);

    List<User> findByAuthorities (String authorities);

    List<User> findByShops (List<Shop> shops);

    List<User> findByShopShopIdAndAuthorities (Long id, String authorities);
}
