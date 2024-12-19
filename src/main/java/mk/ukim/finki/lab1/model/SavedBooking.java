package mk.ukim.finki.lab1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Entity
public class SavedBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private int numberOfTickets;

    @ManyToOne
    private User user;

    public SavedBooking(){
    }

    public SavedBooking(String eventName, int numberOfTickets, User user) {
        this.eventName = eventName;
        this.numberOfTickets = numberOfTickets;
        this.user=user;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
