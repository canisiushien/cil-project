package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Reunion;
import cil.bf.activiteApp.service.dto.ReunionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReunionMapper {


    @Mappings({
            @Mapping(source = "typeReunion.id", target = "idTypeReunion"),
            @Mapping(source = "typeSession.id", target = "idTypeSession"),
            // Exclure la liste des participants du mapping
            @Mapping(target = "membresReunion", ignore = true),
            @Mapping(target = "rapporteur", ignore = true),
            @Mapping(target = "president", ignore = true)


    })
    ReunionDTO toDto(Reunion r);

    @Mappings({
            @Mapping(source = "idTypeReunion", target = "typeReunion.id"),
            // Exclure la liste des participants du mapping
            @Mapping(target = "membresReunion", ignore = true),
            @Mapping(target = "rapporteur", ignore = true),
            @Mapping(target = "president", ignore = true)
    })
    Reunion toEntity(ReunionDTO r);
}
