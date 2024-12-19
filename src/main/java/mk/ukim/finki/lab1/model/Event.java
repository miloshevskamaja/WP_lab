package mk.ukim.finki.lab1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;


@Getter
@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double popularityScore;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Location location;
    private Integer availableTickets;

    public Event() {
    }

    public Event(String name, String description, double popularityScore, Category category, Location location, Integer availableTickets) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.category=category;
        this.location=location;
        this.availableTickets=availableTickets;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPopularityScore(double popularityScore) {
        this.popularityScore = popularityScore;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
