package com.hcLinear.backendTest.dto;

import com.hcLinear.backendTest.entities.TeamEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDTO toTeamDTO(TeamEntity team, long playerCount, String captainName);
}
