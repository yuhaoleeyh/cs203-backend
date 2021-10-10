package csd.cs203project.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import csd.cs203project.model.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    @Query(
    value = "SELECT * FROM user u WHERE u.company = ?1 and u.user_type <> ?2", 
    nativeQuery = true)
    List<User> findEmployeesByCompany(String company, String userType);

    List<User> findByShopShopType(String typeOfShop);

    Optional<User> findByTelegramSignUpToken(String s);
}
