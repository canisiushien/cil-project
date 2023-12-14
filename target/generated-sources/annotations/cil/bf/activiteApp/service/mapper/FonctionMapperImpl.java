package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.Fonction;
import cil.bf.activiteApp.service.dto.FonctionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class FonctionMapperImpl implements FonctionMapper {

    @Override
    public FonctionDTO toDto(Fonction exercice) {
        if ( exercice == null ) {
            return null;
        }

        FonctionDTO fonctionDTO = new FonctionDTO();

        fonctionDTO.setId( exercice.getId() );
        fonctionDTO.setLibelle( exercice.getLibelle() );
        fonctionDTO.setDescription( exercice.getDescription() );

        return fonctionDTO;
    }

    @Override
    public Fonction toEntity(FonctionDTO exerciceDTO) {
        if ( exerciceDTO == null ) {
            return null;
        }

        Fonction fonction = new Fonction();

        fonction.setId( exerciceDTO.getId() );
        fonction.setLibelle( exerciceDTO.getLibelle() );
        fonction.setDescription( exerciceDTO.getDescription() );

        return fonction;
    }
}
