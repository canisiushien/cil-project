package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Indicateur;
import cil.bf.activiteApp.service.dto.IndicateurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IndicateurMapper {

    IndicateurDTO toDto(Indicateur indicateur);

    Indicateur toEntity(IndicateurDTO indicateurDTO);
}
