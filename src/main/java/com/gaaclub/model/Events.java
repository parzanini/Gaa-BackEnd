package com.gaaclub.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Document(collection = "events")
public class Events {

@Id
@Generated
    private long id;
    private String name;
    private String description;

    @DBRef
    List <Matches> matches;

}
