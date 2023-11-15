package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Periodicite;
import cil.bf.activiteApp.service.dto.PeriodiciteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PeriodiciteMapper {

    PeriodiciteDTO toDto(Periodicite p);

    Periodicite toEntity(PeriodiciteDTO p);
}
