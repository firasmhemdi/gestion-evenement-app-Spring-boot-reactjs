package com.example.projetd.integration.Controller;

import com.example.projetd.integration.Entity.Event;
import com.example.projetd.integration.Entity.User;
import com.example.projetd.integration.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return eventService.updateEvent(id, updatedEvent);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/category/{categoryName}")
    public List<Event> getEventsByCategory(@PathVariable String categoryName) {
        return eventService.getEventsByCategory(categoryName);
    }

    @PutMapping("/{eventId}/inscriptions/{userId}/approuver")
    public String approuverInscription(@PathVariable Long eventId, @PathVariable Long userId) {
        return eventService.approuverInscription(eventId, userId);
    }

    @PutMapping("/{eventId}/inscriptions/{-}/refuser")
    public String refuserInscription(@PathVariable Long eventId, @PathVariable Long userId) {
        return eventService.refuserInscription(eventId, userId);
    }

    @GetMapping("/{eventId}/participants")
    public List<User> getParticipants(@PathVariable Long eventId) {
        return eventService.getParticipants(eventId);
    }
}
