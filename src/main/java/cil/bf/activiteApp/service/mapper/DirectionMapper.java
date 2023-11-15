/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Direction;
import cil.bf.activiteApp.service.dto.DirectionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DirectionMapper {

    DirectionDTO toDto(Direction direction);

    Direction toEntity(DirectionDTO directionDTO);
}
