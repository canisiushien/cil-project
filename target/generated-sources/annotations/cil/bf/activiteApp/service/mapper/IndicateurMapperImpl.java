package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Indicateur;
import cil.bf.activiteApp.service.dto.IndicateurDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:30:28+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class IndicateurMapperImpl implements IndicateurMapper {

    @Override
    public IndicateurDTO toDto(Indicateur indicateur) {
        if ( indicateur == null ) {
            return null;
        }

        IndicateurDTO indicateurDTO = new IndicateurDTO();

        indicateurDTO.setId( indicateur.getId() );
        indicateurDTO.setValeur( indicateur.getValeur() );
        indicateurDTO.setLibelle( indicateur.getLibelle() );
        indicateurDTO.setReference( indicateur.getReference() );
        indicateurDTO.setCible( indicateur.getCible() );
        indicateurDTO.setTypeIndicateur( indicateur.getTypeIndicateur() );
        indicateurDTO.setUnite( indicateur.getUnite() );

        return indicateurDTO;
    }

    @Override
    public Indicateur toEntity(IndicateurDTO indicateurDTO) {
        if ( indicateurDTO == null ) {
            return null;
        }

        Indicateur indicateur = new Indicateur();

        indicateur.setId( indicateurDTO.getId() );
        indicateur.setValeur( indicateurDTO.getValeur() );
        indicateur.setLibelle( indicateurDTO.getLibelle() );
        indicateur.setReference( indicateurDTO.getReference() );
        indicateur.setCible( indicateurDTO.getCible() );
        indicateur.setTypeIndicateur( indicateurDTO.getTypeIndicateur() );
        indicateur.setUnite( indicateurDTO.getUnite() );

        return indicateur;
    }
}
