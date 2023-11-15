package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Activite;
import cil.bf.activiteApp.service.dto.ActiviteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ActiviteMapper {

    ActiviteDTO toDto(Activite activite);

    Activite toEntity(ActiviteDTO activiteDTO);
}
