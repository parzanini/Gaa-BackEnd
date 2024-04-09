package com.gaaclub.service;

import com.gaaclub.model.Events;
import com.gaaclub.model.Matches;
import com.gaaclub.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    private EventsRepository eventsRepository;


    @Override
    public Events getEventById(long id) {
        Optional <Events> optional = eventsRepository.findById(id);
        Events event = null;
        if (optional.isPresent()) {
            event = optional.get();
        } else {
            throw new RuntimeException(" event not found for id :: " + id);
        }
        return event;
    }

    @Override
    public void saveEvent(Events event) {
        eventsRepository.save(event);
    }

    @Override
    public void deleteEvent(long id) {
        this.eventsRepository.deleteById(id);
    }

    @Override
    public void updateEvent(Events event) {
        this.eventsRepository.save(event);
    }

    @Override
    public List<Events> getAllEvents() {
        return eventsRepository.findAll();
    }

    @Override
    public List<Matches> getMatchesForEvent(long eventId) {
        Events event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event id: " + eventId));
        return event.getMatches();

    }
}
