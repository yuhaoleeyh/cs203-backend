package csd.cs203project.repository.footfalldata;

import csd.cs203project.model.LastUpdateDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastUpdateDateRepository extends JpaRepository<LastUpdateDate, Long> {
}
