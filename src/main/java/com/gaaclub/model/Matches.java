package com.gaaclub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Document(collection = "matches")
public class Matches {

@Id
    private long id;
    private String name;
    private String description;
    private String date;
    private String location;
    private String time;
    private String team1;
    private String team2;
    private String score;
    private String result;
    private String referee;
}
