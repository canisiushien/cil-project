package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Activite;
import cil.bf.activiteApp.service.dto.ActiviteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:30:28+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ActiviteMapperImpl implements ActiviteMapper {

    @Override
    public ActiviteDTO toDto(Activite activite) {
        if ( activite == null ) {
            return null;
        }

        ActiviteDTO activiteDTO = new ActiviteDTO();

        activiteDTO.setId( activite.getId() );
        activiteDTO.setCode( activite.getCode() );
        activiteDTO.setLibelle( activite.getLibelle() );
        activiteDTO.setStatut( activite.getStatut() );
        activiteDTO.setCoutPrevisionnel( activite.getCoutPrevisionnel() );
        activiteDTO.setCoutReel( activite.getCoutReel() );
        activiteDTO.setUniteReel( activite.getUniteReel() );
        activiteDTO.setCibleReel( activite.getCibleReel() );
        activiteDTO.setResponsable( activite.getResponsable() );
        activiteDTO.setDateDebut( activite.getDateDebut() );
        activiteDTO.setDateFin( activite.getDateFin() );
        activiteDTO.setLieu( activite.getLieu() );
        activiteDTO.setIndicateur( activite.getIndicateur() );

        return activiteDTO;
    }

    @Override
    public Activite toEntity(ActiviteDTO activiteDTO) {
        if ( activiteDTO == null ) {
            return null;
        }

        Activite activite = new Activite();

        activite.setId( activiteDTO.getId() );
        activite.setCode( activiteDTO.getCode() );
        activite.setLibelle( activiteDTO.getLibelle() );
        activite.setStatut( activiteDTO.getStatut() );
        activite.setCoutPrevisionnel( activiteDTO.getCoutPrevisionnel() );
        activite.setCoutReel( activiteDTO.getCoutReel() );
        activite.setUniteReel( activiteDTO.getUniteReel() );
        activite.setCibleReel( activiteDTO.getCibleReel() );
        activite.setResponsable( activiteDTO.getResponsable() );
        activite.setDateDebut( activiteDTO.getDateDebut() );
        activite.setDateFin( activiteDTO.getDateFin() );
        activite.setLieu( activiteDTO.getLieu() );
        activite.setIndicateur( activiteDTO.getIndicateur() );

        return activite;
    }
}
