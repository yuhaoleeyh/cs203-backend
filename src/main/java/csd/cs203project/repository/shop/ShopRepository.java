package csd.cs203project.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csd.cs203project.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository <Shop, Long> {
    Optional<Shop> findById(Long shopId);

    Optional<Shop> findByName(String name);

    @Transactional
    Long deleteByName(String name);
}
