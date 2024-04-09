package com.gaaclub.repository;

import com.gaaclub.model.Matches;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchesRepository extends MongoRepository<Matches, Long>{
}
