package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Privilege;
import cil.bf.activiteApp.service.dto.PrivilegeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PrivilegeMapper {
    PrivilegeDTO toDto(Privilege exercice);

    Privilege toEntity(PrivilegeDTO exerciceDTO);
}