package mk.ukim.finki.lab1.web.controller;

import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.Event;
import mk.ukim.finki.lab1.model.Location;
import mk.ukim.finki.lab1.service.EventService;
import mk.ukim.finki.lab1.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService,LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String searchingString,
                                @RequestParam(required = false) String searchingRating,
                                @RequestParam(required = false) String category,
                                Model model){
        List<Event> eventList;
        if(searchingString!=null){
            //eventList=this.eventService.searchByNameRating(searchingString,Double.parseDouble(searchingRating),category);
            eventList=this.eventService.searchEvents(searchingString);
        }else{
            eventList=this.eventService.listAll();
        }

        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }

        List<Category> categories=this.eventService.list();
        model.addAttribute("categories",categories);
        model.addAttribute("events",eventList);
        return "listEvents";
    }

    @GetMapping("/add-form")
    public String addEventPage(Model model){
        List<Location> locations=this.locationService.findAll();
        List<Category> categories=this.eventService.list();
        model.addAttribute("categories",categories);
        model.addAttribute("locations",locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam(required = false) Long id,@RequestParam String name, @RequestParam String description, @RequestParam String rating, @RequestParam Long location,@RequestParam Long category, @RequestParam Integer availableTickets){
        double popularityScore=Double.parseDouble(rating);
        if(id!=null){
            this.eventService.update(id,name,description,popularityScore,location,category,availableTickets);
        }else {
            this.eventService.save(name, description, popularityScore, location,category,availableTickets);
        }
        return "redirect:/events";
    }


    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id,Model model){
        if(this.eventService.findById(id).isPresent()){
            Event event = this.eventService.findById(id).get();
            List<Location> locations=this.locationService.findAll();
            List<Category> categories=this.eventService.list();
            model.addAttribute("categories",categories);
            model.addAttribute("event",event);
            model.addAttribute("locations",locations);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        if(eventService.findById(id).isPresent()) {
            this.eventService.deleteById(id);
            return "redirect:/events";
        }else{
            return "redirect:/events?error=EventNotFound";
        }
    }

}
