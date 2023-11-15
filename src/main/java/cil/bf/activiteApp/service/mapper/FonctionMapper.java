package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Fonction;
import cil.bf.activiteApp.service.dto.FonctionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FonctionMapper {
    FonctionDTO toDto(Fonction exercice);

    Fonction toEntity(FonctionDTO exerciceDTO);
}