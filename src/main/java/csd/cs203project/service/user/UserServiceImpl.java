package csd.cs203project.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import csd.cs203project.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import csd.cs203project.model.User;
import csd.cs203project.repository.user.UserRepository;

import java.security.SecureRandom;
import java.util.Base64;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    /** 
     * @param typeOfShop
     * @return List<User>
     */
    @Override
    public List<User> findByShopShopType(String typeOfShop) {
        return userRepository.findByShopShopType(typeOfShop);
    }

    
    /** 
     * @param user
     * @return User
     */
    @Override
    public User addUser(User user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if (u.isPresent()) {
            return null;
        }

        String telegramSignUpToken = generateSignUpToken(user.getId());
        user.setTelegramSignUpToken(telegramSignUpToken);

        return userRepository.save(user);
    }

    
    /** 
     * @param email
     * @return User
     */
    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).map(user -> {
            return user;
        }).orElse(null);
    }


    
    /** 
     * @param email
     * @param newUserInfo
     * @return User
     */
    @Override
    public User updateUser(String email, User newUserInfo) {
        Optional<User> u = userRepository.findByEmail(email);
        if (u.isPresent()){
            User user = u.get();
            user.setName(newUserInfo.getName());
            user.setVaccinationStatus(newUserInfo.getVaccinationStatus());
            user.setSwabTestResult(newUserInfo.getSwabTestResult());
            user.setFetStatus(newUserInfo.getFetStatus());
            user.setShop(newUserInfo.getShop());

            return userRepository.save(user);
        } else {
            return null;
        }
    }

    
    /** 
     * @param email
     */
    @Override
    public void deleteUser(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            userRepository.deleteByEmail(email);
        } else {
            throw new EmptyResultDataAccessException(1); 
        }
    }

    
    /** 
     * @param authorities
     * @return List<User>
     */
    @Override
    public List<User> findByAuthorities (String authorities) {
        return userRepository.findByAuthorities(authorities);
    }

    
    /** 
     * @param shops
     * @return List<User>
     */
    @Override
    public List<User> findByShops (List<Shop> shops) {
        List<User> users = new ArrayList<>();
        for (Shop shop : shops) {
            users.addAll(userRepository.findByShopId(shop.getId()));
        }

        return users;
    }

    
    /** 
     * @param id
     * @param authorities
     * @return List<User>
     */
    @Override
    public List<User> findByShopShopIdAndAuthorities (Long id, String authorities) {
        return userRepository.findByShopShopIdAndAuthorities(id, authorities);
    }

    
    /** 
     * @param userId
     * @return String
     */
    public String generateSignUpToken(Long userId) {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    
}
