package mk.ukim.finki.lab1.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@Entity
@Table(name = "event_users")
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<SavedBooking> savedBookings;

    public User(){

    }

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

}
