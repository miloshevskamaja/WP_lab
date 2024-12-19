package mk.ukim.finki.lab1.service.impl;

import mk.ukim.finki.lab1.model.Event;
import mk.ukim.finki.lab1.model.EventBooking;
import mk.ukim.finki.lab1.repository.impl.InMemoryEventRepository;
import mk.ukim.finki.lab1.repository.jpa.EventRepository;
import mk.ukim.finki.lab1.repository.jpa.SavedBookingRepository;
import mk.ukim.finki.lab1.service.EventBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    @Autowired
    private final EventRepository eventRepository;



    public EventBookingServiceImpl(EventRepository eventRepository){
        this.eventRepository=eventRepository;

    }
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return new EventBooking(eventName,attendeeName,attendeeAddress,numberOfTickets);
    }


    @Override
    public boolean canBook(String eventName, int numOfTickets) {
        Optional<Event> event = eventRepository.findByName(eventName);

        if (event.isPresent()) {
            Event foundEvent = event.get();
            int available = foundEvent.getAvailableTickets();

            if (numOfTickets <= available) {
                foundEvent.setAvailableTickets(available - numOfTickets);
                eventRepository.save(foundEvent);
                return true;
            }
            return false;
        }
        return false;
    }

/*
    @Override
    public List<SavedBooking> getSavedBookings(){
        return eventRepository.getSavedBookings();
    }
    @Override
    public void addBooking(String name, int number){
        eventRepository.addBooking(name,number);
    }

    @Override
    public List<SavedBooking> searchByName(String name){
        return eventRepository.searchByName(name);
    }
    */

}
