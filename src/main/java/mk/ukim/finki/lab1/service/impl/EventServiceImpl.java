package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.Event;
import mk.ukim.finki.lab1.model.Location;
import mk.ukim.finki.lab1.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.lab1.repository.jpa.CategoryRepository;
import mk.ukim.finki.lab1.repository.jpa.EventRepository;
import mk.ukim.finki.lab1.repository.jpa.LocationRepository;
import mk.ukim.finki.lab1.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository, CategoryRepository categoryRepository){
        this.eventRepository=eventRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }


    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.findByNameContaining(text);
    }

//    @Override
//    public List<Event> searchByNameRating(String text, double rating,String catName){
//        //Category category=new Category(catName);
//        return eventRepository.findByNameAndPopularityScoreAndCategoryContaining(text,rating,catName);
//    }

    public List<Event> searchByCategory(String catName){
        Category category=new Category(catName);
        return eventRepository.findByCategory(category);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }


    @Override
    public Optional<Event> save(String name, String description, double popularityScore, Long location,Long category, Integer availableTickets) {
        Location locationNew=this.locationRepository.findById(location)
                .orElseThrow(()->new LocationNotFoundException(location));
        Category categoryNew=this.categoryRepository.findById(category)
                .orElseThrow(()->new LocationNotFoundException(category));

        Event event=new Event(name,description,popularityScore,categoryNew,locationNew,availableTickets);
        return Optional.of(this.eventRepository.save(event));

    }

    @Override
    public Optional<Event> update(Long id, String name, String description, double popularityScore, Long location,Long category,Integer availableTickets) {
        Event event=this.eventRepository.findById(id).orElse(null);
        Location locationNova=this.locationRepository.findById(location).orElseThrow(()->new LocationNotFoundException(location));
        Category categoryNova=this.categoryRepository.findById(category).orElseThrow(()->new LocationNotFoundException(category));
        if(event!= null){
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(locationNova);
            event.setCategory(categoryNova);

            return Optional.of(this.eventRepository.save(event));
        }
        return Optional.empty();
    }

    @Override
    public List<Event> findAllByLocationId(Long id) {
        return eventRepository.findAllByLocation_id(id);
    }


}
