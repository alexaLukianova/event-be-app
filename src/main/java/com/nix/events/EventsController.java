package com.nix.events;

import com.nix.events.dao.Event;
import com.nix.events.exceptions.EventNotFoundException;
import com.nix.events.repositories.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EventsController {

    private EventRepository eventRepository;

    public EventsController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event newEvent) {
        return eventRepository.save(newEvent);
    }

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @PutMapping("/events/{id}")
    public Event update(@RequestBody Event newEvent, @PathVariable Long id) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setTitle(newEvent.getTitle());
                    event.setDate(newEvent.getDate());
                    event.setDescription(newEvent.getDescription());
                    event.setImagePath(newEvent.getImagePath());

                    return eventRepository.save(event);
                })
                .orElseGet(() -> {
                    newEvent.setId(id);
                    return eventRepository.save(newEvent);
                });
    }

    @DeleteMapping("/events/{id}")
    void delete(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }
}
