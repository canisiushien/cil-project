package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.TypeIndicateur;
import cil.bf.activiteApp.service.dto.TypeIndicateurDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T18:47:57+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TypeIndicateurMapperImpl implements TypeIndicateurMapper {

    @Override
    public TypeIndicateurDTO toDto(TypeIndicateur t) {
        if ( t == null ) {
            return null;
        }

        TypeIndicateurDTO typeIndicateurDTO = new TypeIndicateurDTO();

        typeIndicateurDTO.setId( t.getId() );
        typeIndicateurDTO.setLibelle( t.getLibelle() );

        return typeIndicateurDTO;
    }

    @Override
    public TypeIndicateur toEntity(TypeIndicateurDTO t) {
        if ( t == null ) {
            return null;
        }

        TypeIndicateur typeIndicateur = new TypeIndicateur();

        typeIndicateur.setId( t.getId() );
        typeIndicateur.setLibelle( t.getLibelle() );

        return typeIndicateur;
    }
}
