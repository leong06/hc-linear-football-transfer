package com.hcLinear.backendTest.dto;

import com.hcLinear.backendTest.entities.TransferRequestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(target = "playerId", source = "player.id")
    @Mapping(target = "playerName", expression = "java(transfer.getPlayer().getLastName() + \" \" + transfer.getPlayer().getFirstName())")
    @Mapping(target = "sourceTeamName", source = "sourceTeam.name")
    @Mapping(target = "sourceTeamId", source = "sourceTeam.id")
    @Mapping(target = "targetTeamName", source = "targetTeam.name")
    @Mapping(target = "targetTeamId", source = "targetTeam.id")
    TransferDTO toTransferDTO(TransferRequestEntity transfer);
}
