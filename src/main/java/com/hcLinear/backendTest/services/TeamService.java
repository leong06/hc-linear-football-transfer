package com.hcLinear.backendTest.services;

import com.hcLinear.backendTest.dto.CreateTeamDTO;
import com.hcLinear.backendTest.dto.TeamDTO;
import com.hcLinear.backendTest.dto.TeamMapper;
import com.hcLinear.backendTest.entities.PlayerEntity;
import com.hcLinear.backendTest.entities.TeamEntity;
import com.hcLinear.backendTest.exceptions.ConflictException;
import com.hcLinear.backendTest.exceptions.NotFoundException;
import com.hcLinear.backendTest.exceptions.ValidationException;
import com.hcLinear.backendTest.repositories.PlayerRepository;
import com.hcLinear.backendTest.repositories.TeamRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TeamMapper teamMapper;
    private static final int MIN_FOUNDED_YEAR = 1800;

    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.teamMapper = teamMapper;
    }

    private TeamDTO teamToTeamDTO(TeamEntity team) {
        long playerCount = playerRepository.countByTeamId(team.getId());

        String captainName = null;
        Optional<PlayerEntity> captain = playerRepository.findByTeamIdAndCaptainTrue(team.getId());
        if(captain.isPresent()) {
            captainName = captain.get().getFirstName() + " " + captain.get().getLastName();
        }
        return teamMapper.toTeamDTO(team, playerCount, captainName);

    }


    public List<TeamDTO> findAll() {
        List<TeamDTO> teams = new ArrayList<>();
        for (TeamEntity team : teamRepository.findAll()) {
            teams.add(teamToTeamDTO(team));
        }
        return teams;
    }


    public TeamDTO findById(Long id) {
        return teamToTeamDTO(findEntityById(id));
    }


    public TeamEntity findEntityById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nem található a csapat a " + id + "azonosítóval!"));
    }

    public TeamDTO save(CreateTeamDTO createTeamDTO) {

        TeamEntity team = teamMapper.toTeamEntity(createTeamDTO);

        team.setName(team.getName().trim());
        int currentYear = LocalDate.now().getYear();
        if (team.getFoundedYear() > currentYear || team.getFoundedYear() < MIN_FOUNDED_YEAR) {
            throw new ValidationException("A csapat létrehozásának éve nem lehet a jelenlegi évvel nagyobb vagy a " + MIN_FOUNDED_YEAR + " évvel kisebb!");
        }
        if (teamRepository.existsByName(team.getName())) {
            throw new ConflictException("A csapat neve már létezik!");
        }

        return teamToTeamDTO(teamRepository.save(team));
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }



}
