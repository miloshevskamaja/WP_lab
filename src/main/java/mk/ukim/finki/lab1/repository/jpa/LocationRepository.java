package mk.ukim.finki.lab1.repository.jpa;

import mk.ukim.finki.lab1.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
