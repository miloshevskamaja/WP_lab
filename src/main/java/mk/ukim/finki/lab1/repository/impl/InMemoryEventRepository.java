package mk.ukim.finki.lab1.repository.impl;

import mk.ukim.finki.lab1.bootstrap.DataHolder;
import mk.ukim.finki.lab1.model.*;
import mk.ukim.finki.lab1.model.exceptions.EventNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventRepository {

    public List<Event> findAll(){
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text){
        return DataHolder.events.stream().filter(r-> r.getName().toLowerCase().contains(text.toLowerCase()) ||
                r.getDescription().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }

    public List<Category> list(){
        return DataHolder.categories;
    }

    public List<Event> searchByNameRating(String text, double rating,String catName){
        return DataHolder.events.stream().filter(r-> r.getName().toLowerCase().contains(text.toLowerCase()) &&
                r.getPopularityScore()>=rating && r.getCategory().getCatName().equals(catName)).collect(Collectors.toList());
    }

    public List<Event> searchByCategory(String catName){
        return DataHolder.events.stream().filter(r->r.getCategory().getCatName().equals(catName)).collect(Collectors.toList());

    }

    public Optional<Event> findById(Long id){
        return DataHolder.events.stream().filter(r->r.getId().equals(id)).findFirst();
    }
    public EventBooking createBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets){
        return new EventBooking(eventName,attendeeName,attendeeAddress,numberOfTickets);
    }



    public Optional<Event> save(String name, String description, double popularityScore, Location location, Integer availableTickets){
        Category random=new Category("nova kategorija");
        Event event=new Event(name,description,popularityScore,random,location,availableTickets);
        DataHolder.events.add(event);
        return Optional.of(event);
    }

    public Optional<Event> update(Long id,String name, String description, double popularityScore, Location location,Integer availableTickets ){
        Event event=DataHolder.events.stream().filter(r->r.getId().equals(id)).findFirst().orElseThrow(()-> new EventNotFoundException());
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);
        event.setAvailableTickets(availableTickets);
        return Optional.of(event);

    }
    public void deleteById(Long id){
        DataHolder.events.removeIf(r->r.getId().equals(id));
    }



    public int getAvailableTickets(String name){
        Event event= (Event) DataHolder.events.stream()
                .filter(r->r.getName().equals(name))
                .findFirst().orElseThrow(()-> new IllegalArgumentException("Event not found "+name));
        return event.getAvailableTickets();
    }
    public void setAvailableTickets(String name,int num){
        Event event= (Event) DataHolder.events.stream()
                .filter(r->r.getName().equals(name))
                .findFirst().orElseThrow(()->new IllegalArgumentException("Event not found: "+name));
        event.setAvailableTickets(num);

    }

/*
    public List<SavedBooking> getSavedBookings(){
        return DataHolder.savedBookings;
    }

    public void addBooking(String name, int number){
        boolean bookingExists=false;
        for(SavedBooking booking: getSavedBookings()){
            if(booking.getEventName().equals(name)){
                bookingExists=true;
                break;
            }
        }

        if(!bookingExists){
            DataHolder.savedBookings.add(new SavedBooking(name,number));
        }
    }

    public List<SavedBooking> searchByName(String name) {
        return DataHolder.savedBookings.stream().filter(r -> r.getEventName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }


     */




}
