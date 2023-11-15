package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Programme;
import cil.bf.activiteApp.service.dto.ProgrammeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Created by Zak TEGUERA on 14/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProgrammeMapper {
    ProgrammeDTO toDto(Programme programme);

    Programme toEntity(ProgrammeDTO programmeDTO);
}