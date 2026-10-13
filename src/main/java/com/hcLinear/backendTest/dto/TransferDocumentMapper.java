package com.hcLinear.backendTest.dto;

import com.hcLinear.backendTest.entities.TransferDocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferDocumentMapper {

    @Mapping(target = "transferRequestId", source = "transferRequest.id")
    @Mapping(target = "playerId", source = "player.id")
    @Mapping(target = "playerName", expression = "java(transferDocumentEntity.getPlayer().getFirstName() + \" \" + transferDocumentEntity.getPlayer().getLastName())")
    @Mapping(target = "sourceTeamId", source = "sourceTeam.id")
    @Mapping(target = "sourceTeamName", source = "sourceTeam.name")
    @Mapping(target = "targetTeamId", source = "targetTeam.id")
    @Mapping(target = "targetTeamName", source = "targetTeam.name")
    TransferDocumentDTO toTransferDocumentDTO(TransferDocumentEntity transferDocumentEntity);
}
