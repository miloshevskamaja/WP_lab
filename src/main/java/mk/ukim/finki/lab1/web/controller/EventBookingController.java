package mk.ukim.finki.lab1.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab1.model.SavedBooking;
import mk.ukim.finki.lab1.model.User;
import mk.ukim.finki.lab1.service.EventBookingService;
import mk.ukim.finki.lab1.service.SavedBookingService;
import mk.ukim.finki.lab1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final SavedBookingService savedBookingService;
    private final UserService userService;


    public EventBookingController(EventBookingService eventBookingService, SavedBookingService savedBookingService, UserService userService) {
        this.eventBookingService = eventBookingService;
        this.savedBookingService = savedBookingService;
        this.userService = userService;
    }



    @PostMapping
    public String getEventBookingPage(@RequestParam String user,
                                      @RequestParam Integer numTickets,
                                      @RequestParam String event,
                                      HttpServletRequest request,
                                      Model model){

        // this works but not with InMemoryAuthentication
        //User hostAddress=(User) request.getSession().getAttribute("user");
        String username = request.getRemoteUser();

       // User hostAddress = this.userService.findByUsername(username);
        savedBookingService.save(event,numTickets,username);
        List<SavedBooking> savedBookingList=savedBookingService.getSavedBookings();

        model.addAttribute("savedBookings",savedBookingList);
        model.addAttribute("hostName",username);
        model.addAttribute("hostAddress", username);
        model.addAttribute("eventName",event);
        model.addAttribute("numOfTickets",numTickets);

        if(eventBookingService.canBook(event,numTickets)) {
            return "bookingConfirmation";
        }else{
            return "redirect:/events?error=NotEnoughTickets";
        }
    }



}
