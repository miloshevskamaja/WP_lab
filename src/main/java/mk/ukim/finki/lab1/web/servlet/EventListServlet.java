package mk.ukim.finki.lab1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab1.model.Event;
import mk.ukim.finki.lab1.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name="event-servlet", urlPatterns = "/servlet/events")
public class EventListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final EventService eventService;

    EventListServlet(SpringTemplateEngine springTemplateEngine, EventService eventService){
        this.springTemplateEngine=springTemplateEngine;
        this.eventService=eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Event> eventList;
        String searchName=req.getParameter("searchingString");
        String searchRating=req.getParameter("searchingRating");
        String catName=req.getParameter("category");




        if(searchName!=null & searchRating!=null && !Objects.equals(searchRating,"") && catName!=null){
            //eventList=this.eventService.searchByNameRating(searchName,Double.parseDouble(searchRating), catName);
            //eventList=this.eventService.searchByCategory(catName);
            eventList=this.eventService.searchEvents(searchName);
        }
        else{
            eventList=this.eventService.listAll();
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("categories", this.eventService.list());
        context.setVariable("events", eventList);




        this.springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }

}
