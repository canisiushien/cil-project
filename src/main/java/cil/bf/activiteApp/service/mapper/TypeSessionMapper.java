package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.TypeSession;
import cil.bf.activiteApp.service.dto.TypeSessionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TypeSessionMapper {

    TypeSessionDTO toDto(TypeSession t);

    TypeSession toEntity(TypeSessionDTO t);
}
