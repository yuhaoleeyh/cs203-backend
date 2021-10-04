package csd.cs203project.service.user;

import csd.cs203project.model.User;
import csd.cs203project.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByShopShopType(String typeOfShop) {
        return userRepository.findByShopShopType(typeOfShop);
    }
}
