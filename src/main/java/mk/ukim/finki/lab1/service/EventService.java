package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.Event;
import mk.ukim.finki.lab1.model.Location;
import mk.ukim.finki.lab1.model.SavedBooking;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
   // List<Event> searchByNameRating(String text,double rating, String catName);
    List<Category> list();
    List<Event> searchByCategory(String catName);
    Optional<Event> findById(Long id);
    void deleteById(Long id);
    Optional<Event> save(String name, String description, double popularityScore, Long location, Long category,Integer availableTickets);

    Optional<Event> update(Long id, String name, String description, double popularityScore, Long location,Long category, Integer availableTickets);

    List<Event> findAllByLocationId(Long id);
}
