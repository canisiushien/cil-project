package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Exercice;
import cil.bf.activiteApp.service.dto.ExerciceDTO;
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
public interface ExerciceMapper {
    ExerciceDTO toDto(Exercice exercice);

    Exercice toEntity(ExerciceDTO exerciceDTO);
}