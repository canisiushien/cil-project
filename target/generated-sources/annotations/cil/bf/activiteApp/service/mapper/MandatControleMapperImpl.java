package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.MandatControle;
import cil.bf.activiteApp.service.dto.MandatControleDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-15T16:36:40+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class MandatControleMapperImpl implements MandatControleMapper {

    @Override
    public MandatControleDTO toDto(MandatControle mandatControle) {
        if ( mandatControle == null ) {
            return null;
        }

        MandatControleDTO mandatControleDTO = new MandatControleDTO();

        mandatControleDTO.setId( mandatControle.getId() );
        mandatControleDTO.setLibelle( mandatControle.getLibelle() );
        mandatControleDTO.setNumSession( mandatControle.getNumSession() );
        mandatControleDTO.setNumDossierDecla( mandatControle.getNumDossierDecla() );
        mandatControleDTO.setMotif( mandatControle.getMotif() );
        mandatControleDTO.setDateDebut( mandatControle.getDateDebut() );
        mandatControleDTO.setDateFin( mandatControle.getDateFin() );
        mandatControleDTO.setTypeMission( mandatControle.getTypeMission() );
        mandatControleDTO.setEtat( mandatControle.getEtat() );

        return mandatControleDTO;
    }

    @Override
    public MandatControle toEntity(MandatControleDTO mandatControleDTO) {
        if ( mandatControleDTO == null ) {
            return null;
        }

        MandatControle mandatControle = new MandatControle();

        mandatControle.setId( mandatControleDTO.getId() );
        mandatControle.setLibelle( mandatControleDTO.getLibelle() );
        mandatControle.setNumSession( mandatControleDTO.getNumSession() );
        mandatControle.setNumDossierDecla( mandatControleDTO.getNumDossierDecla() );
        mandatControle.setMotif( mandatControleDTO.getMotif() );
        mandatControle.setDateDebut( mandatControleDTO.getDateDebut() );
        mandatControle.setDateFin( mandatControleDTO.getDateFin() );
        mandatControle.setTypeMission( mandatControleDTO.getTypeMission() );
        mandatControle.setEtat( mandatControleDTO.getEtat() );

        return mandatControle;
    }
}
