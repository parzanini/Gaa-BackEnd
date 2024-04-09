package com.gaaclub.service;

import com.gaaclub.model.Events;
import com.gaaclub.model.Matches;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EventsService {

    Events getEventById(long id);
    void saveEvent(Events event);
    void deleteEvent(long id);
    void updateEvent(Events event);
    List<Events> getAllEvents();

    List<Matches> getMatchesForEvent(long eventId);
}
