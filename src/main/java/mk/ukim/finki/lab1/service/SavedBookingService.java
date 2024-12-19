package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.SavedBooking;

import java.util.List;
import java.util.Optional;

public interface SavedBookingService {

    Optional<SavedBooking> save(String eventName,int numberOfTickets,String user);
    List<SavedBooking> getSavedBookings();
}
