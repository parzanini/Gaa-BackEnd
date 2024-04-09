package com.gaaclub.service;

import com.gaaclub.model.Matches;
import com.gaaclub.repository.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchesServiceImpl implements MatchesService {

    @Autowired
    private MatchesRepository matchesRepository;

    @Override
    public Matches getMatchById(long id) {
        Optional <Matches> optional = matchesRepository.findById(id);
        Matches match = null;
        if (optional.isPresent()) {
            match = optional.get();
        } else {
            throw new RuntimeException(" match not found for id :: " + id);
        }
        return match;
    }

    @Override
    public void saveMatch(Matches match) {
        matchesRepository.save(match);
    }

    @Override
    public void deleteMatch(long id) {
        this.matchesRepository.deleteById(id);
    }

    @Override
    public void updateMatch(Matches match) {
this.matchesRepository.save(match);
    }

    @Override
    public List<Matches> getAllMatches() {
        return matchesRepository.findAll();
    }

}
