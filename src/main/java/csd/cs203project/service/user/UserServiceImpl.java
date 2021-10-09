package csd.cs203project.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csd.cs203project.model.User;
import csd.cs203project.repository.user.UserRepository;
import csd.cs203project.telegrambot.TelegramBot;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private TelegramBot telegramBot;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TelegramBot telegramBot) {
        this.userRepository = userRepository;
        this.telegramBot = telegramBot;
    }

    @Override
    public List<User> findByShopShopType(String typeOfShop) {
        return userRepository.findByShopShopType(typeOfShop);
    }

    @Override
    public void addUser(User user) {
        //Generate TelegramSignUpToken
        String telegramSignUpToken = telegramBot.generateSignUpToken(user.getId());
        user.setTelegramSignUpToken(telegramSignUpToken);

        userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).map(user -> {
            return user;
        }).orElse(null);
    }

    @Override
    public User updateUser(String email, User newUserInfo) {
        return userRepository.findByEmail(email).map(user -> {
            user.setName(newUserInfo.getName());
            user.setTelegramHandle(newUserInfo.getTelegramHandle());
            return userRepository.save(user);
        }).orElse(null);
    }
}
