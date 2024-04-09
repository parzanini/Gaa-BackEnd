package com.gaaclub.service;

import com.gaaclub.model.Matches;

import java.util.List;


public interface MatchesService {
    Matches getMatchById(long id);
    void saveMatch(Matches match);
    void deleteMatch(long id);
    void updateMatch(Matches match);
    List<Matches> getAllMatches();

}
