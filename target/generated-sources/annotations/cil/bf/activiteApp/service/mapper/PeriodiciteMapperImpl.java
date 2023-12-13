package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Periodicite;
import cil.bf.activiteApp.service.dto.PeriodiciteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T18:47:57+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PeriodiciteMapperImpl implements PeriodiciteMapper {

    @Override
    public PeriodiciteDTO toDto(Periodicite p) {
        if ( p == null ) {
            return null;
        }

        PeriodiciteDTO periodiciteDTO = new PeriodiciteDTO();

        periodiciteDTO.setId( p.getId() );
        periodiciteDTO.setLibelle( p.getLibelle() );

        return periodiciteDTO;
    }

    @Override
    public Periodicite toEntity(PeriodiciteDTO p) {
        if ( p == null ) {
            return null;
        }

        Periodicite periodicite = new Periodicite();

        periodicite.setId( p.getId() );
        periodicite.setLibelle( p.getLibelle() );

        return periodicite;
    }
}
