package com.example.projetd.integration.Service;

import com.example.projetd.integration.Entity.Category;
import com.example.projetd.integration.Entity.Event;
import com.example.projetd.integration.Entity.User;
import com.example.projetd.integration.repository.CategoryRepository;
import com.example.projetd.integration.repository.EventRepository;
import com.example.projetd.integration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id).map(event -> {
            event.setTitle(updatedEvent.getTitle());
            event.setCategory(updatedEvent.getCategory());
            event.setDate(updatedEvent.getDate());
            event.setLocation(updatedEvent.getLocation());
            return eventRepository.save(event);
        }).orElse(null);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getEventsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return eventRepository.findByCategory(category);
    }

    public String approuverInscription(Long eventId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);

        if (user.isPresent() && event.isPresent()) {
            Event e = event.get();
            e.getParticipants().add(user.get());
            eventRepository.save(e);
            return "Inscription approuvée.";
        }
        return "Utilisateur ou événement introuvable.";
    }

    public String refuserInscription(Long eventId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);

        if (user.isPresent() && event.isPresent()) {
            Event e = event.get();
            e.getParticipants().remove(user.get());
            eventRepository.save(e);
            return "Inscription refusée.";
        }
        return "Utilisateur ou événement introuvable.";
    }

    public List<User> getParticipants(Long eventId) {
        return eventRepository.findById(eventId)
                .map(Event::getParticipants)
                .orElse(List.of());
    }
}
