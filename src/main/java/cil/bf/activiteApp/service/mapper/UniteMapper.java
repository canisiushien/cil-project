package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Unite;
import cil.bf.activiteApp.service.dto.UniteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UniteMapper {

    UniteDTO toDto(Unite u);

    Unite toEntity(UniteDTO u);
}
