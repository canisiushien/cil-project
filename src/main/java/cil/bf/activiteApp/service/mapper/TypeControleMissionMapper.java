package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Profil;
import cil.bf.activiteApp.domain.TypeControleMission;
import cil.bf.activiteApp.service.dto.ProfilDTO;
import cil.bf.activiteApp.service.dto.TypeControleMissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TypeControleMissionMapper {
    TypeControleMissionDTO toDto(TypeControleMission typeControleMission);

    TypeControleMission toEntity(TypeControleMissionDTO typeControleMissionDTO);
}