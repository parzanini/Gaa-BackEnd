package com.gaaclub.controller;

import com.gaaclub.model.Events;
import com.gaaclub.model.Matches;
import com.gaaclub.model.Members;
import com.gaaclub.service.EventsService;
import com.gaaclub.service.MatchesService;
import com.gaaclub.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://gaa-frontend.onrender.com",allowedHeaders = "*")
public class MainController {

    private final EventsService eventsService;
    private final MatchesService matchesService;
    private final MembersService membersService;

    @Autowired
    public MainController(EventsService eventsService, MatchesService matchesService, MembersService membersService) {
        this.eventsService = eventsService;
        this.matchesService = matchesService;
        this.membersService = membersService;
    }

    //-----------------GET REQUESTS-----------------
    @GetMapping("/events")
    public List<Events> getEvents() {
        return eventsService.getAllEvents();
    }

    @GetMapping("/matches")
    public List<Matches> getMatches() {
        return matchesService.getAllMatches();
    }

    @GetMapping("/members")
    public List<Members> getMembers() {
        return membersService.getAllMembers();
    }

    @GetMapping("/events/{id}")
    public Events getEventById(@PathVariable(name = "id") Long event_id) {
        return eventsService.getEventById(event_id);
    }

    @GetMapping("/matches/{id}")
    public Matches getMatchById(@PathVariable(name = "id") Long match_id) {
        return matchesService.getMatchById(match_id);
    }
    @GetMapping("/members/{id}")
    public Members getMemberById(@PathVariable(name = "id") String member_id) {
        return membersService.getMemberById(member_id);
    }
    @GetMapping("/events/{id}/matches")
    public List<Matches> getMatchesForEvent(@PathVariable long id) {
        return eventsService.getMatchesForEvent(id);
    }
    //-----------------POST REQUESTS-----------------
    @PostMapping("/addevent")
    public  ResponseEntity <String> addEvent (@RequestBody Events event){
        try {
            eventsService.saveEvent(event);
            return ResponseEntity.ok("Event added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add the event");
        }
    }

    @PostMapping("/addmatch")
    public ResponseEntity<String> addMatch(@RequestBody Matches match) {
        try {
            matchesService.saveMatch(match);
            return ResponseEntity.ok("Match added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add the match");
        }
    }
    @PostMapping("/addmember")
    public ResponseEntity<String> addMember(@RequestBody Members member) {
        try {
            membersService.saveMember(member);
            return ResponseEntity.ok("Member added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add the member");
        }
    }
    //-----------------PUT REQUESTS-----------------
    @PutMapping("/updateevent/{id}")
    public ResponseEntity <String> updateEvent (@RequestBody Events event){
        try {
            eventsService.updateEvent(event);
            return ResponseEntity.ok("Event updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update the event");
        }
    }
    @PutMapping("/updatematch/{id}")
    public ResponseEntity <String> updateMatch (@RequestBody Matches match){
        try {
            matchesService.updateMatch(match);
            return ResponseEntity.ok("Match updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update the match");
        }
    }
    @PutMapping("/updatemember/{id}")
    public ResponseEntity <String> updateMember (@RequestBody Members member){
        try {
            membersService.updateMember(member);
            return ResponseEntity.ok("Member updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update the member");
        }
    }
    //-----------------DELETE REQUESTS-----------------
    @DeleteMapping("/deleteevent/{id}")
    public void deleteEvent(@PathVariable(name = "id") Long event_id) {
        eventsService.deleteEvent(event_id);
    }
    @DeleteMapping("/deletematch/{id}")
    public void deleteMatch(@PathVariable(name = "id") Long match_id) {
        matchesService.deleteMatch(match_id);
    }
    @DeleteMapping("/deletemember/{id}")
    public void deleteMember(@PathVariable(name = "id") String member_id) {
        membersService.deleteMember(member_id);
    }

    //------------------Keep Server Running------------------
    @GetMapping("/keepServerRunning")
    public String keepServerRunning() {
        return "OK TA RODANDO";
    }

}

