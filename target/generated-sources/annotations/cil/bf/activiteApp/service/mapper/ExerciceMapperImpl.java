package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Exercice;
import cil.bf.activiteApp.service.dto.ExerciceDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class ExerciceMapperImpl implements ExerciceMapper {

    @Override
    public ExerciceDTO toDto(Exercice exercice) {
        if ( exercice == null ) {
            return null;
        }

        ExerciceDTO exerciceDTO = new ExerciceDTO();

        exerciceDTO.setId( exercice.getId() );
        exerciceDTO.setDateDebut( exercice.getDateDebut() );
        exerciceDTO.setDateFin( exercice.getDateFin() );
        exerciceDTO.setAnnee( exercice.getAnnee() );
        exerciceDTO.setStatut( exercice.getStatut() );
        exerciceDTO.setActif( exercice.isActif() );

        return exerciceDTO;
    }

    @Override
    public Exercice toEntity(ExerciceDTO exerciceDTO) {
        if ( exerciceDTO == null ) {
            return null;
        }

        Exercice exercice = new Exercice();

        exercice.setId( exerciceDTO.getId() );
        exercice.setDateDebut( exerciceDTO.getDateDebut() );
        exercice.setDateFin( exerciceDTO.getDateFin() );
        exercice.setAnnee( exerciceDTO.getAnnee() );
        exercice.setStatut( exerciceDTO.getStatut() );
        exercice.setActif( exerciceDTO.isActif() );

        return exercice;
    }
}
