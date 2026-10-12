package com.hcLinear.backendTest.dto;

import com.hcLinear.backendTest.entities.PlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target ="fullName", expression = "java(player.getFirstName() + \" \" + player.getLastName())")
    @Mapping(target = "teamName", source = "team.name")
    PlayerDTO toPlayerDTO(PlayerEntity player);

}
