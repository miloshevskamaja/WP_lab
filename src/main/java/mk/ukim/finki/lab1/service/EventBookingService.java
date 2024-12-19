package mk.ukim.finki.lab1.service;

import mk.ukim.finki.lab1.model.EventBooking;
import mk.ukim.finki.lab1.model.SavedBooking;

import java.util.List;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);

    public boolean canBook(String eventName, int numOfTickets);

/*
    List<SavedBooking> getSavedBookings();
    void addBooking(String name, int number);
    List<SavedBooking> searchByName(String name);

     */

}
