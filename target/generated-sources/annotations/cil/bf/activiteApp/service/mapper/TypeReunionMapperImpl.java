package cil.bf.activiteApp.service.mapper;

import cil.bf.activiteApp.domain.TypeReunion;
import cil.bf.activiteApp.service.dto.TypeReunionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T19:46:56+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TypeReunionMapperImpl implements TypeReunionMapper {

    @Override
    public TypeReunionDTO toDto(TypeReunion t) {
        if ( t == null ) {
            return null;
        }

        TypeReunionDTO typeReunionDTO = new TypeReunionDTO();

        typeReunionDTO.setId( t.getId() );
        typeReunionDTO.setLibelle( t.getLibelle() );
        typeReunionDTO.setDescription( t.getDescription() );
        typeReunionDTO.setEtat( t.getEtat() );
        typeReunionDTO.setCode( t.getCode() );
        typeReunionDTO.setDeleted( t.isDeleted() );

        return typeReunionDTO;
    }

    @Override
    public TypeReunion toEntity(TypeReunionDTO t) {
        if ( t == null ) {
            return null;
        }

        TypeReunion typeReunion = new TypeReunion();

        typeReunion.setId( t.getId() );
        typeReunion.setLibelle( t.getLibelle() );
        typeReunion.setDescription( t.getDescription() );
        typeReunion.setCode( t.getCode() );
        typeReunion.setEtat( t.getEtat() );
        typeReunion.setDeleted( t.isDeleted() );

        return typeReunion;
    }
}
