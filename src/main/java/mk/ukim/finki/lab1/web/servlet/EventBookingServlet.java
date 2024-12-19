package mk.ukim.finki.lab1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab1.model.EventBooking;
import mk.ukim.finki.lab1.model.SavedBooking;
import mk.ukim.finki.lab1.service.EventBookingService;
import mk.ukim.finki.lab1.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@WebServlet(name="booking-servlet", urlPatterns = "/servlet/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final EventBookingService eventBookingService;
    EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventBookingService eventBookingService){
        this.springTemplateEngine=springTemplateEngine;
        this.eventBookingService=eventBookingService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*  List<SavedBooking> bookingsToSend;
        String searchName=req.getParameter("searchText");

        bookingsToSend=eventBookingService.searchByName(searchName);

        IWebExchange iWebExchange=JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp);

        WebContext context=new WebContext(iWebExchange);
        context.setVariable("savedBookings", bookingsToSend);
        springTemplateEngine.process("bookingConfirmation.html",context,resp.getWriter());

       */
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String eventName = req.getParameter("event");
        String numOfTickets = req.getParameter("numTickets");
        String user=req.getParameter("user");

        /* listing saved bookings
        List<SavedBooking> savedBookings = eventBookingService.getSavedBookings();
        eventBookingService.addBooking(eventName,Integer.parseInt(numOfTickets));
        */
        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context=new WebContext(iWebExchange);


            context.setVariable("hostName", user);
            context.setVariable("hostAddress", req.getRemoteAddr());
            context.setVariable("eventName",eventName);
            context.setVariable("numOfTickets",numOfTickets);
            //context.setVariable("savedBookings", savedBookings);
            springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());




    }
}
