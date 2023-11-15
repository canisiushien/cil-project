package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.TypeReunion;
import cil.bf.activiteApp.service.dto.TypeReunionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TypeReunionMapper {

    TypeReunionDTO toDto(TypeReunion t);

    TypeReunion toEntity(TypeReunionDTO t);
}
