package mk.ukim.finki.lab1.model.exceptions;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException() {
        super("Event not found");
    }
}
