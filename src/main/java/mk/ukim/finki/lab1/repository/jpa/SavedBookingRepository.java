package mk.ukim.finki.lab1.repository.jpa;

import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.SavedBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedBookingRepository extends JpaRepository<SavedBooking,Long> {
    List<SavedBooking> findAll();

}
