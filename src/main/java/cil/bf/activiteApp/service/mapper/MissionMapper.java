package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Mission;
import cil.bf.activiteApp.service.dto.MissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MissionMapper {

    MissionDTO toDto(Mission mission);

    Mission toEntity(MissionDTO missionDTO);
}
