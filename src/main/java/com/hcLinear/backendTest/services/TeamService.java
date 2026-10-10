package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.entities.TeamEntity;
import com.hcLinear.backendTest.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamEntity> findAll() {
        return teamRepository.findAll();
    }

    public Optional<TeamEntity> findById(Long id) {
        return teamRepository.findById(id);
    }

    public TeamEntity save(TeamEntity team) {
        return teamRepository.save(team);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }



}
