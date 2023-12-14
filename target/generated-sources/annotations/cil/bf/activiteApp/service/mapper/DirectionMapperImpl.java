package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Direction;
import cil.bf.activiteApp.service.dto.DirectionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:30:28+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class DirectionMapperImpl implements DirectionMapper {

    @Override
    public DirectionDTO toDto(Direction direction) {
        if ( direction == null ) {
            return null;
        }

        DirectionDTO directionDTO = new DirectionDTO();

        directionDTO.setId( direction.getId() );
        directionDTO.setLibelle( direction.getLibelle() );
        directionDTO.setSigle( direction.getSigle() );
        directionDTO.setResponsable( direction.getResponsable() );

        return directionDTO;
    }

    @Override
    public Direction toEntity(DirectionDTO directionDTO) {
        if ( directionDTO == null ) {
            return null;
        }

        Direction direction = new Direction();

        direction.setSigle( directionDTO.getSigle() );
        direction.setId( directionDTO.getId() );
        direction.setLibelle( directionDTO.getLibelle() );
        direction.setResponsable( directionDTO.getResponsable() );

        return direction;
    }
}
