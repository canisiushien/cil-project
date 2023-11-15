package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.TypeIndicateur;
import cil.bf.activiteApp.service.dto.TypeIndicateurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TypeIndicateurMapper {

    TypeIndicateurDTO toDto(TypeIndicateur t);

    TypeIndicateur toEntity(TypeIndicateurDTO t);
}
