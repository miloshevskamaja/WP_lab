package mk.ukim.finki.lab1.repository.jpa;

import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByNameAndPopularityScoreAndCategory(String name, double rating, Category category);
    List<Event> findByCategory(Category category);
    List<Event> findByNameContaining(String text);
    //List<Event> findByNameAndPopularityScoreAndCategoryContaining(String name, double rating, String category);
    List<Event> findAllByLocation_id(Long id);
    Optional<Event> findByName(String name);
}
