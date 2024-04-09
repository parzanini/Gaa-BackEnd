package com.gaaclub.model;

import com.gaaclub.repository.EventsRepository;
import com.gaaclub.repository.MatchesRepository;
import com.gaaclub.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyDataLoader implements ApplicationRunner {

    private final MembersRepository membersRepository;
    private final MatchesRepository matchesRepository;
    private final EventsRepository eventsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DummyDataLoader(MembersRepository membersRepository, MatchesRepository matchesRepository, EventsRepository eventsRepository, PasswordEncoder passwordEncoder) {
        this.membersRepository = membersRepository;
        this.matchesRepository = matchesRepository;
        this.eventsRepository = eventsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Define a date formatter for formatting LocalDate objects
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Insert dummy members
        Members member1 = new Members("Thiago", "Parzanini", "parza@hotmail.com", passwordEncoder.encode("123456"), "D01ABC", "123 Main St", "Dublin", "Dublin", Role.ADMIN);
        Members member2 = new Members("SpongeBob", "SquarePants", "sponge@bob.com", passwordEncoder.encode("123456"), "D02DEF", "456 Elm St", "Galway", "Galway", Role.USER);
        membersRepository.saveAll(List.of(member1, member2));

        // Insert dummy matches
        Matches match1 = new Matches(1,"Football Match", "Local football game", dateFormatter.format(LocalDate.of(2024,3,20)), "Croke Park", "15:00", "Donegal", "Dublin", "3-10 (5) - 1-10 (13)", "Won", "John Doe");
        Matches match2 = new Matches(2,"Hurling Match", "County hurling match", dateFormatter.format(LocalDate.of(2024,3,12)), "Semple Stadium", "14:30", "Tipperary", "Kilkenny", "6-11 (7) - 4-10 (13)", "Lost", "Jane Doe");
        Matches match3 = new Matches(3, "Camogie", "All-Ireland Senior Camogie Championship (Group Stage)",dateFormatter.format(LocalDate.of(2024, 4, 20)), "Cusack Park","14:00", "Galway", "Cork","2-15 (21) - 1-12 (15)", "Won", "Niamh Nic Dhonnchadha (Dublin)");
        Matches match4 = new Matches(4, "Ladies' Gaelic Football", "National Football League (Division 1)",dateFormatter.format(LocalDate.of(2024, 4, 21)), "Breffni Park","18:00", "Dublin", "Mayo","3-12 (21) - 2-10 (16)", "Won", "Siobhán Bean Uí Éadaoin (Kerry)");
        Matches match5  = new Matches(5,"Football Match","Local Football League (Division 2)",dateFormatter.format(LocalDate.of(2024, 4, 22)),"Páirc Uí Chaoimh","15:00","Cork","Kerry","1-12 (15) - 2-10 (16)","Lost","Máire Ní Chathasaigh (Galway)");
        Matches match6 = new Matches(6, "Gaelic Football", "Leinster Senior Football Championship (Preliminary Round)",dateFormatter.format(LocalDate.of(2024, 5, 5)), "O'Moore Park","14:00", "Laois", "Wicklow", "1-15 (18) - 0-14 (14)", "Won", "Dara Ó Conchúir (Louth)");
        Matches match7 = new Matches(7, "Hurling", "Munster Hurling League (Final)",dateFormatter.format(LocalDate.of(2024, 5, 5)), "Gaelic Grounds","16:00", "Limerick", "Clare", "1-21 (24) - 2-14 (20)", "Won", "Pádraig Ó Maolchalraigh (Cork)");
        Matches match8 = new Matches(8, "Camogie", "All-Ireland Senior Camogie Championship (Group Stage)",dateFormatter.format(LocalDate.of(2024, 5, 12)), "Upton Park","13:00", "Wexford", "Tipperary", "2-10 (16) - 1-12 (15)", "Won", "Sorcha Ní hAonghusa (Offaly)");
        Matches match9 = new Matches(9, "Ladies' Gaelic Football", "National Football League (Division 3)",dateFormatter.format(LocalDate.of(2024, 5, 12)), "Dooley Park","15:00", "Fermanagh", "Westmeath", "3-08 (17) - 1-11 (14)", "Won", "Aisling Ní hAodha (Kildare)");
        Matches match10 = new Matches(10, "Gaelic Football", "Connacht Senior Football Championship (Quarter-Final)", dateFormatter.format(LocalDate.of(2024, 5, 19)), "Pearse Stadium","14:00", "Galway", "Mayo", "0-13 (13) - 1-08 (11)", "Lost", "Seán Ó Cathaoir (Derry)");
        matchesRepository.saveAll(List.of(match1, match2, match3, match4, match5, match6, match7, match8, match9, match10));

        // Insert dummy events with matches
        Events event1 = new Events(1,"Local GAA Event","Local GAA event", new ArrayList<>());
        event1.setMatches(new ArrayList<>(List.of(match1, match2)));
        eventsRepository.save(event1);
        Events event2 = new Events(2,"National GAA Event","National GAA event", new ArrayList<>());
        event2.setMatches(new ArrayList<>(List.of(match3, match4)));
        eventsRepository.save(event2);
        Events event3 = new Events(3,"International GAA Event","International GAA event", new ArrayList<>());
        event3.setMatches(new ArrayList<>(List.of(match5, match6)));
        eventsRepository.save(event3);
        Events event4 = new Events(4,"Charity GAA Event","Charity GAA event", new ArrayList<>());
        event4.setMatches(new ArrayList<>(List.of(match7, match8)));
        eventsRepository.save(event4);
        Events event5 = new Events(5,"Community GAA Event","Community GAA event", new ArrayList<>());
        event5.setMatches(new ArrayList<>(List.of(match9, match10)));
    }
}
