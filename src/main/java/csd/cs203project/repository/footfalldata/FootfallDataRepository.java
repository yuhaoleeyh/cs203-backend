package csd.cs203project.repository.footfalldata;

import csd.cs203project.model.FootfallData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootfallDataRepository extends JpaRepository<FootfallData, Long> {
}
