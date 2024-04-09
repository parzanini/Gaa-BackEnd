package com.gaaclub.repository;
import com.gaaclub.model.Members;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface MembersRepository extends MongoRepository<Members, String> {


    Members findByEmail(String email);
}
