package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Profil;
import cil.bf.activiteApp.service.dto.ProfilDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProfilMapper {

    ProfilDTO toDto(Profil profil);

    Profil toEntity(ProfilDTO profilDTO);
}
