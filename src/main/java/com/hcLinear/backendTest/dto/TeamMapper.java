package com.hcLinear.backendTest.dto;

import com.hcLinear.backendTest.entities.TeamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDTO toTeamDTO(TeamEntity team, long playerCount, String captainName);

    @Mapping(target = "id", ignore = true)
    TeamEntity toTeamEntity(CreateTeamDTO createTeamDTO);

}
