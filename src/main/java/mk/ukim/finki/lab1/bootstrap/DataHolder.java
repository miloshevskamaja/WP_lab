package mk.ukim.finki.lab1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.Event;
import mk.ukim.finki.lab1.model.Location;
import mk.ukim.finki.lab1.model.SavedBooking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events=new ArrayList<>();
    public static List<Category> categories=new ArrayList<>();
    public static List<Location> locations=new ArrayList<>();


    @PostConstruct
    public void init(){
        Category first = new Category("visoka kategorija");
        Category second= new Category("niska kategorija");
        Category third = new Category("sredna kategorija");
        categories.add(first);
        categories.add(second);
        categories.add(third);

        Location lokacija1 =new Location("first-location","outside","20","outside");
        Location lokacija2 =new Location("second-location","inside","40","inside");
        Location lokacija3 =new Location("third-location","address 3","50","address 3");
        Location lokacija4 =new Location("forth-location","address 4","60","address 4");
        Location lokacija5 =new Location("fifth-location","address 5","70","address 5");
        locations.add(lokacija1);
        locations.add(lokacija2);
        locations.add(lokacija3);
        locations.add(lokacija4);
        locations.add(lokacija5);



        events.add(new Event("Running Sprint", "reading",55.2,first,lokacija2,10));
        events.add(new Event("Watching Film", "watching",90.4,first,lokacija4,8));
        events.add(new Event("Running Jogging", "running",70.2,second,lokacija1,6));
        events.add(new Event("Watching TV", "sleeping",15.22,second,lokacija2,4));
        events.add(new Event("Eating", "eating",60.3,third,lokacija3,5));
        events.add(new Event("Studying", "studying",80.0,third,lokacija2,2));
        events.add(new Event("Climbing", "climbing",55.6,second,lokacija1,1));
        events.add(new Event("Back-end Programming", "driving",66.2,first,lokacija1,3));
        events.add(new Event("Front-end Programming", "programming",43.8,first,lokacija1,2));
        events.add(new Event("Relaxing", "relaxing",32.6,third,lokacija5,3));
    }
}
