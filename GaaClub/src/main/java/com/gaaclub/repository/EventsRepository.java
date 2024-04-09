package com.gaaclub.repository;

import com.gaaclub.model.Events;
import com.gaaclub.model.Matches;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventsRepository extends MongoRepository<Events, Long>{

}
