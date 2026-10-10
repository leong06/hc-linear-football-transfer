package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.entities.TeamEntity;
import com.hcLinear.backendTest.exceptions.ConflictException;
import com.hcLinear.backendTest.exceptions.NotFoundException;
import com.hcLinear.backendTest.exceptions.ValidationException;
import com.hcLinear.backendTest.repositories.TeamRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private static final int MIN_FOUNDED_YEAR = 1800;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamEntity> findAll() {
        return teamRepository.findAll();
    }

    public TeamEntity findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nem található a csapat a " + id + "azonosítóval!"));
    }

    public TeamEntity save(TeamEntity team) {
        int currentYear = LocalDate.now().getYear();
        if (team.getFoundedYear() > currentYear || team.getFoundedYear() < MIN_FOUNDED_YEAR) {
            throw new ValidationException("A csapat létrehozásának éve nem lehet a jelenlegi évvel nagyobb vagy a " + MIN_FOUNDED_YEAR + " évvel kisebb!");
        }
        if (teamRepository.existsByName(team.getName().trim())) {
            throw new ConflictException("A csapat neve már létezik!");
        }

        return teamRepository.save(team);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }



}
