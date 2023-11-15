package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.MandatControle;
import cil.bf.activiteApp.service.dto.MandatControleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MandatControleMapper {

    MandatControleDTO toDto(MandatControle mandatControle);

    MandatControle toEntity(MandatControleDTO mandatControleDTO);
}
