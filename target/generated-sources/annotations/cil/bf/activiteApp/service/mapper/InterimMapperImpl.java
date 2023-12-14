package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Interim;
import cil.bf.activiteApp.service.dto.InterimDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class InterimMapperImpl implements InterimMapper {

    @Override
    public InterimDTO toDto(Interim interim) {
        if ( interim == null ) {
            return null;
        }

        InterimDTO interimDTO = new InterimDTO();

        interimDTO.setId( interim.getId() );
        interimDTO.setDateDebut( interim.getDateDebut() );
        interimDTO.setDateFin( interim.getDateFin() );
        interimDTO.setMotif( interim.getMotif() );
        interimDTO.setInterimaire( interim.getInterimaire() );
        interimDTO.setResponsable( interim.getResponsable() );
        interimDTO.setActif( interim.isActif() );

        return interimDTO;
    }

    @Override
    public Interim toEntity(InterimDTO interimDTO) {
        if ( interimDTO == null ) {
            return null;
        }

        Interim interim = new Interim();

        interim.setId( interimDTO.getId() );
        interim.setDateDebut( interimDTO.getDateDebut() );
        interim.setDateFin( interimDTO.getDateFin() );
        interim.setMotif( interimDTO.getMotif() );
        interim.setActif( interimDTO.isActif() );
        interim.setInterimaire( interimDTO.getInterimaire() );
        interim.setResponsable( interimDTO.getResponsable() );

        return interim;
    }
}
