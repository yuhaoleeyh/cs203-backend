package csd.cs203project.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import csd.cs203project.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    List<User> findByShopShopType(String typeOfShop);

    List<User> findByAuthorities(String authorities);

    List<User> findByShopId(Long id);

    @Query(value="SELECT * FROM user WHERE shop_id = ?1 and authorities = ?2", nativeQuery = true)
    List<User> findByShopShopIdAndAuthorities(Long id, String authorities);

    Optional<User> findByTelegramSignUpToken(String s);

    Optional<User> findByEmail(String email);

    @Transactional
    Long deleteByEmail(String email);
}
