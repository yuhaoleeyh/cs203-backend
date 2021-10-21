package csd.cs203project.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csd.cs203project.model.*;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository <Shop, Long> {
    Shop findById(int shopId);
}
