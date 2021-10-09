package csd.cs203project.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csd.cs203project.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    List<User> findByCompany(String company);

    List<User> findByShopShopType(String typeOfShop);

    Optional<User> findByTelegramSignUpToken(String s);

    Optional<User> findByEmail(String email);
}
